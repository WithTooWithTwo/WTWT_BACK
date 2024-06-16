package wtwt.domain.trip.model.enums;

import static java.util.Objects.isNull;

import java.util.Arrays;

public enum PreferGender {
    FEMALE, MALE, NONE;

    public static PreferGender from(String gender) {
        if (isNull(gender)) {
            return NONE;
        }

        return Arrays.stream(PreferGender.values())
            .filter(genderType -> genderType.name().equals(gender.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 선호 성별 타입입니다."));
    }
}
