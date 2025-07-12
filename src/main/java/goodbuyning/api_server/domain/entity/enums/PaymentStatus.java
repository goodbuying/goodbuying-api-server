package goodbuyning.api_server.domain.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 결제 상태를 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    PENDING("pending", "결제 대기"),
    PROOF_SUBMITTED("proof_submitted", "입금 증빙 제출"),
    PAYMENT_CONFIRMED("payment_confirmed", "입금 확인"),
    PAYMENT_REJECTED("payment_rejected", "입금 거절"),
    SHIPPING_PREPARED("shipping_prepared", "배송 준비"),
    FAILED_BY_SELLER("failed_by_seller", "판매자 사유로 실패"),
    FAILED_BY_CUSTOMER("failed_by_customer", "구매자 사유로 실패");

    private final String code;
    private final String displayName;
}
