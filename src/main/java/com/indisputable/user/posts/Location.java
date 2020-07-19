package com.indisputable.user.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @Setter
    @Getter
    @JsonProperty("location_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64, unique = true)
    private String id;

    @Setter
    @Getter
    @JsonProperty("country")
    @Column(name = "country")
    private String country;

    @Setter
    @Getter
    @JsonProperty("state")
    @Column(name = "state")
    private String state;

    @Setter
    @Getter
    @JsonProperty("city")
    @Column(name = "city")
    private String city;

    @Setter
    @Getter
    @JsonProperty("locality")
    @Column(name = "locality")
    private String locality;


    @Setter
    @Getter
    @JsonProperty("pin_code")
    @Column(name = "pin_code")
    private int pinCode;
}
