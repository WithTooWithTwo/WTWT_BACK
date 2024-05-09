package wtwt.domain.auth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wtwt.common.base.BaseTimeEntity;
import wtwt.domain.auth.model.enums.ProviderType;
import wtwt.domain.user.model.User;

@Entity
@Table(name = "auth_provider")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthProvider extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "provider_type", length = 50, nullable = false, columnDefinition = "VARCHAR")
    @Enumerated(EnumType.STRING)
    private ProviderType providerType;

    @Column(name = "provider_id", length = 100)
    private String providerId;

    @Column(name = "refresh_token", length = 300)
    private String refreshToken;

    @Builder
    public AuthProvider(Long id, User user, ProviderType providerType, String providerId,
        String refreshToken) {
        this.id = id;
        this.user = user;
        this.providerType = providerType;
        this.providerId = providerId;
        this.refreshToken = refreshToken;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
