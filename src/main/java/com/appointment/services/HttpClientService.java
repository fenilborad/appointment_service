package com.appointment.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface HttpClientService {
    JsonNode post(String urlPath, String payload);
    JsonNode get(String urlPath);
}
