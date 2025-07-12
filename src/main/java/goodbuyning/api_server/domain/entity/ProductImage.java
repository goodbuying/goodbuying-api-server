package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 상품 이미지 정보를 저장하는 Entity
 * 상품에 첨부된 이미지 파일들을 관리합니다.
 */
@Entity
@Table(name = "product_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ProductImage extends BaseEntity {

    /**
     * 상품 이미지 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 이미지 파일키
     * 파일 서버에 저장된 이미지의 식별자입니다.
     */
    @Column(name = "filekey", nullable = false, length = 100)
    private String filekey;

    /**
     * 연결된 상품 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
