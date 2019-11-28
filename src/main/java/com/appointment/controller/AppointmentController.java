package com.appointment.controller;

import com.appointment.dto.AppointmentPayload;
import com.appointment.helper.Response;
import com.appointment.helper.ResponseHelper;
import com.appointment.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping(path = "/health")
    public ResponseEntity health(){
        Response response = ResponseHelper.sendSuccessResponse("Appointment Service is up and running.");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(value ="/{doctor_code}/{page_number}",produces = {"application/json; charset=UTF-8"})
    public ResponseEntity getAppointments(@PathVariable(name = "doctor_code") String doctorCode, @PathVariable(name = "page_number") int pageNumber){
        //Later, the Authentication Token will be passed to collect the list of appointments of particular doctor.
        return new ResponseEntity(appointmentService.findByDoctorCode(doctorCode,pageNumber), HttpStatus.OK);

    }

    @GetMapping(value ="/timeslots/{doctor_code}",produces = {"application/json; charset=UTF-8"})
    public ResponseEntity getAvailableAppointmentSlots(@PathVariable(name = "doctor_code") String doctorCode,
                                                       @RequestParam(name = "date") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate date){
        //Later, the Authentication Token will be passed to collect the list of appointments of particular doctor.
        return new ResponseEntity(appointmentService.getAvailableTimeSlots(doctorCode,date), HttpStatus.OK);

    }

    @PostMapping(produces = {"application/json"})
    public ResponseEntity bookAppointment(@Valid @RequestBody  AppointmentPayload payload){
        return new ResponseEntity(appointmentService.save(payload), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}",produces = {"application/json"})
    public ResponseEntity cancelAppointment(@PathVariable(name = "id") Long id){
        return new ResponseEntity(appointmentService.cancelAppointment(id),HttpStatus.OK);
    }

    @PutMapping(value = "/reschedule/{id}",produces = {"application/json"})
    public ResponseEntity rescheduleAppointment(@PathVariable(name = "id") Long id,
                                                @RequestParam(name = "date") @FutureOrPresent(message = "{validation.appointmentdate.mustbefuture}") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate date,
                                                @RequestParam(name = "time") @DateTimeFormat(pattern = "HH:mm") LocalTime time){
        return new ResponseEntity(appointmentService.rescheduleAppointment(id,date,time),HttpStatus.OK);
    }
}
