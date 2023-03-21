package by.ilya_vatsevich.bootcamp_task.dto;

import by.ilya_vatsevich.bootcamp_task.annotation.ValidRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class UserRequestDto {

    @NotBlank(message = "last_name field should be filled.")
    @Pattern(regexp = "^[a-zA-Z]+",message = "last_name field should contain only latin characters.")
    @Length(max = 40,message = "last_name length must be less or equal to {max}")
    private String lastName;

    @NotBlank(message = "first_name field should be filled.")
    @Pattern(regexp = "^[a-zA-Z]+",message = "first_name field should contain only latin characters.")
    @Length(max = 20,message = "first_name length must be less or equal to {max}")
    private String firstName;

    @NotBlank(message = "email field should be filled.")
    @Pattern(regexp = "^[A-Za-z][a-z.-_]+@[A-Za-z0-9.-]+[.][A-Za-z]+$")
    @Length(max = 50,message = "email length must be less or equal to {max}")
    private String email;

    @NotBlank(message = "patronymic field should be filled.")
    @Pattern(regexp = "^[a-zA-Z]+",message = "patronymic field should contain only latin characters.")
    @Length(max = 40,message = "patronymic length must be less or equal to {max}")
    private String patronymic;

    @NotNull(message = "last_name field should be filled.")
    @Size(min = 1,message = "You must choose at least {min} role.")
    @ValidRoles
    private Set<String> roles;

}
