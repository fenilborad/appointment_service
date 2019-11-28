package com.appointment.repositories;

import com.appointment.entities.DoctorAppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentStatusRepository extends JpaRepository<DoctorAppointmentStatus,Long> {
    List<DoctorAppointmentStatus> findByDoctorCode(String doctorCode);

    DoctorAppointmentStatus findByDoctorCodeAndStatusName(String doctorCode,String name);
}
