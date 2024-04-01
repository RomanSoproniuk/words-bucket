package wordsbucket.wordsbucket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wordsbucket.wordsbucket.dto.UserLoginRequestDto;
import wordsbucket.wordsbucket.dto.UserLoginResponseDto;
import wordsbucket.wordsbucket.dto.UserRegistrationRequestDto;
import wordsbucket.wordsbucket.dto.UserResponseDto;
import wordsbucket.wordsbucket.exception.RegistrationException;
import wordsbucket.wordsbucket.security.AuthenticationService;
import wordsbucket.wordsbucket.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginResponseDto loginUser(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        return authenticationService.authenticate(userLoginRequestDto);
    }

    @PostMapping("/registration")
    public UserResponseDto registerUser(@RequestBody UserRegistrationRequestDto userRequestDto)
            throws RegistrationException {
        return userService.registerUser(userRequestDto);
    }
}
