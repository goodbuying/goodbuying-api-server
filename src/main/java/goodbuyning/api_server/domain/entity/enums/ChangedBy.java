package goodbuyning.api_server.domain.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 주문 상태 변경 주체를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum ChangedBy {
    SYSTEM("system", "시스템"),
    ADMIN("admin", "관리자"),
    CUSTOMER("customer", "고객"),
    SELLER("seller", "판매자");

    private final String code;
    private final String displayName;
}
