package wtwt.domain.trip.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import wtwt.domain.user.model.enums.Gender;

@Embeddable
public class Preference {

    @Column(name = "prefer_min_age")
    private Integer minAge;

    @Column(name = "prefer_max_age")
    private Integer maxAge;

    @Column(name = "prefer_gender", length = 10, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "capacity")
    private Integer capacity;
}
