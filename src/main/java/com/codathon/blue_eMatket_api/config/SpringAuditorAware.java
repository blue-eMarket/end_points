package com.codathon.blue_eMatket_api.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringAuditorAware implements AuditorAware {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("none").filter(s -> !s.isEmpty());
    }
}
