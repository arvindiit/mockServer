package com.example.mockserverbackend.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseDTO {

    private int status;
    private Set<ResponseHeaderDTO> headers;

    private String responseBody;

    public HttpHeaders getHttpHeaders(){
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        headers.forEach(header -> multiValueMap.addIfAbsent(header.getKey(), header.getValue()));
        return new HttpHeaders(multiValueMap);
    }

    public ResponseDTO() {
    }
}
