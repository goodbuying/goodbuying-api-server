package goodbuyning.api_server.domain.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 주문 상태를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    PENDING("pending", "대기중"),
    CONFIRMED("confirmed", "확인됨"),
    SHIPPED("shipped", "배송중"),
    DELIVERED("delivered", "배송완료"),
    CANCELLED("cancelled", "취소됨");

    private final String code;
    private final String displayName;
}
