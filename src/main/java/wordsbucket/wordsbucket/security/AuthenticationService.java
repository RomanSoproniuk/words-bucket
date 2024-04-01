package wordsbucket.wordsbucket.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import wordsbucket.wordsbucket.dto.UserLoginRequestDto;
import wordsbucket.wordsbucket.dto.UserLoginResponseDto;
import wordsbucket.wordsbucket.model.User;
import wordsbucket.wordsbucket.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public UserLoginResponseDto authenticate(UserLoginRequestDto userLoginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequestDto.email(),
                        userLoginRequestDto.password())
        );
        String token = jwtUtil.generateToken(authentication.getName());
        User thisUser = userRepository.findByEmail(authentication.getName()).get();
        String userEmail = thisUser.getEmail();
        String userName = thisUser.getName();
        return new UserLoginResponseDto(token, userEmail, userName);
    }
}
