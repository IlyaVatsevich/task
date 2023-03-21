package by.ilya_vatsevich.bootcamp_task.mapper;

import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.dto.UserResponseDto;
import by.ilya_vatsevich.bootcamp_task.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToEntity(UserRequestDto userRequestDto) {
        return User.builder().withPatronymic(userRequestDto.getPatronymic())
                .withEmail(userRequestDto.getEmail())
                .withLastName(userRequestDto.getLastName())
                .withFirstName(userRequestDto.getFirstName())
                .withUserRoles(userRequestDto.getRoles()
                        .stream()
                        .map(s -> User.UserRole.valueOf(s.toUpperCase()))
                        .collect(Collectors.toUnmodifiableSet()))
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
}
