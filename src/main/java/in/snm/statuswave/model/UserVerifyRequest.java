package in.snm.statuswave.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserVerifyRequest (
    @NotBlank(message = "username is mandatory")
    String username,
    @NotBlank(message = "otp is mandatory") 
    String otp) {}