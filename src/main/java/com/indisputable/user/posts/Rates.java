package com.indisputable.user.posts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "rates")
public class Rates {

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
    @JsonProperty("duration_type")
    @Column(name = "duration_type")
    private String durationAndType;

    @Setter
    @Getter
    @JsonProperty("rates")
    @Column(name = "rates")
    private int rates;
}
