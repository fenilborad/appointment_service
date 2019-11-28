package com.appointment.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class StatusPayload {

    @NotBlank
    @NotEmpty
    private String doctorCode;

    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z]*)$")
    private String name;

    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z]*)$")
    private String displayName;

    @NotBlank
    @NotEmpty
    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    private int weight;

    @NotBlank
    @NotEmpty
    private String color;

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
