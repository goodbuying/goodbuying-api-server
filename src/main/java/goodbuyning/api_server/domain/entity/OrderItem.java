package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 주문 상품 정보를 저장하는 Entity
 * 주문에 포함된 각 상품의 상세 정보를 관리합니다.
 */
@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderItem extends BaseEntity {

    /**
     * 주문 상품 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 ID (Foreign Key)
     * Product Entity와 연결됩니다.
     */
    @Column(name = "product_id", nullable = false)
    private Long productId;

    /**
     * 상품 옵션 ID (Foreign Key)
     * ProductOption Entity와 연결됩니다.
     */
    @Column(name = "product_option_id", nullable = false)
    private Long productOptionId;

    /**
     * 주문 수량
     * 해당 상품의 주문 개수입니다.
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * 단가
     * 주문 당시의 상품 가격입니다. (가격 변동에 대비)
     */
    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    /**
     * 총 가격
     * quantity * unit_price로 계산됩니다.
     */
    @Column(name = "total_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalPrice;

    /**
     * 연결된 주문 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * 상품 정보 (읽기 전용)
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    /**
     * 상품 옵션 정보 (읽기 전용)
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id", insertable = false, updatable = false)
    private ProductOption productOption;

    /**
     * 총 가격을 계산하여 설정합니다.
     */
    public void calculateTotalPrice() {
        this.totalPrice = this.unitPrice.multiply(BigDecimal.valueOf(this.quantity));
    }

    /**
     * 주문 수량을 변경합니다.
     */
    public void changeQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateTotalPrice();
    }

    /**
     * 주문과의 연관관계를 설정합니다.
     */
    public void setOrder(Order order) {
        this.order = order;
        if (order != null && !order.getOrderItems().contains(this)) {
            order.getOrderItems().add(this);
        }
    }

    /**
     * 주문 상품 생성 시 총 가격을 자동 계산합니다.
     */
    @PrePersist
    @PreUpdate
    public void prePersist() {
        calculateTotalPrice();
    }
}
