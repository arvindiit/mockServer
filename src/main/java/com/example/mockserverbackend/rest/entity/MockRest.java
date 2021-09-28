package com.example.mockserverbackend.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mock")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MockRest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String path;

    //@Enumerated(EnumType.STRING)
    private String operation;

    /*public enum Operation {
        GET,
        POST,
        PUT,
        DELETE
    }*/

    @OneToMany(cascade= CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<CustomRest> customRests;

    public MockRest() {

    }


}
