package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 상품 옵션 정보를 저장하는 Entity
 * 상품의 사이즈, 색상, 재고 정보를 관리합니다.
 */
@Entity
@Table(name = "product_option")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductOption extends BaseEntity {

    /**
     * 상품 옵션 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품 사이즈
     * 기본값은 "NONE" 또는 "FREE"입니다.
     */
    @Column(name = "size", nullable = false, length = 50)
    @Builder.Default
    private String size = "FREE";

    /**
     * 상품 색상
     * 기본값은 "NONE"입니다.
     */
    @Column(name = "color", nullable = false, length = 50)
    @Builder.Default
    private String color = "NONE";

    /**
     * 총 재고량
     * 해당 옵션의 전체 재고 수량입니다.
     */
    @Column(name = "total_stock", nullable = false)
    private Integer totalStock;

    /**
     * 현재 재고량
     * 주문에 따라 감소하는 실제 재고 수량입니다.
     */
    @Column(name = "current_stock", nullable = false)
    private Integer currentStock;

    /**
     * 연결된 상품 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * 옵션 정보를 업데이트합니다.
     */
    public void updateOption(String size, String color, Integer totalStock, Integer currentStock) {
        this.size = size;
        this.color = color;
        this.totalStock = totalStock;
        this.currentStock = currentStock;
    }

    /**
     * 재고를 감소시킵니다.
     * 주문 시 호출됩니다.
     */
    public void decreaseStock(int quantity) {
        if (currentStock < quantity) {
            throw new IllegalArgumentException("재고가 부족합니다.");
        }
        this.currentStock -= quantity;
    }

    /**
     * 재고를 증가시킵니다.
     * 주문 취소 시 호출됩니다.
     */
    public void increaseStock(int quantity) {
        this.currentStock += quantity;
        // 총 재고량을 초과할 수 없습니다.
        if (this.currentStock > this.totalStock) {
            this.currentStock = this.totalStock;
        }
    }

    /**
     * 재고를 보충합니다.
     */
    public void replenishStock(int additionalStock) {
        this.totalStock += additionalStock;
        this.currentStock += additionalStock;
    }

    /**
     * 품절 여부를 확인합니다.
     */
    public boolean isOutOfStock() {
        return currentStock <= 0;
    }

    /**
     * 상품과의 연관관계를 설정합니다.
     */
    public void setProduct(Product product) {
        this.product = product;
        if (product != null && !product.getProductOptions().contains(this)) {
            product.getProductOptions().add(this);
        }
    }
}
