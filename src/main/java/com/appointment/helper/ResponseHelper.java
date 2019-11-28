package com.appointment.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseHelper {

    private static Response response = new Response();

    public static ResponseEntity getSuccessResponse(List list){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json; charset=UTF-8");
        ResponseEntity response = new ResponseEntity(list,headers,HttpStatus.OK);
        return response;
    }

    public static ResponseEntity getSuccessResponse(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE,"application/json; charset=UTF-8");
        ResponseEntity response = new ResponseEntity(headers,HttpStatus.OK);
        return response;
    }

    public static Response sendSuccessResponse(Object data){
        try{
            response.setData(data);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    public static Response sendSuccessResponse(Object data, long totalPages, long totalElements){
        try{
            response.setData(data,totalPages,totalElements);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }

    public static Response sendFailureResponse(String error){
        response.setData(null);
        response.setError(error);
        return response;
    }
}
