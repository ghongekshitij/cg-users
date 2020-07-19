package com.indisputable.user.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "service_availability")
public class Availability {

    @Id
    @Setter
    @Getter
    @JsonProperty("service_availability_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64, unique = true)
    private String id;

    @Setter
    @Getter
    @JsonProperty("day")
    @Column(name = "day")
    private String day;

    @Setter
    @Getter
    @JsonProperty("availability_type")
    @Column(name = "availability_type")
    private String availabilityType;
}
