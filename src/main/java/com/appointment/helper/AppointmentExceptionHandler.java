package com.appointment.helper;

import com.appointment.dto.ErrorResponseDTO;
import com.appointment.exceptions.DuplicateEntryException;
import com.appointment.exceptions.EntitySaveFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AppointmentExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = EntitySaveFailureException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntitySaveFailureException(final Exception ex) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(ex.getMessage());
        errorResponseDTO.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        errorResponseDTO.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponseDTO);
    }

    @ExceptionHandler(value = DuplicateEntryException.class)
    public ResponseEntity<ErrorResponseDTO> handleDuplicateEntryException(final Exception ex) {

        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(ex.getMessage());
        errorResponseDTO.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        errorResponseDTO.setStatusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponseDTO);
    }
}
