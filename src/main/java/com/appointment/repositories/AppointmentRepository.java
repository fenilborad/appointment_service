package com.appointment.repositories;

import com.appointment.entities.Appointment;
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

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);
}
