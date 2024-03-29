package com.ecommerce.persistent.user;

import com.ecommerce.persistent.cart.CartEntity;
import com.ecommerce.persistent.location.LocationEntity;
import com.ecommerce.persistent.role.RoleEntity;
import com.ecommerce.persistent.seller.SellerEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;

    private String email;

    private String phoneNumber;

    @Column(name = "created_at")
    private Instant createdAt;

    private Instant updatedAt;

    private String password;

    private boolean sellingEnabled;

    private String codeResetPassword;

    private Instant lastSendResetPasswordAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<LocationEntity> locations;

    @OneToOne(mappedBy = "user", cascade = CascadeType.MERGE)
    private SellerEntity seller;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartEntity> cart;
}
