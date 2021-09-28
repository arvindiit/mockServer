package com.example.mockserverbackend.rest.controllers;

import com.example.mockserverbackend.Utils;
import com.example.mockserverbackend.rest.dto.Mocker;
import com.example.mockserverbackend.rest.dto.ResponseDTO;
import com.example.mockserverbackend.rest.entity.MockRest;
import com.example.mockserverbackend.rest.service.MockerService;
import com.example.mockserverbackend.rest.service.ResponseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class CustomRestController {

    private MockerService mockerService;
    private ResponseService responseService;

    public CustomRestController(MockerService mockerService, ResponseService responseService) {
        this.mockerService = mockerService;
        this.responseService = responseService;
    }

    @GetMapping("/rest/**")
    public ResponseEntity<String> getResponse(@RequestHeader Map<String, String> headers,
                              HttpServletRequest request){

        //String[] tenantAndPath = Utils.getTenantAndPath(request.getRequestURI().replaceFirst("/rest", ""));
        ResponseDTO responseDTO = responseService.fetchMockResponse(" ", request.getRequestURI().replaceFirst("/rest", ""), "GET", headers, null);
        ResponseEntity<String> responseEntity = ResponseEntity.status(HttpStatus.OK)
                .headers(responseDTO.getHttpHeaders())
                .body(responseDTO.getResponseBody());
        return responseEntity;

    }

    @PostMapping("/rest/**")
    public String getResponse(@RequestBody String body, @RequestHeader Map<String, String> headers,
                              @PathVariable("path") String path){
        return "posted";
    }

    @PostMapping("/rest/createMock")
    public String createMock(@RequestBody Mocker mocker){

        mockerService.createMock(mocker);
        return "created";
    }
}
