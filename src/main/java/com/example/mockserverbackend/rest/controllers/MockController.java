package com.example.mockserverbackend.rest.controllers;


import com.example.mockserverbackend.rest.dto.EndpointDTO;
import com.example.mockserverbackend.rest.dto.Mocker;
import com.example.mockserverbackend.rest.entity.CustomRest;
import com.example.mockserverbackend.rest.entity.MockRest;
import com.example.mockserverbackend.rest.service.MockerService;
import com.example.mockserverbackend.rest.service.ResponseService;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin("*")
public class MockController {


    private MockerService mockerService;
    private ResponseService responseService;

    public MockController(MockerService mockerService, ResponseService responseService) {
        this.mockerService = mockerService;
        this.responseService = responseService;
    }
    @GetMapping("/mock/fetchEndpointDetails")
    public List<EndpointDTO> getResponse(@RequestParam(name = "tenant") String tenant,
                                         HttpServletRequest request){

        return mockerService.fetchEndpointDetails(" ");

    }

    @GetMapping("/mock/fetchMockDetails")
    public MockRest getMocker(@RequestParam(name = "tenant") String tenant,
                                     @RequestParam(name = "path") String path,
                                     @RequestParam(name = "operation") String operation){

        return mockerService.getCustomRest(path, operation);
        //return response;

    }

    @PostMapping("/mock/createMock")
    public MockRest createMock(@RequestParam(name = "tenant") String tenant,
                             @RequestBody MockRest mocker){

        return mockerService.createMock(mocker);
    }

    @DeleteMapping("/mock/deleteMock")
    public void deleteMock(@RequestParam(name = "tenant") String tenant,
                              @RequestParam(name = "path") String path,
                              @RequestParam(name = "operation") String operation) {

        mockerService.delete(path, operation);
    }
}
