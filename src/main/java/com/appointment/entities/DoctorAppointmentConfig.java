package com.appointment.entities;

import com.vladmihalcea.hibernate.type.array.EnumArrayType;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Table(name = "doctor_appointment_config")
@Entity
@TypeDef(
        typeClass = EnumArrayType.class,
        defaultForType = Weekdays[].class,
        parameters = { @org.hibernate.annotations.Parameter(
                name = EnumArrayType.SQL_ARRAY_TYPE,
                value = "weekdays")
        }
)
public class DoctorAppointmentConfig extends BaseEntity {

    @Id
    private String doctorCode;

    @Column(name = "compulsory")
    private boolean isCompulsory;

    @Column(name = "open_from", nullable = false)
    private LocalTime openFrom;

    @Column(name = "open_to", nullable = false)
    private LocalTime openTo;

    @Column(name = "open_days", columnDefinition = "open_days[]",nullable = false)
    private Weekdays[] openDays;

    @Column(name = "do_off")
    private boolean doOff;

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public boolean isCompulsory() {
        return isCompulsory;
    }

    public void setCompulsory(boolean compulsory) {
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

    public boolean isDoOff() {
        return doOff;
    }

    public void setDoOff(boolean doOff) {
        this.doOff = doOff;
    }
}
