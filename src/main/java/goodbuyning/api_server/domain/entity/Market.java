package goodbuyning.api_server.domain.entity;

import goodbuyning.api_server.domain.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 마켓 정보를 저장하는 Entity
 * 판매자가 운영하는 마켓의 기본 정보를 관리합니다.
 */
@Entity
@Table(name = "market")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Market extends BaseEntity {

    /**
     * 마켓 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 연결된 판매자 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;

    /**
     * 마켓 이름
     * 고객에게 표시되는 마켓의 상호명입니다.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * 마켓 설명
     * 마켓에 대한 간단한 소개 및 설명입니다.
     */
    @Column(name = "description", nullable = false, length = 500)
    private String description;

    /**
     * 마켓 상태
     * ACTIVE: 운영중, INACTIVE: 일시정지, DELETED: 삭제됨
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    /**
     * 마켓 대표 이미지 파일키
     * 파일 서버에 저장된 이미지의 식별자입니다.
     */
    @Column(name = "cover_image_filekey", length = 100)
    private String coverImageFilekey;

    /**
     * 계좌이체 타이머 (분 단위)
     * 5분 단위로 설정 가능하며 최대 30분까지 설정할 수 있습니다.
     */
    @Column(name = "timer", nullable = false)
    private Integer timer;

    /**
     * 마켓에 연결된 링크 목록
     * 1:N 관계로 연결됩니다.
     */
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Link> links = new ArrayList<>();

    /**
     * 마켓의 상품 목록
     * 1:N 관계로 연결됩니다.
     */
    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Product> products = new ArrayList<>();
}
