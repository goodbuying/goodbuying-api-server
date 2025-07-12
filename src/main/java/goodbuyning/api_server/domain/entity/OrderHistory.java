package goodbuyning.api_server.domain.entity;

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

    /**
     * 주문과의 연관관계를 설정합니다.
     */
    public void setOrder(Order order) {
        this.order = order;
        if (order != null && !order.getOrderHistories().contains(this)) {
            order.getOrderHistories().add(this);
        }
    }

    /**
     * 상태 변경이 유효한지 확인합니다.
     */
    public boolean isValidStatusChange() {
        if (previousStatus == null) {
            // 최초 생성 시에는 PENDING 상태여야 함
            return orderStatus == OrderStatus.PENDING;
        }
        
        // 상태 변경 규칙 검증
        return switch (previousStatus) {
            case PENDING -> orderStatus == OrderStatus.CONFIRMED || orderStatus == OrderStatus.CANCELLED;
            case CONFIRMED -> orderStatus == OrderStatus.SHIPPED || orderStatus == OrderStatus.CANCELLED;
            case SHIPPED -> orderStatus == OrderStatus.DELIVERED;
            case DELIVERED, CANCELLED -> false; // 최종 상태에서는 변경 불가
        };
    }
}
