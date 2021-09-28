package com.example.mockserverbackend.rest.repository;

import com.example.mockserverbackend.rest.dto.EndpointDTO;
import com.example.mockserverbackend.rest.entity.MockRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MockRestRepository extends JpaRepository<MockRest, Long> {

    MockRest findByOperationAndPath(String operation, String path);

    @Query("SELECT NEW com.example.mockserverbackend.rest.dto.EndpointDTO(mockRest.path, mockRest.operation) from MockRest mockRest")
    List<EndpointDTO> findOperationAndPath();
}
