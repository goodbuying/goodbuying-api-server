package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 판매자 소셜 로그인 정보를 저장하는 Entity
 * 카카오, 네이버, 구글, 애플 등의 소셜 로그인 연동 정보를 관리합니다.
 */
@Entity
@Table(name = "seller_social")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SellerSocial extends BaseEntity {

    /**
     * 소셜 로그인 정보 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 소셜 제공업체의 사용자 고유 키
     * 각 소셜 플랫폼에서 제공하는 사용자 식별자입니다.
     */
    @Column(name = "provider_key", nullable = false, length = 100)
    private String providerKey;

    /**
     * 소셜 로그인 제공업체
     * KAKAO, NAVER, GOOGLE, APPLE 중 하나
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private SocialProvider provider;

    /**
     * 소셜 토큰 만료 시간
     * 소셜 플랫폼에서 제공하는 토큰의 만료 시간입니다.
     */
    @Column(name = "expire_at")
    private LocalDateTime expireAt;

    /**
     * 소셜 연동 상태
     * ACTIVE: 연동됨, INACTIVE: 연동 해제
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    /**
     * 연결된 판매자 정보
     * 1:1 관계로 연결됩니다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    /**
     * 소셜 연동을 해제합니다.
     */
    public void disconnect() {
        this.status = Status.INACTIVE;
    }

    /**
     * 소셜 토큰 만료 시간을 업데이트합니다.
     */
    public void updateExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }
}
