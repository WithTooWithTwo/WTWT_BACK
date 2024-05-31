package wtwt.domain.trip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import wtwt.domain.user.model.enums.Gender;

@Embeddable
public class Preference {

    @Column(name = "prefer_min_age")
    private Integer minAge;

    @Column(name = "prefer_max_age")
    private Integer maxAge;

    @Column(name = "prefer_gender")
    private Gender gender;
}
