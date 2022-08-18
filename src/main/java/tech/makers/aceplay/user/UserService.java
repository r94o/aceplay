package tech.makers.aceplay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.makers.aceplay.session.Session;
import tech.makers.aceplay.session.SessionService;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Session createUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        String token = sessionService.generateToken(user.getUsername());
        return new Session(user, token);
    }
}
