package com.appointment.dto;

import java.util.List;

public class TimeslotSuggestionResponseDto{


    private List suggestions;
    private String doctorCode;
    private Integer days;

    public List getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List suggestions) {
        this.suggestions = suggestions;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }


    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }
}
