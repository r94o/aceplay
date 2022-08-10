package tech.makers.aceplay.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.makers.aceplay.session.Session;
import tech.makers.aceplay.session.SessionService;

// https://www.youtube.com/watch?v=5r3QU09v7ig&t=883s
@RestController
public class UsersController {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private SessionService sessionService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @PostMapping("/api/users")
  public Session create(@RequestBody UserDTO userDTO) {
    User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()));
    userRepository.save(user);
    String token = sessionService.generateToken(user.getUsername());
    return new Session(user, token);
  }
}
