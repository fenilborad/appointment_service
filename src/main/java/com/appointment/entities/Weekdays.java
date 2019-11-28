package com.appointment.entities;

public enum Weekdays {

    SUN("SUN"),
    MON("MON"),
    TUE("TUE"),
    WED("WED"),
    THU("THU"),
    FRI("FRI"),
    SAT("SAT");

    private String day;

    Weekdays(String day){
        this.day = day;
    }

    public String day(){
        return day;
    }
}
