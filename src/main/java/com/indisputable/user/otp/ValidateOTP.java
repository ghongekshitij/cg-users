package com.indisputable.user.otp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class ValidateOTP {

    @Getter
    @Setter
    @JsonProperty("user_id")
    private String userId;

    @Getter
    @Setter
    @JsonProperty("otp")
    private int otp;

}
