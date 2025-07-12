package goodbuyning.api_server.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 결제 방법을 나타내는 Enum
 */
@Getter
@RequiredArgsConstructor
public enum PaymentMethod {
    BANK_TRANSFER("bank_transfer", "계좌이체"),
    CREDIT_CARD("credit_card", "신용카드"),
    VIRTUAL_ACCOUNT("virtual_account", "가상계좌"),
    MOBILE_PAYMENT("mobile_payment", "모바일 결제");

    private final String code;
    private final String displayName;
}
