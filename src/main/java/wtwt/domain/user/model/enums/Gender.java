package wtwt.domain.user.model.enums;

import java.util.Arrays;

public enum Gender {
    MALE, FEMALE, HIDE;

    public static Gender from(String gender) {
        return Arrays.stream(Gender.values())
            .filter(genderType -> genderType.name().equals(gender.toUpperCase()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 성별 타입입니다."));
    }
}
