package com.appointment.services;

import com.appointment.dto.*;
import com.appointment.entities.Appointment;
import com.appointment.entities.DoctorAppointmentConfig;
import com.appointment.entities.DoctorAppointmentStatus;
import com.appointment.entities.Weekdays;
import com.appointment.exceptions.DuplicateEntryException;
import com.appointment.exceptions.EntitySaveFailureException;
import com.appointment.helper.Response;
import com.appointment.helper.ResponseHelper;
import com.appointment.repositories.AppointmentRepository;
import com.appointment.repositories.AppointmentStatusRepository;
import com.appointment.repositories.DoctorAppointmentConfigRepository;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final int PAGE_SIZE = 10;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentStatusRepository appointmentStatusRepository;

    @Autowired
    private DoctorAppointmentConfigRepository doctorAppointmentConfigRepository;

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Response findByDoctorCode(String doctorCode, int pageNum) {
        Pageable firstPageWithTenElements = PageRequest.of(pageNum-1, PAGE_SIZE);

        Page<Appointment> appointmentPage = appointmentRepository.findByDoctorCode(doctorCode,firstPageWithTenElements);

        List<AppointmentResponseDTO> responseDTOList = appointmentPage.stream().sorted(Comparator.comparing(Appointment::getStartTime))
                .map(appointment-> convertAppointmentToAppointmentResponseDTO(appointment))
                .collect(Collectors.toList());

        return ResponseHelper.sendSuccessResponse(responseDTOList,appointmentPage.getTotalPages(),appointmentPage.getTotalElements());
    }

    @Override
    public Response save(AppointmentPayload payload) {
        PatientResponseEntity patientResponseEntity = registrationService.getPatientByEmail(payload.getEmail());
        String patientCode = StringUtils.EMPTY;
        if(patientResponseEntity != null){
            patientCode = patientResponseEntity.getPatientCode();
        }else{
            PatientRegistrationPayload patientRegistrationPayload = new PatientRegistrationPayload();
            patientRegistrationPayload.setEmail(payload.getEmail());
            patientRegistrationPayload.setFirstName(payload.getPatientName());
            patientRegistrationPayload.setLastName(payload.getPatientLastName());
            patientRegistrationPayload.setPhone(payload.getPhone());

            PatientResponseEntity response = registrationService.registration(patientRegistrationPayload);
            if(response!=null){
                patientCode = response.getPatientCode();
            }
        }

        DoctorAppointmentStatus statusCancelled = appointmentStatusRepository.findByDoctorCodeAndStatusName(payload.getDoctorCode(),"cancelled");
        Appointment appointment = appointmentRepository.findByDoctorCodeAndAppointmentDateAndStartTimeAndStatusNot(payload.getDoctorCode(),payload.getDate(),payload.getTime(),statusCancelled);
        if(appointment!=null){
            String errorMessage = messageSource.getMessage(
                    "validation.appointmenttime.exist", new Object[]{}, Locale.ENGLISH);
            throw new DuplicateEntryException(errorMessage);
        }
        Random random = new Random();
        String appointmentUniqueCode = "AP-"+LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd"))+"-"+ random.nextInt(1000);
        appointment = new Appointment();
        appointment.setDoctorCode(payload.getDoctorCode());
        appointment.setUniqueCode(appointmentUniqueCode);
        appointment.setPatientCode(patientCode);
        appointment.setDuration(30);
        appointment.setAppointmentDate(payload.getDate());
        appointment.setStartTime(payload.getTime());
        appointment.setEndTime(payload.getTime().plusMinutes(30));
        appointment.setInsertedAt(new Date());
        DoctorAppointmentStatus statusAccepted = appointmentStatusRepository.findByDoctorCodeAndStatusName(payload.getDoctorCode(),"not_accepted");
        appointment.setStatus(statusAccepted);
        return ResponseHelper.sendSuccessResponse(this.convertAppointmentToAppointmentResponseDTO(appointmentRepository.save(appointment)));
    }

    private List getAvailableTimeSlotsList(String doctorCode, LocalDate date){
        DoctorAppointmentConfig doctorAppointmentConfig = doctorAppointmentConfigRepository.findByDoctorCode(doctorCode);
        LocalTime openTime = doctorAppointmentConfig.getOpenFrom();
        LocalTime closeTime = doctorAppointmentConfig.getOpenTo();

        DoctorAppointmentStatus statusCancelled = appointmentStatusRepository.findByDoctorCodeAndStatusName(doctorCode,"cancelled");
        List<Appointment> appointments = appointmentRepository.findByDoctorCodeAndAppointmentDateAndStatusNot(doctorCode,date,statusCancelled).stream()
                .sorted(Comparator.comparing(Appointment::getStartTime)).collect(Collectors.toList());

        LocalTime slotEndTime;
        List<TimeSlot> timeSlots = new ArrayList<>();
        while(true){
            TimeSlot currentTimeslot = new TimeSlot();
            currentTimeslot.setFrom(openTime);
            slotEndTime = openTime.plusMinutes(30);
            currentTimeslot.setTo(slotEndTime);
            timeSlots.add(currentTimeslot);
            openTime = slotEndTime;
            if(openTime==closeTime)
                break;
        }

        List<TimeSlot> bookedSlots = new ArrayList<>();
        for (Appointment appointment:appointments) {
            TimeSlot bookedSlot = new TimeSlot();
            bookedSlot.setFrom(appointment.getStartTime());
            bookedSlot.setTo(appointment.getEndTime());
            bookedSlots.add(bookedSlot);
        }

        timeSlots.removeAll(bookedSlots);
        return timeSlots;
    }

    @Override
    public Response getAvailableTimeSlots(String doctorCode, LocalDate date) {
        AppointmentTimeslotsDTO appointmentTimeslotsDTO = new AppointmentTimeslotsDTO();
        appointmentTimeslotsDTO.setTimeslots(getAvailableTimeSlotsList(doctorCode,date));
        appointmentTimeslotsDTO.setDate(date);
        appointmentTimeslotsDTO.setDoctorCode(doctorCode);
        return ResponseHelper.sendSuccessResponse(appointmentTimeslotsDTO);
    }

    @Override
    public Response getSuggestedTimeSlots(String doctorCode, LocalDate date){

        DoctorAppointmentConfig doctorAppointmentConfig = doctorAppointmentConfigRepository.findByDoctorCode(doctorCode);
        Weekdays[] weekdays = doctorAppointmentConfig.getOpenDays();
        List<Integer> weekdaysList = Arrays.stream(weekdays).map(Weekdays::getValue).collect(Collectors.toList());

        LocalDate nextDate = date;
        int numOfDays=0;
        List<LocalDate> datesToSuggest = new ArrayList<>();
        while(true){
            if(numOfDays == 7)
                break;

            if(weekdaysList.contains(nextDate.getDayOfWeek().getValue())){
                numOfDays++;
                datesToSuggest.add(nextDate);
            }
            nextDate = nextDate.plusDays(1);
        }

        List<Suggesstion> suggestionList = new ArrayList<>();

        datesToSuggest.stream().forEach(i -> {
            Suggesstion suggesstion = new Suggesstion();
            suggesstion.setTimeslots(this.getAvailableTimeSlotsList(doctorCode,i));
            suggesstion.setDate(i);
            suggestionList.add(suggesstion);
        });

        TimeslotSuggestionResponseDto timeslotSuggestion = new TimeslotSuggestionResponseDto();
        timeslotSuggestion.setSuggestions(suggestionList);
        timeslotSuggestion.setDays(numOfDays);
        timeslotSuggestion.setDoctorCode(doctorCode);
        return ResponseHelper.sendSuccessResponse(timeslotSuggestion);
    }

    @Override
    public Response cancelAppointment(Long id) {
        Optional<Appointment> optionalAppointment= appointmentRepository.findById(id);
        String appointmentCode = "";
        String appointmentDate = "";
        boolean isCancelled = false;
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            DoctorAppointmentStatus cancelled = appointmentStatusRepository.findByDoctorCodeAndStatusName(appointment.getDoctorCode(),"cancelled");
            appointment.setStatus(cancelled);
            appointment = appointmentRepository.save(appointment);
            appointmentCode = appointment.getUniqueCode();
            appointmentDate = appointment.getAppointmentDate().toString();
            isCancelled = true;
        }
        if(isCancelled){
            String successMessage = messageSource.getMessage("appointment.cancelled",new Object[]{appointmentCode, appointmentDate},Locale.ENGLISH);
            return ResponseHelper.sendSuccessResponse(successMessage);
        }else{
            String errorMessage = messageSource.getMessage("appointment.cancel.fail",new Object[]{appointmentCode},Locale.ENGLISH);
            throw new EntitySaveFailureException(errorMessage);
        }
    }

    @Override
    public Response rescheduleAppointment(Long id, LocalDate date, LocalTime starttime) {
        Optional<Appointment> optionalAppointment= appointmentRepository.findById(id);
        String appointmentCode = "";
        String appointmentDate = "";
        boolean isRescheduled = false;
        if(optionalAppointment.isPresent()){
            Appointment appointment = optionalAppointment.get();
            DoctorAppointmentStatus cancelled = appointmentStatusRepository.findByDoctorCodeAndStatusName(appointment.getDoctorCode(),"cancelled");
            if(cancelled.equals(appointment.getStatus())){
                String errorMessage = messageSource.getMessage("appointment.reschedule.cancelled",new Object[]{},Locale.ENGLISH);
                throw new EntitySaveFailureException(errorMessage);
            }
            Appointment updatedAppointment = appointmentRepository.findByDoctorCodeAndAppointmentDateAndStartTimeAndStatusNot(
                    appointment.getDoctorCode(),date,starttime,cancelled);
            if(updatedAppointment!=null && appointment.getId() != updatedAppointment.getId()){
                String errorMessage = messageSource.getMessage("validation.appointmenttime.exist",new Object[]{},Locale.ENGLISH);
                throw new EntitySaveFailureException(errorMessage);
            }

            DoctorAppointmentConfig doctorAppointmentConfig = doctorAppointmentConfigRepository.findByDoctorCode(appointment.getDoctorCode());
            if(doctorAppointmentConfig!=null){
                if(starttime.isAfter(doctorAppointmentConfig.getOpenTo()) || starttime.isBefore(doctorAppointmentConfig.getOpenFrom())){
                    String fromTime = doctorAppointmentConfig.getOpenFrom().format(DateTimeFormatter.ofPattern("hh:mm a"));
                    String toTime = doctorAppointmentConfig.getOpenTo().format(DateTimeFormatter.ofPattern("hh:mm a"));
                    String errorMessage = messageSource.getMessage("validation.appointmenttime.outoftime",new Object[]{fromTime,toTime},Locale.ENGLISH);
                    throw new EntitySaveFailureException(errorMessage);
                }
            }

            DoctorAppointmentStatus notAccepted = appointmentStatusRepository.findByDoctorCodeAndStatusName(appointment.getDoctorCode(),"not_accepted");
            appointment.setStatus(notAccepted);
            appointment.setAppointmentDate(date);
            appointment.setStartTime(starttime);
            appointment.setEndTime(starttime.plusMinutes(30));
            appointment = appointmentRepository.save(appointment);
            appointmentCode = appointment.getUniqueCode();
            appointmentDate = appointment.getAppointmentDate().toString();
            isRescheduled = true;
        }
        if(isRescheduled){
            String successMessage = messageSource.getMessage("appointment.rescheduled",new Object[]{appointmentCode, appointmentDate},Locale.ENGLISH);
            return ResponseHelper.sendSuccessResponse(successMessage);
        }else{
            String errorMessage = messageSource.getMessage("appointment.rescheduled.fail",new Object[]{appointmentCode},Locale.ENGLISH);
            throw new EntitySaveFailureException(errorMessage);
        }
    }

    public AppointmentResponseDTO convertAppointmentToAppointmentResponseDTO(Appointment appointment){
        return modelMapper.map(appointment, AppointmentResponseDTO.class);
    }
}
