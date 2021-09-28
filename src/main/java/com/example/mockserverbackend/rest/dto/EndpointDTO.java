package com.example.mockserverbackend.rest.dto;

import com.example.mockserverbackend.rest.entity.MockRest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndpointDTO {
    String path;
    String operation;

    public EndpointDTO(String path, String operation) {
        this.path = path;
        this.operation = operation;
    }
}
