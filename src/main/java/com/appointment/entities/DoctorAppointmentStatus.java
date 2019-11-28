package com.appointment.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doctor_appointment_status",uniqueConstraints = {@UniqueConstraint(columnNames = {"doctor_code","status_name"})})
public class DoctorAppointmentStatus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="doctor_code",nullable = false)
    private String doctorCode;

    @Column(name="status_name",nullable = false)
    private String statusName;

    @Column(name="display_name",nullable = false)
    private String displayName;

    @Column(name="weight",nullable = false)
    private int weight;

    @Column(name="color",nullable = false)
    private String color;

    @Column(name="inserted_at",nullable = false)
    private Date insertedAt;

    @Column(name="updated_at",nullable = false)
    private Date updatedAt;

    @Column(name="inserted_by",nullable = false)
    private String insertedBy;

    @Column(name="updated_by",nullable = false)
    private String updatedBy;

    public Long getId() {
        return id;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getInsertedBy() {
        return insertedBy;
    }

    public void setInsertedBy(String insertedBy) {
        this.insertedBy = insertedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
