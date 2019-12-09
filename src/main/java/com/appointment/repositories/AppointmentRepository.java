package com.appointment.repositories;

import com.appointment.entities.Appointment;
import com.appointment.entities.DoctorAppointmentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Page<Appointment> findByDoctorCode(String doctorCode, Pageable pageable);

    Appointment findByDoctorCodeAndAppointmentDateAndStartTime(String doctorCode,LocalDate appointmentDate, LocalTime startTime);

    Appointment findByDoctorCodeAndAppointmentDateAndStartTimeAndStatusNot(String doctorCode,LocalDate appointmentDate, LocalTime startTime,DoctorAppointmentStatus status);

    List<Appointment> findByDoctorCodeAndAppointmentDateAndStatusNot(String doctorCode, LocalDate appointmentDate, DoctorAppointmentStatus status);
}
