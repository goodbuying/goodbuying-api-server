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
}
