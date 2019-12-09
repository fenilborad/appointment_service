package com.appointment.services;

import com.appointment.dto.DoctorAppointmentConfigPayload;
import com.appointment.helper.Response;

public interface DoctorAppointmentConfigService {
     Response save(DoctorAppointmentConfigPayload doctorAppointmentConfigPayload);

     Response getDoctorAppointmentConfig(String doctorCode);
}
