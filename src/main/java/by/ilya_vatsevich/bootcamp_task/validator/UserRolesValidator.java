package by.ilya_vatsevich.bootcamp_task.validator;

import by.ilya_vatsevich.bootcamp_task.annotation.ValidRoles;
import by.ilya_vatsevich.bootcamp_task.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class UserRolesValidator implements ConstraintValidator<ValidRoles, Set<String>> {

    @Override
    public boolean isValid(Set<String> value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return true;
        }

        Set<String> matchedRoles = Arrays.stream(User.UserRole.values())
                .map(User.UserRole::getIdName)
                .filter(value::contains)
                .collect(Collectors.toUnmodifiableSet());

        return matchedRoles.size() == value.size();
    }

}
