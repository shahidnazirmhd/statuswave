package in.snm.statuswave.otp;

import lombok.*;

import in.snm.statuswave.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Otp {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String otp;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime validateAt;

    @ManyToOne
    @JoinColumn(name = "_user_id", nullable = false)
    private User user;
}
