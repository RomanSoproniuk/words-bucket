package wordsbucket.wordsbucket.dto;

public record UserLoginResponseDto(String token,
                                   String email,
                                   String name) {
}
