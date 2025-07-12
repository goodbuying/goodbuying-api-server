package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 상품 정보를 저장하는 Entity
 * 마켓에서 판매되는 상품의 기본 정보를 관리합니다.
 */
@Entity
@Table(name = "product")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    /**
     * 상품 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 상품명
     * 고객에게 표시되는 상품의 이름입니다.
     */
    @Column(name = "name", nullable = false, length = 200)
    private String name;

    /**
     * 상품 가격
     * 원화 기준의 상품 판매 가격입니다.
     */
    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    /**
     * 상품 설명
     * 상품에 대한 상세 설명입니다.
     */
    @Column(name = "description", length = 1000)
    private String description;

    /**
     * 상품 상태
     * AVAILABLE: 판매가능, UNAVAILABLE: 품절, DISABLED: 비활성화
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    /**
     * 할인율 (%)
     * 0~100 사이의 값으로 기본값은 0입니다.
     */
    @Column(name = "discount", nullable = false)
    @Builder.Default
    private Integer discount = 0;

    /**
     * 연결된 마켓 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

    /**
     * 상품 옵션 목록 (사이즈, 색상, 재고)
     * 1:N 관계로 연결됩니다.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductOption> productOptions = new ArrayList<>();

    /**
     * 상품 이미지 목록
     * 1:N 관계로 연결됩니다.
     */
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ProductImage> productImages = new ArrayList<>();

    /**
     * 상품 정보를 업데이트합니다.
     */
    public void updateInfo(String name, BigDecimal price, String description, Integer discount) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.discount = discount;
    }

    /**
     * 상품 상태를 변경합니다.
     */
    public void changeStatus(Status status) {
        this.status = status;
    }

    /**
     * 마켓과의 연관관계를 설정합니다.
     */
    public void setMarket(Market market) {
        this.market = market;
        if (market != null && !market.getProducts().contains(this)) {
            market.getProducts().add(this);
        }
    }

    /**
     * 할인이 적용된 실제 판매 가격을 계산합니다.
     */
    public BigDecimal getDiscountedPrice() {
        if (discount == 0) {
            return price;
        }
        BigDecimal discountAmount = price.multiply(BigDecimal.valueOf(discount)).divide(BigDecimal.valueOf(100));
        return price.subtract(discountAmount);
    }

    /**
     * 상품의 총 재고량을 계산합니다.
     */
    public int getTotalStock() {
        return productOptions.stream()
                .mapToInt(ProductOption::getCurrentStock)
                .sum();
    }

    /**
     * 상품이 품절인지 확인합니다.
     */
    public boolean isOutOfStock() {
        return getTotalStock() <= 0;
    }
}
