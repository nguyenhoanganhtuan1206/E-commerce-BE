package com.ecommerce.persistent.payment_order;

import com.ecommerce.domain.delivery_status.DeliveryStatus;
import com.ecommerce.domain.payment_status.PaymentStatus;
import com.ecommerce.persistent.cart.CartEntity;
import com.ecommerce.persistent.payment_method.PaymentMethodEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payment_order")
@Getter
@Setter
@With
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    private String address;

    private String phoneNumber;

    private String emailAddress;

    private String location;

    private long totalPrice;

    private Instant orderedAt;

    private Instant deliveryAt;

    private PaymentStatus paymentStatus;

    private DeliveryStatus deliveryStatus;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    private PaymentMethodEntity paymentMethod;

    @OneToMany(mappedBy = "paymentOrder", cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private List<CartEntity> carts;
}