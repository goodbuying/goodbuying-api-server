package goodbuyning.api_server.domain.entity;

import goodbuyning.api_server.domain.entity.enums.LinkType;
import jakarta.persistence.*;
import lombok.*;

/**
 * 마켓 연결 링크 정보를 저장하는 Entity
 * 틱톡, 유튜브 등의 소셜 미디어 링크를 관리합니다.
 */
@Entity
@Table(name = "link")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Link extends BaseEntity {

    /**
     * 링크 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 링크 타입
     * TIKTOK, YOUTUBE, INSTAGRAM, BLOG, WEBSITE 중 하나
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private LinkType type;

    /**
     * 링크 URL
     * 실제 연결될 외부 링크 주소입니다.
     */
    @Column(name = "url", nullable = false, length = 500)
    private String url;

    /**
     * 연결된 마켓 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;
}
