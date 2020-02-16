package com.appointment.helper;

import com.appointment.entities.DoctorAppointmentConfig;
import com.appointment.repositories.DoctorAppointmentConfigRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class AppointmentIntercepter extends HandlerInterceptorAdapter {

    @Autowired
    private DoctorAppointmentConfigRepository doctorAppointmentConfigRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().toUpperCase().equals("POST")){
            //Don't intercept.
            return true;
        }
        String doctorCode = request.getParameter("doctor_code");
        if(doctorCode == null || doctorCode.equals(StringUtils.EMPTY)){
            //throw new HeaderMissingException(messageSource.getMessage("doctor.code.missing",new Object[]{}, Locale.ENGLISH));
            Response res=ResponseHelper.sendFailureResponse(messageSource.getMessage("doctor.code.missing",new Object[]{}, Locale.ENGLISH));
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.setContentType("application/json; charset=UTF-8");
            String resJson = getInterceptorErrorMessage(res.getData(),res.getError());
            response.getWriter().write(resJson);
            return false;
        }
        DoctorAppointmentConfig doctorAppointmentConfig = doctorAppointmentConfigRepository.findByDoctorCode(doctorCode);
        if(doctorAppointmentConfig.isDoOff()){
            Response res=ResponseHelper.sendFailureResponse(messageSource.getMessage("doctor.appointment.off",new Object[]{}, Locale.ENGLISH));
            response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write(getInterceptorErrorMessage(res.getData(),res.getError()));
            //response.getWriter().write("doctor.appointment.off");
            return false;
        }
        return true;
    }

    private String getInterceptorErrorMessage(Object data,String error){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("error",error);
        ((ObjectNode) rootNode).putPOJO("data",data);

        return rootNode.toString();
    }
}
