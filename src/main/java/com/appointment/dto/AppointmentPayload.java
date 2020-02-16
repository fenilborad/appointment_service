package com.appointment.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentPayload {

    @NotEmpty(message = "{validation.patientcode.required}")
    @Pattern(regexp = "^([a-zA-Z]*)$")
    @NotBlank(message = "{validation.patientcode.required}")
    private String patientName;


    @Pattern(regexp = "^([a-zA-Z]*)$")
    private String patientLastName;

    @NotEmpty(message = "{validation.uiquecode.required}")
    @NotBlank(message = "{validation.uiquecode.required}")
    private String doctorCode;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @FutureOrPresent(message = "{validation.appointmentdate.mustbefuture}")
    private LocalDate date;

    @Email
    @NotBlank(message = "{validation.email.required}")
    @NotEmpty(message = "{validation.email.required}")
    private String email;

    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    @Size(min = 10, max = 12)
    @NotBlank(message = "{validation.phone.required}")
    @NotEmpty(message = "{validation.phone.required}")
    private String phone;

    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime time;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }
}
