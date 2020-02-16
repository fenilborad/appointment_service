package com.appointment.services;

import com.appointment.dto.PatientRegistrationPayload;
import com.appointment.dto.PatientResponseEntity;

public interface RegistrationService {
    PatientResponseEntity getPatientByEmail(String email);

    PatientResponseEntity registration(PatientRegistrationPayload payload);
}
