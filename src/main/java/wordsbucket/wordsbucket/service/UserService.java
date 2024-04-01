package wordsbucket.wordsbucket.service;

import wordsbucket.wordsbucket.dto.UserRegistrationRequestDto;
import wordsbucket.wordsbucket.dto.UserResponseDto;
import wordsbucket.wordsbucket.exception.RegistrationException;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationRequestDto userRequestDto)
            throws RegistrationException;
}
