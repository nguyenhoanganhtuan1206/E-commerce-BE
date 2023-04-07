package com.ecommerce.domain.location;

import com.ecommerce.domain.user.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class LocationDTO {

    private UUID id;

    private String address;

    private String city;

    private String district;

    private String commune;

    private Instant createdAt;

    private Instant updatedAt;

    private boolean defaultLocation;

    private UserDTO user;
}
