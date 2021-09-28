package com.example.mockserverbackend.rest.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "rest_request_headers")
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public class CustomRestRequestHeaders implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String value;

    public CustomRestRequestHeaders(String key, String value) {
        this.name = key;
        this.value = value;
    }

    public CustomRestRequestHeaders() {
    }


}
