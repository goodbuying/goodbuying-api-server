package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * 주문 관련 이미지 정보를 저장하는 Entity
 * 입금 증빙, 배송 관련 이미지 등을 관리합니다.
 */
@Entity
@Table(name = "order_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderImage extends BaseEntity {

    /**
     * 주문 이미지 고유 ID (Primary Key)
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
     * 연결된 주문 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * 이미지 파일키를 업데이트합니다.
     */
    public void updateFilekey(String filekey) {
        this.filekey = filekey;
    }

    /**
     * 주문과의 연관관계를 설정합니다.
     */
    public void setOrder(Order order) {
        this.order = order;
        if (order != null && !order.getOrderImages().contains(this)) {
            order.getOrderImages().add(this);
        }
    }
}
