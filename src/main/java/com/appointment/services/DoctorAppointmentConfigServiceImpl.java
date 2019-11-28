package com.appointment.services;

import com.appointment.dto.DoctorAppointmentConfigPayload;
import com.appointment.entities.DoctorAppointmentConfig;
import com.appointment.exceptions.EntitySaveFailureException;
import com.appointment.helper.Response;
import com.appointment.helper.ResponseHelper;
import com.appointment.repositories.DoctorAppointmentConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class DoctorAppointmentConfigServiceImpl implements DoctorAppointmentConfigService{

    @Autowired
    private DoctorAppointmentConfigRepository doctorAppointmentConfigRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public Response save(DoctorAppointmentConfigPayload doctorAppointmentConfigPayload) {

        DoctorAppointmentConfig doctorAppointmentConfig = doctorAppointmentConfigRepository.findByDoctorCode(doctorAppointmentConfigPayload.getDoctorCode());
        if(doctorAppointmentConfig==null){
            doctorAppointmentConfig = new DoctorAppointmentConfig();
        }
        doctorAppointmentConfig.setCompulsory(doctorAppointmentConfigPayload.getIsCompulsory());
        doctorAppointmentConfig.setDoctorCode(doctorAppointmentConfigPayload.getDoctorCode());
        doctorAppointmentConfig.setDoOff(doctorAppointmentConfigPayload.getIsOff());

        doctorAppointmentConfig.setOpenDays(doctorAppointmentConfigPayload.getOpenDays());
        doctorAppointmentConfig.setOpenFrom(doctorAppointmentConfigPayload.getOpenFrom());
        doctorAppointmentConfig.setOpenTo(doctorAppointmentConfigPayload.getOpenTo());
        boolean isSaved = doctorAppointmentConfigRepository.save(doctorAppointmentConfig) !=null;
        if(!isSaved){
            String errorMessage = messageSource.getMessage(
                    "appointment.config.notsaved", new Object[]{}, Locale.ENGLISH);
            throw new EntitySaveFailureException(errorMessage);
        }

        String successMessage = messageSource.getMessage(
                "appointment.config.saved", new Object[]{}, Locale.ENGLISH);
        return ResponseHelper.sendSuccessResponse(successMessage);
    }
}
