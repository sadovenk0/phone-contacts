package com.app.phonecontacts.exception;

import com.app.phonecontacts.model.dto.utils.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(
            HttpServletRequest request,
            Exception ex
    ) {
        return getErrorResponse(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<ErrorResponse> getErrorResponse(
            HttpServletRequest request,
            HttpStatus httpStatus,
            String message
    ) {
        log.warn("Exception raised = {} :: URL = {}", message, request.getRequestURL());
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorResponse(
                                httpStatus,
                                message,
                                request.getRequestURL().toString()
                        )
                );
    }
}
