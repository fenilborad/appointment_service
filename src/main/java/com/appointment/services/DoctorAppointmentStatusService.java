package com.appointment.services;

import com.appointment.dto.StatusPayload;
import com.appointment.entities.DoctorAppointmentStatus;

public interface DoctorAppointmentStatusService {
    public DoctorAppointmentStatus save(StatusPayload payload);
}
