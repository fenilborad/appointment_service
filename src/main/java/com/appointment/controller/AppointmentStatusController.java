package com.appointment.controller;

import com.appointment.dto.StatusPayload;
import com.appointment.helper.Response;
import com.appointment.helper.ResponseHelper;
import com.appointment.services.DoctorAppointmentStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointment/status")
public class AppointmentStatusController {

    @Autowired
    private DoctorAppointmentStatusService doctorAppointmentStatusService;

    @GetMapping(path = "/health")
    public ResponseEntity health(){
        Response response = ResponseHelper.sendSuccessResponse("Appointment Status Service is up and running.");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping(consumes = {"application/json"})
    public ResponseEntity addStatus(@RequestBody StatusPayload statusPayload){
        Response response = ResponseHelper.sendSuccessResponse(doctorAppointmentStatusService.save(statusPayload));
        return new ResponseEntity(response, HttpStatus.OK);
    }

}
