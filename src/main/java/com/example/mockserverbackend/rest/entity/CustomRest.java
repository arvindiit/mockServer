package com.example.mockserverbackend.rest.entity;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"id", "customRestResponse"})
@AllArgsConstructor
public class CustomRest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToOne(cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private CustomRestRequest request;

    @OneToOne(cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private CustomRestResponse response;

    public CustomRest() {
    }
}
