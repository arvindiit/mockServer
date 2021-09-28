package com.example.mockserverbackend.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "rest_response")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomRestResponse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int statusCode;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<CustomRestResponseHeaders> responseHeaders;

    @Lob
    private String responseBody;

    public CustomRestResponse() {
    }
}
