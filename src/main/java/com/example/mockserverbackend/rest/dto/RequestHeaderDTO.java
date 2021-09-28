package com.example.mockserverbackend.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestHeaderDTO {
    private String key;

    private String value;

    public RequestHeaderDTO() {
    }
}
