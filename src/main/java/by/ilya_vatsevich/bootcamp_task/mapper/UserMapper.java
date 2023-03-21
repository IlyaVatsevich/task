package by.ilya_vatsevich.bootcamp_task.mapper;

import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.dto.UserResponseDto;
import by.ilya_vatsevich.bootcamp_task.entity.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToEntity(UserRequestDto userRequestDto) {
        return User.builder().withPatronymic(userRequestDto.getPatronymic())
                .withEmail(userRequestDto.getEmail())
                .withLastName(userRequestDto.getLastName())
                .withFirstName(userRequestDto.getFirstName())
                .withUserRoles(mapRoles(userRequestDto.getRoles()))
                .build();
    }

    public UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .withEmail(user.getEmail())
                .withRoles(user.getUserRoles()
                        .stream()
                        .map(User.UserRole::getIdName)
                        .collect(Collectors.toUnmodifiableSet()))
                .withFirstLastPatronymic(user.getLastName() + " " + user.getFirstName() + " " + user.getPatronymic())
                .build();
    }

    private Set<User.UserRole> mapRoles(Set<String> roles) {
        return roles.stream().map(role -> {
            switch (role) {
                case "Administrator":
                    return User.UserRole.ADMINISTRATOR;
                case "Sale user":
                    return User.UserRole.SALE_USER;
                case "Customer User":
                    return User.UserRole.CUSTOMER_USER;
                default: return User.UserRole.SECURE_API_USER;
            }
        }).collect(Collectors.toUnmodifiableSet());
    }
}
