package wordsbucket.wordsbucket.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
import wordsbucket.wordsbucket.dto.UserRegistrationRequestDto;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch,
        UserRegistrationRequestDto> {
    @Override
    public boolean isValid(UserRegistrationRequestDto value, ConstraintValidatorContext context) {
        return value.getPassword() != null
                && Objects.equals(value.getPassword(), value.getRepeatPassword());
    }
}
