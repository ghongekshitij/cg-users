package com.indisputable.user.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indisputable.user.images.ImageInformation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_post")
public class UserPosts {

    @Id
    @Setter
    @Getter
    @JsonProperty("post_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64, unique = true)
    private String id;

    @Setter
    @Getter
    @JsonProperty("about")
    @Column(name = "about")
    private String about;

    @Setter
    @Getter
    @JsonProperty("services")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_services", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "service_id", referencedColumnName = "id")
    })
    private List<ProvidedServices> providedServices = new ArrayList<>();

    @Setter
    @Getter
    @JsonProperty("rate")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_rates", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "rate_id", referencedColumnName = "id")
    })
    private List<Rates> rates = new ArrayList<>();

    @Setter
    @Getter
    @JsonProperty("availabilities")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_availabilities", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "availabilities_id", referencedColumnName = "id")
    })
    private List<Availability> availabilities = new ArrayList<>();

    @Setter
    @Getter
    @JsonProperty("images")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_image", joinColumns = {
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
            @JoinColumn(name = "image_id", referencedColumnName = "id")
    })
    private List<ImageInformation> imageInformation = new ArrayList<>();

    @Setter
    @Getter
    @JsonProperty("general_information")
    @OneToOne(cascade = CascadeType.ALL)
    private GeneralInformation generalInformation;

    @Setter
    @Getter
    @JsonProperty("location")
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @Setter
    @Getter
    @JsonProperty("is_deleted")
    @Column(name = "is_deleted")
    private boolean isDeletedUser;

    @Setter
    @Getter
    @JsonProperty("is_blocked")
    @Column(name = "is_blocked")
    private boolean isBlocked;

}
