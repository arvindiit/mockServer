package com.example.mockserverbackend;

import com.example.mockserverbackend.rest.dto.*;
import com.example.mockserverbackend.rest.entity.CustomRest;
import com.example.mockserverbackend.rest.entity.MockRest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

public class GenerateTestMock {

    public static void main(String[] args) throws JsonProcessingException {
        Mocker mocker = new Mocker();
        mocker.setOperation("GET");
        mocker.setPath("/arvind/rest/test");

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setRequestBody("This is test request body");

        RequestHeaderDTO requestHeaderDTO1 = new RequestHeaderDTO();
        requestHeaderDTO1.setKey("encoding");
        requestHeaderDTO1.setValue("application/json");

        RequestHeaderDTO requestHeaderDTO2 = new RequestHeaderDTO();
        requestHeaderDTO2.setKey("accept-language");
        requestHeaderDTO2.setValue("en");

        RequestHeaderDTO requestHeaderDTO3 = new RequestHeaderDTO();
        requestHeaderDTO3.setKey("x-auth-user");
        requestHeaderDTO3.setValue("abcd2091238ksdh");

        Set<RequestHeaderDTO> requestHeaderDTOSet = new HashSet<>();

        requestHeaderDTOSet.add(requestHeaderDTO1);
        requestHeaderDTOSet.add(requestHeaderDTO2);
        requestHeaderDTOSet.add(requestHeaderDTO3);

        requestDTO.setHeaders(requestHeaderDTOSet);

        ResponseHeaderDTO responseHeaderDTO1 = new ResponseHeaderDTO("accept-language", "gb");
        ResponseHeaderDTO responseHeaderDTO2 = new ResponseHeaderDTO("currency", "euro");
        ResponseHeaderDTO responseHeaderDTO3 = new ResponseHeaderDTO("authorized", "true");

        Set<ResponseHeaderDTO> responseHeaderDTOS = new HashSet<>();
        responseHeaderDTOS.add(responseHeaderDTO1);
        responseHeaderDTOS.add(responseHeaderDTO2);
        responseHeaderDTOS.add(responseHeaderDTO3);

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setHeaders(responseHeaderDTOS);
        responseDTO.setResponseBody("This is the mock response set by arvind");
        responseDTO.setStatus(200);


        mocker.setRequest(requestDTO);
        mocker.setResponse(responseDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(mocker);

        System.out.println(jsonBody);

    }
}
