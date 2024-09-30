package in.snm.statuswave.user;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.snm.statuswave.email.EmailService;
import in.snm.statuswave.email.EmailTemplateName;
import in.snm.statuswave.model.UserVerifyRequest;
import in.snm.statuswave.otp.Otp;
import in.snm.statuswave.otp.OtpService;
import in.snm.statuswave.role.Role;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final OtpService otpService;
    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER);  
        }
        var buildedUser =
        User.builder()
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .companyName(user.getCompanyName())
            .profileName(user.getProfileName())
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .role(user.getRole())
            .accountLocked(false)
            .enabled(false)
            .build();
        return userService.save(buildedUser);
    }

    public UserVerifyRequest sendValidationEmail(User user) throws MessagingException {
        String otp = otpService.generateOneTimePassword(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getFullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                otp,
                "Account Activation"
        );
        return UserVerifyRequest.builder().username(user.getUsername()).build();
    }

    public String activateAccount(String otp, String subject) throws MessagingException {
        Otp savedOtp = otpService.findByOtp(otp).orElse(null);
        if (savedOtp != null && subject.equals(savedOtp.getUser().getUsername())) {
            if(!LocalDateTime.now().isAfter(savedOtp.getExpiresAt())) {
                return enableUser(savedOtp);
            } else {
                sendValidationEmail(savedOtp.getUser());
                return "Activation OTP expired. A new OTP has been sent";
            }
        } else {
            return "Incorrect OTP";
        }
    }

    @Transactional
    private String enableUser(Otp savedOtp) {
        var user = userService
                .findById(savedOtp.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userService.save(user);
        savedOtp.setValidateAt(LocalDateTime.now());
        otpService.save(savedOtp);
        return "OK";
    }
}
