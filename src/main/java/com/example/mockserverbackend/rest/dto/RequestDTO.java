package com.example.mockserverbackend.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class RequestDTO {

    private Set<RequestHeaderDTO> headers;

    private String requestBody = "";

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody == null ? "" : requestBody;
    }

    public RequestDTO() {
    }
}
