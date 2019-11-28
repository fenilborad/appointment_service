package com.appointment.dto;

import com.appointment.entities.Weekdays;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

public class DoctorAppointmentConfigPayload {

    @NotBlank
    @NotEmpty
    private String doctorCode;

    private boolean isCompulsory;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime openFrom;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime openTo;

    @NotNull
    private Weekdays[] openDays;

    private boolean isOff;


    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public boolean getIsCompulsory() {
        return isCompulsory;
    }

    public void setIsCompulsory(boolean compulsory) {
        isCompulsory = compulsory;
    }

    public LocalTime getOpenFrom() {
        return openFrom;
    }

    public void setOpenFrom(LocalTime openFrom) {
        this.openFrom = openFrom;
    }

    public LocalTime getOpenTo() {
        return openTo;
    }

    public void setOpenTo(LocalTime openTo) {
        this.openTo = openTo;
    }

    public Weekdays[] getOpenDays() {
        return openDays;
    }

    public void setOpenDays(Weekdays[] openDays) {
        this.openDays = openDays;
    }

    public boolean getIsOff() {
        return isOff;
    }

    public void setIsOff(boolean off) {
        isOff = off;
    }
}
