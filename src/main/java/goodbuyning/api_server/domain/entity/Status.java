package goodbuyning.api_server.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 엔티티의 일반적인 상태를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum Status {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    DELETED("삭제"),
    PENDING("대기중"),
    SUSPENDED("일시정지");

    private final String displayName;
}
