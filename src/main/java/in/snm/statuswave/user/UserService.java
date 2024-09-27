package in.snm.statuswave.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.snm.statuswave.model.UserVerifyRequest;
import in.snm.statuswave.role.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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
        return userRepository.save(buildedUser);
    }

    public UserVerifyRequest sendValidationEmail(User user) {
        return UserVerifyRequest.builder().username(user.getUsername()).build();
    }

    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isProfileNameExist(String profileName) {
        return userRepository.findByProfileName(profileName).isPresent();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
