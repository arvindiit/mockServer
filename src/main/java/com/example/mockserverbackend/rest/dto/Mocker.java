package com.example.mockserverbackend.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mocker {
    String path;
    String operation;

    String name;
    RequestDTO request;
    ResponseDTO response;
}
