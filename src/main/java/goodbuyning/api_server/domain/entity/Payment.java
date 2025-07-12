package goodbuyning.api_server.domain.entity;

import goodbuyning.api_server.domain.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 결제 정보를 저장하는 Entity
 * 주문에 대한 입금자명, 입금액, 증빙서류 등을 관리합니다.
 */
@Entity
@Table(name = "payment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Payment extends BaseEntity {

    /**
     * 결제 정보 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 입금자명
     * 실제 계좌이체를 한 사람의 이름입니다.
     */
    @Column(name = "depositor_name", nullable = false, length = 50)
    private String depositorName;

    /**
     * 입금액
     * 실제 입금된 금액입니다.
     */
    @Column(name = "deposit_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal depositAmount;

    /**
     * 증빙서류 파일키
     * 입금 증명서나 계좌이체 영수증 파일의 식별자입니다.
     */
    @Column(name = "proof_filekey", nullable = false, length = 100)
    private String proofFilekey;

    /**
     * 결제 상태
     * 결제의 승인/거절 상태를 나타냅니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status;

    /**
     * 연결된 주문 정보
     * 1:1 관계로 연결됩니다.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
