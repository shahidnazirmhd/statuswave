package in.snm.statuswave.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.snm.statuswave.role.Role;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public User saveUser(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.USER);  
        }
        var buildedUser =
        User.builder()
            .firstname(user.getFirstname())
            .lastname(user.getLastname())
            .companyName(user.getCompanyName())
            .profileURL(user.getProfileURL())
            .email(user.getEmail())
            .password(passwordEncoder.encode(user.getPassword()))
            .role(user.getRole())
            .build();
        return userRepository.save(buildedUser);
    }

    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
