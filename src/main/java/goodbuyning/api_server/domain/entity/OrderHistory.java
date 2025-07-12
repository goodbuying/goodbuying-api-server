package goodbuyning.api_server.domain.entity;

import goodbuyning.api_server.domain.entity.enums.ChangedBy;
import goodbuyning.api_server.domain.entity.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

/**
 * 주문 상태 변경 이력을 저장하는 Entity
 * 주문 상태가 변경될 때마다 이력을 기록하여 추적 가능하게 합니다.
 */
@Entity
@Table(name = "order_history")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderHistory extends BaseEntity {

    /**
     * 주문 이력 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 변경된 주문 상태
     * 새로 변경된 주문 상태입니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    /**
     * 이전 주문 상태
     * 변경 전의 주문 상태입니다. (최초 생성 시에는 null)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "previous_status")
    private OrderStatus previousStatus;

    /**
     * 변경 주체
     * 누가 상태를 변경했는지를 나타냅니다.
     * SYSTEM: 시스템, ADMIN: 관리자, CUSTOMER: 고객, SELLER: 판매자
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "changed_by", nullable = false)
    private ChangedBy changedBy;

    /**
     * 연결된 주문 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
