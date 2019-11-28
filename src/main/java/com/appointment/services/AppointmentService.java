package com.appointment.services;

import com.appointment.dto.AppointmentPayload;
import com.appointment.helper.Response;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentService {

    Response findByDoctorCode(String doctorCode, int pageNum);

    Response save(AppointmentPayload payload);

    Response getAvailableTimeSlots(String doctorCode, LocalDate date);

    Response cancelAppointment(Long id);

    Response rescheduleAppointment(Long id, LocalDate date, LocalTime starttime);
}
