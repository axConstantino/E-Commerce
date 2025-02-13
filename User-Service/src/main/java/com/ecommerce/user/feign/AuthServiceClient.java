package com.ecommerce.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {
    @PostMapping("/auth/tokens/revoke/{userId}")
    void revokeAllTokens(@PathVariable Long userId);
}
