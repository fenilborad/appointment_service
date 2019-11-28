package com.appointment.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class DeleteAppointmentPayload {

    @NotEmpty(message = "{validation.uiquecode.required}")
    @NotBlank(message = "{validation.uiquecode.required}")
    private String doctorCode;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @FutureOrPresent(message = "{validation.appointmentdate.mustbefuture}")
    private LocalDate date;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime time;

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

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
