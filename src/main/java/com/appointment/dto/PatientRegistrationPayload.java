package com.appointment.dto;

import javax.validation.constraints.*;

public class PatientRegistrationPayload {
    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z]*)$")
    private String firstName;

    @NotBlank
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z]*)$")
    private String lastName;

    @Email
    @NotEmpty
    @NotBlank
    private String email;

    @Pattern(regexp="^(0|[1-9][0-9]*)$")
    @Size(min = 10, max = 12)
    @NotBlank
    @NotEmpty
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
}
