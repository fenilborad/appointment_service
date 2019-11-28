package com.appointment.services;

import com.appointment.dto.StatusPayload;
import com.appointment.entities.DoctorAppointmentStatus;
import com.appointment.repositories.AppointmentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DoctorAppointmentStatusServiceImpl implements DoctorAppointmentStatusService {

    @Autowired
    private AppointmentStatusRepository appointmentStatusRepository;

    @Override
    public DoctorAppointmentStatus save(StatusPayload payload) {
        DoctorAppointmentStatus doctorAppointmentStatus = new DoctorAppointmentStatus();
        doctorAppointmentStatus.setColor(payload.getColor());
        doctorAppointmentStatus.setDoctorCode(payload.getDoctorCode());
        doctorAppointmentStatus.setWeight(payload.getWeight());
        doctorAppointmentStatus.setDisplayName(payload.getDisplayName());
        doctorAppointmentStatus.setStatusName(payload.getName());
        doctorAppointmentStatus.setInsertedAt(new Date());
        doctorAppointmentStatus.setUpdatedAt(new Date());
        doctorAppointmentStatus.setInsertedBy("");
        doctorAppointmentStatus.setUpdatedBy("");
        return appointmentStatusRepository.save(doctorAppointmentStatus);
    }
}
