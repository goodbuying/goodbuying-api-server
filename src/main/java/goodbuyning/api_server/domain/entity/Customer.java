package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 고객 정보를 저장하는 Entity
 * 상품을 구매하는 고객의 기본 정보를 관리합니다.
 */
@Entity
@Table(name = "customer")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Customer extends BaseEntity {

    /**
     * 고객 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 고객 고유 키 (Unique)
     * UUID 형태로 생성되어 외부 노출용 식별자로 사용됩니다.
     */
    @Column(name = "customer_key", unique = true, nullable = false, length = 36)
    private String customerKey;

    /**
     * 고객 이름 (실명)
     * 필수 입력 항목입니다.
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 고객 연락처
     * 주문 시 배송 정보로 사용됩니다.
     */
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    /**
     * 고객 기본 주소
     * 배송지 정보로 사용됩니다.
     */
    @Column(name = "address", length = 200)
    private String address;

    /**
     * 고객 상세 주소
     * 기본 주소와 함께 배송지 정보로 사용됩니다.
     */
    @Column(name = "address_detail", length = 200)
    private String addressDetail;

    /**
     * 고객 계정 상태
     * ACTIVE: 활성, INACTIVE: 비활성, DELETED: 삭제됨
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    /**
     * 리프레시 토큰
     * JWT 토큰 갱신을 위해 사용됩니다.
     */
    @Column(name = "refresh_token", length = 500)
    private String refreshToken;

    /**
     * 소셜 로그인 정보
     * 1:1 관계로 연결됩니다.
     */
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CustomerSocial customerSocial;

    /**
     * 고객의 상태를 변경합니다.
     */
    public void changeStatus(Status status) {
        this.status = status;
    }

    /**
     * 리프레시 토큰을 업데이트합니다.
     */
    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * 고객 정보를 업데이트합니다.
     */
    public void updateInfo(String name, String phoneNumber, String address, String addressDetail) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
