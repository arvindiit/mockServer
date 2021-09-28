package com.example.mockserverbackend.rest.service;

import com.example.mockserverbackend.rest.dto.ResponseDTO;
import com.example.mockserverbackend.rest.dto.ResponseHeaderDTO;
import com.example.mockserverbackend.rest.entity.*;
import com.example.mockserverbackend.rest.repository.MockRestRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class ResponseService {

    private MockRestRepository mockRestRepository;

    public ResponseService(MockRestRepository customRestRepository){
        this.mockRestRepository = customRestRepository;
    }

    @Transactional
    public ResponseDTO fetchMockResponse(String tenant, String path,
                                         String operation, Map<String, String> headers, String requestBody){

        MockRest mockRest = mockRestRepository.findByOperationAndPath(operation, path);
        requestBody = requestBody == null ? "" : requestBody;
        CustomRest currentCustomRest = null;
        for (CustomRest customRest : mockRest.getCustomRests()) {
            if (customRest.getRequest().getRequestBody().equals(requestBody)) {
                currentCustomRest = customRest;
                break;
            }
        }

        if (currentCustomRest != null) {

            boolean doHeadersMatch = ServiceHelper.doAllHeadersMatch(currentCustomRest.getRequest().getRequestHeaders(), headers);
            if (doHeadersMatch) {
                ResponseDTO responseDTO = buildResponseDTO(currentCustomRest.getResponse());
                return responseDTO;
            }
        }

        return null;
    }

    private ResponseDTO buildResponseDTO(CustomRestResponse customRestResponse){
        ResponseDTO.ResponseDTOBuilder responseDTOBuilder = ResponseDTO.builder().responseBody(customRestResponse.getResponseBody())
                .status(customRestResponse.getStatusCode());

        Set<ResponseHeaderDTO> responseHeaderDTOs = new HashSet<>();
        for (CustomRestResponseHeaders customRestResponseHeaders : customRestResponse.getResponseHeaders()){
            ResponseHeaderDTO responseHeaderDTO = new ResponseHeaderDTO(customRestResponseHeaders.getName(), customRestResponseHeaders.getValue());
            responseHeaderDTOs.add(responseHeaderDTO);
        }

        return responseDTOBuilder.headers(responseHeaderDTOs).build();
    }
}
