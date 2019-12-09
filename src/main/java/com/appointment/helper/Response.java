package com.appointment.helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;

public class Response {
    private Object data;
    private String error;
    private boolean success = false;
    private long totalPages = 0;
    private long totalElements = 0;

    public void setData(Object data){
            this.data = data;
            this.success = true;
            this.error = StringUtils.EMPTY;
    }

    public void setData(Object data, long totalPages, long totalElements){
        this.data = data;
        this.success = true;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public void setError(String errorMessage){
        this.error = errorMessage;
        this.data = null;
        this.success = false;
    }



    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
