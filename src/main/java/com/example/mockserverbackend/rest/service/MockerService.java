package com.example.mockserverbackend.rest.service;

import com.example.mockserverbackend.rest.dto.*;
import com.example.mockserverbackend.rest.entity.*;
import com.example.mockserverbackend.rest.repository.MockRestRepository;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MockerService {

    private MockRestRepository mockRestRepository;
    public MockerService(MockRestRepository mockRestRepository){
        this.mockRestRepository = mockRestRepository;
    }

    public void createMock(Mocker mocker){

        String operation = mocker.getOperation();
        MockRest mockRest = mockRestRepository.findByOperationAndPath(operation, mocker.getPath());
        if(mockRest == null){
            mockRest = buildCustomRest(mocker, operation, mocker.getPath());
            mockRestRepository.save(mockRest);
        }

    }

    @Transactional
    public MockRest createMock(MockRest mockRest){
        return mockRestRepository.save(mockRest);

    }

    public List<EndpointDTO> fetchEndpointDetails(String tenant){
        List<EndpointDTO> list = mockRestRepository.findOperationAndPath();
        return list;
    }

    @Transactional
    public MockRest getCustomRest(String path, String operation){

        return mockRestRepository.findByOperationAndPath(operation, path);
        //return buildMockerResponse(set);

    }

    @Transactional
    public void delete(String path, String operation){

        MockRest mockRest = mockRestRepository.findByOperationAndPath(operation, path);
        mockRestRepository.delete(mockRest);
        //return buildMockerResponse(set);

    }

    private List<Mocker> buildMockerResponse(Set<CustomRest> customRests){
        List<Mocker> mockers = new ArrayList<>();
        for (CustomRest customRest: customRests){
            Mocker mocker = new Mocker();
            mocker.setName(customRest.getName());
            CustomRestRequest customRestRequest = customRest.getRequest();
            CustomRestResponse customRestResponse = customRest.getResponse();

            Set<RequestHeaderDTO> requestHeaderDTOSet = new HashSet<>();
            Set<ResponseHeaderDTO> responseHeaderDTOS = new HashSet<>();
            customRestRequest.getRequestHeaders().forEach(x -> requestHeaderDTOSet.add(new RequestHeaderDTO(x.getName(), x.getValue())));
            customRestResponse.getResponseHeaders().forEach(x -> responseHeaderDTOS.add(new ResponseHeaderDTO(x.getName(), x.getValue())));

            RequestDTO requestDTO = new RequestDTO();
            ResponseDTO responseDTO = new ResponseDTO();

            requestDTO.setRequestBody(customRestRequest.getRequestBody());
            requestDTO.setHeaders(requestHeaderDTOSet);

            responseDTO.setResponseBody(customRestResponse.getResponseBody());
            responseDTO.setHeaders(responseHeaderDTOS);
            mocker.setResponse(responseDTO);
            mocker.setRequest(requestDTO);

            mockers.add(mocker);

        }

        return mockers;

    }

    private MockRest buildCustomRest(Mocker mocker, String operation, String path){
        MockRest.MockRestBuilder mockRestBuilder = MockRest.builder().path(path).operation(operation);
        CustomRest customRest = new CustomRest();
        CustomRestRequest.CustomRestRequestBuilder customRestRequestBuilder = CustomRestRequest.builder().requestBody(mocker.getRequest().getRequestBody());
        CustomRestResponse.CustomRestResponseBuilder customRestResponseBuilder = CustomRestResponse.builder().responseBody(mocker.getResponse().getResponseBody());

        Set<CustomRestRequestHeaders> restRequestHeaders = new HashSet<>();
        Set<CustomRestResponseHeaders> restResponseHeaders = new HashSet<>();

        for(RequestHeaderDTO requestHeaderDTO: mocker.getRequest().getHeaders()) {
            CustomRestRequestHeaders customRestRequestHeader = new CustomRestRequestHeaders(requestHeaderDTO.getKey(), requestHeaderDTO.getValue());
            restRequestHeaders.add(customRestRequestHeader);
        }

        for(ResponseHeaderDTO responseHeaderDTO: mocker.getResponse().getHeaders()) {
            CustomRestResponseHeaders customRestResponseHeaders = new CustomRestResponseHeaders(responseHeaderDTO.getKey(), responseHeaderDTO.getValue());
            restResponseHeaders.add(customRestResponseHeaders);
        }

        customRest.setRequest(customRestRequestBuilder.requestHeaders(restRequestHeaders).build());
        customRest.setResponse(customRestResponseBuilder.responseHeaders(restResponseHeaders).build());
        Set<CustomRest> customRests = new HashSet<>();
        customRests.add(customRest);

        return mockRestBuilder.customRests(customRests).build();
    }
}
