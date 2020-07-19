package com.indisputable.user.info;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "user_information")
public class UserInformation {

    @Id
    @Setter
    @Getter
    @JsonProperty("user_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_id", nullable = false, length = 64, unique = true)
    private String id;

    @Setter
    @Getter
    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @Setter
    @Getter
    @JsonProperty("user_type")
    @Column(name = "user_type")
    private Byte userType;

    @Setter
    @Getter
    @JsonProperty("business_agency_name")
    @Column(name = "business_agency_name")
    private String businessOrAgencyName;

    @Setter
    @Getter
    @JsonProperty("mobile")
    @Column(name = "mobile", unique = true)
    private Long mobile;

    @Setter
    @Getter
    @JsonProperty("is_mobile_verified")
    @Column(name = "is_mobile_verified")
    private boolean isMobileVerified;

    @Setter
    @Getter
    @JsonProperty("email")
    @Column(name = "email", unique = true)
    private String email;

    @Setter
    @Getter
    @JsonProperty("is_email_verified")
    @Column(name = "is_email_verified")
    private boolean isEmailVerified;

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

    @Setter
    @Getter
    @JsonProperty("is_assured")
    @Column(name = "is_assured")
    private boolean isAssured;

    @Setter
    @Getter
    @JsonProperty("is_verified")
    @Column(name = "is_verified")
    private boolean isVerified;

    @Setter
    @Getter
    @OneToOne(mappedBy = "userInformation", cascade = CascadeType.ALL)
    private UserAuthentication userAuthentication;
}
