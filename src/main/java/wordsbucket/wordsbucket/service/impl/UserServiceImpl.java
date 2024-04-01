package wordsbucket.wordsbucket.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import wordsbucket.wordsbucket.dto.UserRegistrationRequestDto;
import wordsbucket.wordsbucket.dto.UserResponseDto;
import wordsbucket.wordsbucket.exception.RegistrationException;
import wordsbucket.wordsbucket.mapper.UserMapper;
import wordsbucket.wordsbucket.model.User;
import wordsbucket.wordsbucket.repository.UserRepository;
import wordsbucket.wordsbucket.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto registerUser(UserRegistrationRequestDto userRequestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Cant register user by email: "
                    + userRequestDto.getEmail() + " since this user is already registered");
        }
        User user = new User();
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }
}
