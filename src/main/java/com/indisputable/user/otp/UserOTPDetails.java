package com.indisputable.user.otp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.indisputable.user.info.UserInformation;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "otp_details")
public class UserOTPDetails {

    @Id
    @Getter
    @Setter
    @JsonProperty("otp_id")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 64, unique = true)
    private String id;

    @Getter
    @Setter
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", unique = true)
    private UserInformation userInformation;

    @Getter
    @Setter
    @Column(name = "generated_on")
    private Long generatedOn;

    @Getter
    @Setter
    @Column(name = "otp")
    private int otp;
}
