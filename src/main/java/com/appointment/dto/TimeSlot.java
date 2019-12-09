package com.appointment.dto;

import java.time.LocalTime;

public class TimeSlot {
    private LocalTime from;
    private LocalTime to;

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TimeSlot)){
            return false;
        }
        TimeSlot t= (TimeSlot)obj;
        return this.from.equals(t.from) && this.to.equals(t.to);
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }
}
