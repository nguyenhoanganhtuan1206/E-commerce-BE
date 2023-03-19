package com.ecommerce.error;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends DomainException {
    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "");
    }
}
