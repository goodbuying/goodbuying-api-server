package goodbuyning.api_server.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 정보를 저장하는 Entity
 * 고객이 상품을 주문한 정보와 배송지 정보를 관리합니다.
 */
@Entity
@Table(name = "`order`")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {

    /**
     * 주문 고유 ID (Primary Key)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 연결된 고객 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * 연결된 마켓 정보
     * N:1 관계로 연결됩니다.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "market_id", nullable = false)
    private Market market;

    /**
     * 주문번호 (Unique)
     * 고객에게 제공되는 고유한 주문 식별번호입니다.
     */
    @Column(name = "order_number", unique = true, nullable = false, length = 50)
    private String orderNumber;

    /**
     * 총 주문 금액
     * 상품 가격 + 배송비의 합계입니다.
     */
    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    /**
     * 주문 상태
     * PENDING: 대기중, CONFIRMED: 확인됨, SHIPPED: 배송중, 
     * DELIVERED: 배송완료, CANCELLED: 취소됨
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    /**
     * 결제 상태
     * 주문의 결제 진행 상황을 나타냅니다.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    /**
     * 결제 방법
     * BANK_TRANSFER: 계좌이체, CREDIT_CARD: 신용카드 등
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    /**
     * 배송 수령인 이름
     */
    @Column(name = "shipping_receiver_name", nullable = false, length = 50)
    private String shippingReceiverName;

    /**
     * 배송 연락처
     */
    @Column(name = "shipping_phone", nullable = false, length = 20)
    private String shippingPhone;

    /**
     * 배송지 주소
     */
    @Column(name = "shipping_address", nullable = false, length = 200)
    private String shippingAddress;

    /**
     * 배송지 상세 주소
     */
    @Column(name = "shipping_address_detail", nullable = false, length = 200)
    private String shippingAddressDetail;

    /**
     * 우편번호
     */
    @Column(name = "shipping_postal_code", nullable = false, length = 10)
    private String shippingPostalCode;

    /**
     * 판매자 메모
     * 판매자가 주문에 대해 남기는 메모입니다.
     */
    @Column(name = "seller_memo", length = 500)
    private String sellerMemo;

    /**
     * 주문 상품 목록
     * 1:N 관계로 연결됩니다.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * 결제 정보
     * 1:1 관계로 연결됩니다.
     */
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    /**
     * 주문 이미지 목록 (입금 증빙 등)
     * 1:N 관계로 연결됩니다.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderImage> orderImages = new ArrayList<>();

    /**
     * 주문 상태 변경 이력
     * 1:N 관계로 연결됩니다.
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OrderHistory> orderHistories = new ArrayList<>();

    /**
     * 주문 상태를 변경합니다.
     */
    public void changeOrderStatus(OrderStatus newStatus, ChangedBy changedBy) {
        OrderStatus previousStatus = this.orderStatus;
        this.orderStatus = newStatus;
        
        // 주문 이력 추가
        OrderHistory history = OrderHistory.builder()
                .order(this)
                .orderStatus(newStatus)
                .previousStatus(previousStatus)
                .changedBy(changedBy)
                .build();
        this.orderHistories.add(history);
    }

    /**
     * 결제 상태를 변경합니다.
     */
    public void changePaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * 배송 정보를 업데이트합니다.
     */
    public void updateShippingInfo(String receiverName, String phone, String address, 
                                 String addressDetail, String postalCode) {
        this.shippingReceiverName = receiverName;
        this.shippingPhone = phone;
        this.shippingAddress = address;
        this.shippingAddressDetail = addressDetail;
        this.shippingPostalCode = postalCode;
    }

    /**
     * 판매자 메모를 업데이트합니다.
     */
    public void updateSellerMemo(String sellerMemo) {
        this.sellerMemo = sellerMemo;
    }

    /**
     * 주문이 취소 가능한 상태인지 확인합니다.
     */
    public boolean isCancellable() {
        return orderStatus == OrderStatus.PENDING || orderStatus == OrderStatus.CONFIRMED;
    }
}
