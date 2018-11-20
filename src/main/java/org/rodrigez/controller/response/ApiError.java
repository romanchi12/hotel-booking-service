package org.rodrigez.controller.response;

import lombok.Getter;

@Getter
public class ApiError {
    private String description;

    public ApiError(String description) {
        this.description = description;
    }
}
