package goodbuyning.api_server.domain.entity;

import goodbuyning.api_server.domain.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 판매자 정보를 저장하는 Entity
 * 일반 회원가입 또는 소셜 로그인으로 가입한 판매자의 기본 정보를 관리합니다.
 */
@Entity
@Table(name = "seller")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Seller extends BaseEntity {

    /**
     * 판매자 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 판매자 고유 키 (Unique)
     * UUID 형태로 생성되어 외부 노출용 식별자로 사용됩니다.
     */
    @Column(name = "seller_key", unique = true, nullable = false, length = 36)
    private String sellerKey;

    /**
     * 판매자 이름 (실명)
     * 필수 입력 항목입니다.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 판매자 닉네임 (상호명)
     * 마켓에서 표시되는 이름으로 사용됩니다.
     */
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    /**
     * 판매자 연락처
     * 선택적 입력 항목입니다.
     */
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /**
     * 판매자 계정 상태
     * ACTIVE: 활성, INACTIVE: 비활성, DELETED: 삭제됨
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    /**
     * 리프레시 토큰 만료 시간
     */
    @Column(name = "expire_at")
    private LocalDateTime expireAt;

    /**
     * 소셜 로그인 정보
     * 1:1 관계로 연결됩니다.
     */
    @OneToOne(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SellerSocial sellerSocial;
}
