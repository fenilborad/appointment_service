package com.appointment.services;

import com.appointment.dto.PatientRegistrationPayload;
import com.appointment.dto.PatientResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Value("${registration.service.base.url}")
    private String baseUrl;

    private static final String GET_PATIENT = "/patient/{0}";
    private static final String REGISTER_PATIENT = "/patient";

    @Autowired
    private HttpClientService httpClientService;

    @Override
    public PatientResponseEntity getPatientByEmail(String email) {
        String url = MessageFormat.format(GET_PATIENT,email);
        JsonNode responseJson = httpClientService.get(baseUrl + url);
        responseJson = responseJson.get("data");
        ObjectMapper m = new ObjectMapper();
        try{
            PatientResponseEntity patient = m.treeToValue(responseJson, PatientResponseEntity.class);
            return patient;
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PatientResponseEntity registration(PatientRegistrationPayload payload) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            String jsonPayload = objectMapper.writeValueAsString(payload);
            JsonNode responseJson = httpClientService.post(baseUrl + REGISTER_PATIENT,jsonPayload);
            responseJson = responseJson.get("data");
            PatientResponseEntity patient = objectMapper.treeToValue(responseJson, PatientResponseEntity.class);
            return patient;
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }

    }
}
