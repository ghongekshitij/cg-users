package com.indisputable.user.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "image_information")
public class ImageInformation {

    @Id
    @Setter
    @Getter
    @JsonProperty("image_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64, unique = true)
    private String id;

    @Setter
    @Getter
    @JsonProperty("image_name")
    @Column(name = "name")
    private String imageName;

    @Setter
    @Getter
    @JsonProperty("image_location")
    @Column(name = "location")
    private String imageLocation;
}
