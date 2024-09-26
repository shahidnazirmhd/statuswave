package in.snm.statuswave.model;

import lombok.Builder;

@Builder
public record AuthFailureResponse(String message, String action) {
    
}
