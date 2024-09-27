package in.snm.statuswave.model;

import lombok.Builder;

@Builder
public record UserVerifyRequest (String username, String otp) {}