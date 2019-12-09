package com.appointment.dto;

import java.time.LocalDate;
import java.util.List;

public class Suggesstion {
    private List timeslots;

    private LocalDate date;

    public List getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(List timeslots) {
        this.timeslots = timeslots;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
