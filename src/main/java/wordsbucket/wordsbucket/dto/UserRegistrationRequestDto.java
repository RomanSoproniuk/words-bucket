package wordsbucket.wordsbucket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import wordsbucket.wordsbucket.validation.FieldMatch;

@Getter
@Setter
@FieldMatch(password = "password",
        repeatPassword = "repeatPassword",
        message = "Fields password and repeat password must match")
public class UserRegistrationRequestDto {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
    @NotBlank
    @Size(min = 8, max = 20)
    private String repeatPassword;
}
