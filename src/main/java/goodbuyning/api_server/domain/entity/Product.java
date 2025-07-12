package goodbuyning.api_server.domain.entity;

import goodbuyning.api_server.domain.entity.enums.Status;
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
}
