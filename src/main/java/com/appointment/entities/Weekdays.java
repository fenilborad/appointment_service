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

    public int getValue(){
        switch(day){
            case "MON":
                return 1;
            case "TUE":
                return 2;
            case "WED":
                return 3;
            case "THU":
                return 4;
            case "FRI":
                return 5;
            case "SAT":
                return 6;
            default:
                return 7;
        }
    }
}
