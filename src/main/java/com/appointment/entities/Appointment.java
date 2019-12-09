package com.appointment.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity(name = "appointment")
@Table(name = "appointment",uniqueConstraints = {@UniqueConstraint(columnNames = {"appointment_date","starttime","status_id"})})
public class Appointment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "unique_code",unique = true,nullable = false)
    private String uniqueCode;

    @Column(name = "patient_code",nullable = false)
    @NotBlank(message = "{validation.patientcode.required}")
    private String patientCode;

    @Column(name = "doctor_code",nullable = false)
    @NotBlank(message = "{validation.uiquecode.required}")
    private String doctorCode;

    @Column(name = "appointment_date",nullable = false)
    @FutureOrPresent(message = "{validation.appointmentdate.mustbefuture}")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate appointmentDate;

    @Column(name = "starttime",nullable = false)
    private LocalTime startTime;

    @Column(name = "endtime",nullable = false)
    private LocalTime endTime;

    @Column(name = "duration",nullable = false)
    private int duration;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private DoctorAppointmentStatus status;

    @Column(name = "inserted_at",nullable = false)
    private Date insertedAt;

    @Column(name = "updated_at",nullable = false)
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getPatientCode() {
        return patientCode;
    }

    public void setPatientCode(String patientCode) {
        this.patientCode = patientCode;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public DoctorAppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(DoctorAppointmentStatus status) {
        this.status = status;
    }

    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
