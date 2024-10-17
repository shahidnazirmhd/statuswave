package in.snm.statuswave.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import in.snm.statuswave.common.NotBlankElementsValidator;

@Constraint(validatedBy = NotBlankElementsValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotBlankElements {
    String message() default "List contains blank elements";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

