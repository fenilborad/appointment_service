package com.appointment.helper;

public class Response {
    private Object data;
    private String error = null;
    private boolean success = false;
    private long totalPages = 0;
    private long totalElements = 0;

    public void setData(Object data){
            this.data = data;
            this.success = true;
    }

    public void setData(Object data, long totalPages, long totalElements){
        this.data = data;
        this.success = true;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public void setError(String errorMessage){
        this.error = errorMessage;
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
