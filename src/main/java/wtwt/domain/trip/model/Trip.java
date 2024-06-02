package wtwt.domain.trip.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wtwt.common.base.BaseTimeEntity;
import wtwt.domain.user.model.User;
import wtwt.domain.user.model.enums.Gender;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Trip extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @Embedded
    private Preference preference;

    @Builder
    public Trip(LocalDate startDate, LocalDate endDate, List<User> users, Integer preferMinAge,
        Integer preferMaxAge, Gender preferGender, Integer preferCapacity) {
        validateDate(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
        this.members = toMembers(users);
        this.preference = Preference.builder()
            .minAge(preferMinAge)
            .maxAge(preferMaxAge)
            .gender(preferGender)
            .capacity(preferCapacity)
            .build();
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("여행 시작 날짜는 여행 종료 날짜보다 이전이어야 합니다.");
        }
    }

    private List<Member> toMembers(List<User> users) {
        List<Member> members = new ArrayList<>();
        for (User user : users) {
            members.add(Member.builder()
                .user(user)
                .trip(this)
                .build());
        }
        return members;
    }
}
