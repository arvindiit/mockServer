package com.example.mockserverbackend.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "rest_request")
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
public class CustomRestRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(cascade=CascadeType.ALL)
    private Set<CustomRestRequestHeaders> requestHeaders;

    @Lob
    private String requestBody;

    public CustomRestRequest() {
    }


}
