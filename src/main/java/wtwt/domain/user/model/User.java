package wtwt.domain.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wtwt.common.base.BaseTimeEntity;
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

    @Column(name = "gender")
    private Gender gender;

    @Builder
    public User(Long id, String nickname, String email, String password, String profileImageUrl,
        String statusMassage, Gender gender) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.profileImageUrl = profileImageUrl;
        this.statusMassage = statusMassage;
        this.gender = gender;
    }
}
