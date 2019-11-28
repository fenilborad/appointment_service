package com.appointment.repositories;

import com.appointment.entities.DoctorAppointmentConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorAppointmentConfigRepository extends JpaRepository<DoctorAppointmentConfig,Long> {
    DoctorAppointmentConfig findByDoctorCode(String doctorCode);
}
