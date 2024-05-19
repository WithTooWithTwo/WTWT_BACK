package wtwt.domain.user.model;

import static java.util.Objects.isNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import wtwt.common.base.BaseTimeEntity;
import wtwt.domain.user.model.enums.Authority;
import wtwt.domain.user.model.enums.Gender;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname", length = 20, unique = true)
    private String nickname;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "profile_image_url", length = 1024)
    private String profileImageUrl;

    @Column(name = "status_message")
    private String statusMassage;

    @Column(name = "gender", length = 10, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;

    @Column(name = "authority", length = 15, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Builder
    public User(Long id, String nickname, String email, String password, String profileImageUrl,
        String statusMassage, Gender gender, LocalDate birthDate, Authority authority) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.statusMassage = statusMassage;
        this.gender = gender;
        this.birthDate = birthDate;
        this.authority = determineAuthority(authority, nickname);
    }

    public boolean checkPassword(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(rawPassword, password);
    }

    public Authority determineAuthority(Authority authority, String nickname) {
        if (!isNull(authority)) {
            return authority;
        }

        return isNull(nickname) ? Authority.RESTRICTED : Authority.NORMAL;
    }

    public void setProfileImage(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
        this.authority = Authority.NORMAL;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMassage = statusMessage;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
