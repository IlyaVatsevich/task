package by.ilya_vatsevich.bootcamp_task.annotation;

import by.ilya_vatsevich.bootcamp_task.validator.PageableValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PageableValidator.class)
public @interface ValidPageable {

    String message() default "You can't sort by this property.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] allowedProperties() default {"email"};

}
