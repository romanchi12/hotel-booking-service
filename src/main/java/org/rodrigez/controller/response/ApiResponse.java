package org.rodrigez.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;

@Getter
@JsonInclude(Include.NON_NULL)
public class ApiResponse {
    private Status status;
    private Object data;
    private ApiError error;

    public ApiResponse(Status status, Object data) {
        this.status = status;
        this.data = data;
    }

    public ApiResponse(Status status, ApiError error) {
        this.status = status;
        this.error = error;
    }
}