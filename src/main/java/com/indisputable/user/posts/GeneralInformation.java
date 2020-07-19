package com.indisputable.user.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "general_information")
public class GeneralInformation {

    @Id
    @Setter
    @Getter
    @JsonProperty("information_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64, unique = true)
    private String id;

    @Setter
    @Getter
    @JsonProperty("viewed")
    @Column(name = "viewed")
    private int viewed;

    @Setter
    @Getter
    @JsonProperty("mobile")
    @Column(name = "mobile")
    private Long mobile;

    @Setter
    @Getter
    @JsonProperty("profile_type")
    @Column(name = "profile_type")
    private short profileType;

    @Setter
    @Getter
    @JsonProperty("gender")
    @Column(name = "gender")
    private short gender;

    @Setter
    @Getter
    @JsonProperty("age")
    @Column(name = "age")
    private short age;

    @Setter
    @Getter
    @JsonProperty("bust_waist_hip")
    @Column(name = "bust_waist_hip")
    private String bustWaistHip;

    @Setter
    @Getter
    @JsonProperty("eye_color")
    @Column(name = "eye_color")
    private String eyeColor;

    @Setter
    @Getter
    @JsonProperty("height")
    @Column(name = "height")
    private short height;

    @Setter
    @Getter
    @JsonProperty("weight")
    @Column(name = "weight")
    private short weight;

    @Setter
    @Getter
    @JsonProperty("ethnicity")
    @Column(name = "ethnicity")
    private String ethnicity;

    @Setter
    @Getter
    @JsonProperty("nationality")
    @Column(name = "nationality")
    private String nationality;

}
