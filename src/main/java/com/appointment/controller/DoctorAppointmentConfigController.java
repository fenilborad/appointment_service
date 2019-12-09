package com.appointment.controller;

import com.appointment.dto.DoctorAppointmentConfigPayload;
import com.appointment.helper.ResponseHelper;
import com.appointment.services.DoctorAppointmentConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/appointment/config")
public class DoctorAppointmentConfigController {

    @Autowired
    private DoctorAppointmentConfigService doctorAppointmentConfigService;

    @GetMapping(path = "/health")
    public ResponseEntity health(){
        return new ResponseEntity(ResponseHelper.sendSuccessResponse("Appointment Config service is up and running"), HttpStatus.OK);
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity save(@Valid @RequestBody DoctorAppointmentConfigPayload doctorAppointmentConfigPayload){
        return new ResponseEntity(doctorAppointmentConfigService.save(doctorAppointmentConfigPayload),HttpStatus.OK);
    }

    @GetMapping(path = "/{doctorCode}")
    public ResponseEntity getConfig(@PathVariable(name = "doctorCode") String doctorCode){
        return new ResponseEntity(doctorAppointmentConfigService.getDoctorAppointmentConfig(doctorCode), HttpStatus.OK);
    }
}
