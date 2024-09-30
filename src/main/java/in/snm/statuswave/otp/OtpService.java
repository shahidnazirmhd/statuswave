package in.snm.statuswave.otp;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.snm.statuswave.user.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;

    public String generateOneTimePassword(User user) {
        String generatedCode = generateCode(6);
        var otp = Otp.builder()
                .otp(generatedCode)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
                otpRepository.save(otp);
        return generatedCode;
    }

    private String generateCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    public Otp save(Otp otp) {
        return otpRepository.save(otp);
    }

    public Optional<Otp> findByOtp(String otp) {
        return otpRepository.findByOtp(otp);
    }
}
