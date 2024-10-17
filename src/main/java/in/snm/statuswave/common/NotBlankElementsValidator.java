package in.snm.statuswave.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

import in.snm.statuswave.custom.NotBlankElements;

public class NotBlankElementsValidator implements ConstraintValidator<NotBlankElements, List<String>> {

    @Override
    public boolean isValid(List<String> list, ConstraintValidatorContext context) {
        if (list == null || list.size() < 2) {
            return false;
        }
        // Check each element in the list to ensure it's not blank
        for (String item : list) {
            if (item == null || item.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

