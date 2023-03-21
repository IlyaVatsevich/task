package by.ilya_vatsevich.bootcamp_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Set;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class UserResponseDto {

    private String firstLastPatronymic;
    private String email;
    private Set<String> roles;

}
