package by.ilya_vatsevich.bootcamp_task.validator;

import by.ilya_vatsevich.bootcamp_task.annotation.ValidPageable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PageableValidator implements ConstraintValidator<ValidPageable, Pageable> {

    private List<String> allowedProperties;

    @Override
    public void initialize(ValidPageable constraintAnnotation) {
        allowedProperties = Arrays.asList(constraintAnnotation.allowedProperties());
    }

    @Override
    public boolean isValid(Pageable value, ConstraintValidatorContext context) {
        if (value==null) {
            return true;
        }

        if (allowedProperties == null || allowedProperties.isEmpty()) {
            return true;
        }

        if (value.isUnpaged()) {
            return true;
        }

        Sort sort = value.getSort();

        if (sort.isUnsorted()) {
            return true;
        }

        List<String> notAllowedProperties = sort.stream()
                .map(Sort.Order::getProperty)
                .filter(property -> !allowedProperties.contains(property))
                .collect(Collectors.toUnmodifiableList());

        return notAllowedProperties.isEmpty();
    }
}
