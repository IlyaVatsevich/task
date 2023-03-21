package by.ilya_vatsevich.bootcamp_task.util;

import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.entity.User;

import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public final class UserGenerator {

    private UserGenerator() {}

    public static User createUser() {
        return User.builder().withEmail("user" +  ThreadLocalRandom.current().nextInt(10_000) + "@yopmail.com")
                .withUserRoles(Set.of(User.UserRole.ADMINISTRATOR))
                .withFirstName("Ilya")
                .withLastName("Vatsevich")
                .withPatronymic("Olegovich")
                .build();
    }

    public static UserRequestDto.UserRequestDtoBuilder createUserRequestDto() {
        return UserRequestDto.builder().withEmail("user1@yopmail.com")
                .withRoles(Set.of(User.UserRole.ADMINISTRATOR.getIdName()))
                .withPatronymic("Olegovich")
                .withFirstName("Ilya")
                .withLastName("Vatsevich");
    }

}
