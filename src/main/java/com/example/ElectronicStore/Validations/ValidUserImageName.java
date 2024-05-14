package com.example.ElectronicStore.Validations;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserImageNameValidator.class)
public @interface ValidUserImageName {

    String message() default "Invalid user image name";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
