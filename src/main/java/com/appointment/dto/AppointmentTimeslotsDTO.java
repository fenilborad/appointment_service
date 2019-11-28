package com.appointment.dto;

import java.time.LocalDate;
import java.util.List;

public class AppointmentTimeslotsDTO {

    private List timeslots;

    private String doctorCode;

    private LocalDate date;

    public List getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List timeslots) {
        this.timeslots = timeslots;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
