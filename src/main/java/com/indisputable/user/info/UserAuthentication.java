package com.indisputable.user.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_auth")
public class UserAuthentication {

    @Id
    @Setter
    @Getter
    @JsonProperty("user_id")
    @Column(name = "id", nullable = false, length = 64, unique = true)
    private String id;

    @Setter
    @Getter
    @JsonProperty("password")
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Setter
    @Getter
    @MapsId
    @OneToOne
    @JoinColumn
    @JsonIgnore
    private UserInformation userInformation;
}
