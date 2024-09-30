package in.snm.statuswave.user;

import java.util.Optional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isProfileNameExist(String profileName) {
        return userRepository.findByProfileName(profileName).isPresent();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
