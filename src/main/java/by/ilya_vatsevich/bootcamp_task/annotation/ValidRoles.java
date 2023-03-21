package by.ilya_vatsevich.bootcamp_task.annotation;

import by.ilya_vatsevich.bootcamp_task.validator.UserRolesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserRolesValidator.class)
public @interface ValidRoles {

    String message() default "The selected roles is not allowed. Please select a roles from the following: Administrator, Sale User, Customer User, Secure API User.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
