package com.ecommerce.configuration.dummy_data.common;

import com.ecommerce.persistent.payment_method.PaymentMethodEntity;
import com.ecommerce.persistent.payment_method.PaymentMethodRepository;
import com.ecommerce.persistent.role.RoleEntity;
import com.ecommerce.persistent.role.RoleRepository;
import com.ecommerce.persistent.user.UserEntity;
import com.ecommerce.persistent.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PaymentMethodRepository paymentMethodRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        seedRoleAndUserData();
        seedPaymentMethod();
    }

    private void seedRoleAndUserData() {
        /**
         * @ Add about User and Role
         * */

        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            roleRepository.save(new RoleEntity("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            roleRepository.save(new RoleEntity("ROLE_USER"));
        }

        if (roleRepository.findByName("ROLE_SELLER").isEmpty()) {
            roleRepository.save(new RoleEntity("ROLE_SELLER"));
        }

        final RoleEntity roleAdmin = roleRepository.findByName("ROLE_ADMIN").orElse(null);

        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            final UserEntity userEntity = new UserEntity();
            userEntity.setEmail("admin@gmail.com");
            userEntity.setUsername("Admin");
            userEntity.setPassword(passwordEncoder.encode("123456"));
            userEntity.setRoles(Collections.singleton(roleAdmin));
            userEntity.setCreatedAt(Instant.now());
            userRepository.save(userEntity);
        }
    }

    public void seedPaymentMethod() {
        /**
         * @ Add about payment method
         * */

        if (paymentMethodRepository.findByNameIgnoreCase("COD").isEmpty()) {
            paymentMethodRepository.save(new PaymentMethodEntity("COD"));
        }

        if (paymentMethodRepository.findByNameIgnoreCase("Paypal").isEmpty()) {
            paymentMethodRepository.save(new PaymentMethodEntity("Paypal"));
        }
    }
}