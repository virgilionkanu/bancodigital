package br.com.bancodigital.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;
    private String friendlyMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiSubError> subErrors;

    private ApiError() { this.timestamp = LocalDateTime.now(); }

    ApiError(HttpStatus status, String friendlyMessage) {
        this();
        this.status = status;
        this.friendlyMessage = friendlyMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getFriendlyMessage() {
        return friendlyMessage;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public List<ApiSubError> getSubErrors() {
        return subErrors;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", timestamp=" + timestamp +
                ", friendlyMessage='" + friendlyMessage + '\'' +
                ", debugMessage='" + debugMessage + '\'' +
                ", subErrors=" + subErrors +
                '}';
    }

    void addBindingResult(BindingResult bindingResult) {
        bindingResult.getGlobalErrors().forEach(this::addValidationError);
        bindingResult.getFieldErrors().forEach(this::addValidationError);
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(
                objectError.getObjectName(),
                objectError.getDefaultMessage());
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getObjectName(),
                fieldError.getField(),
                fieldError.getRejectedValue(),
                fieldError.getDefaultMessage());
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        addSubError(new ApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        addSubError(new ApiValidationError(object, message));
    }

    private void addSubError(ApiSubError subError) {
        if(subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    @JsonDeserialize(as = ApiValidationError.class)
    abstract static class ApiSubError {
    }

    private static class ApiValidationError extends ApiSubError {
        private String object;
        private String field;
        private Object rejectedValue;
        private String message;

        ApiValidationError(String object, String message) {
            this.object = object;
            this.message = message;
        }

        public ApiValidationError(String object, String field, Object rejectedValue, String message) {
            this.object = object;
            this.field = field;
            this.rejectedValue = rejectedValue;
            this.message = message;
        }

        public String getObject() {
            return object;
        }

        public String getField() {
            return field;
        }

        public Object getRejectedValue() {
            return rejectedValue;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "ApiValidationError{" +
                    "object='" + object + '\'' +
                    ", field='" + field + '\'' +
                    ", rejectedValue=" + rejectedValue +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
