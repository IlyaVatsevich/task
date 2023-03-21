package by.ilya_vatsevich.bootcamp_task.service;

import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.entity.User;
import by.ilya_vatsevich.bootcamp_task.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import javax.validation.ValidationException;

import java.util.List;

import static by.ilya_vatsevich.bootcamp_task.util.UserGenerator.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "spring.profiles.active=test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUserShouldSave() {
        UserRequestDto validUser = createUserRequestDto().build();
        Long userId = assertDoesNotThrow(() -> userService.saveUser(validUser));
        assertNotNull(userId);
    }

    @Test
    void testSaveUserShouldThrowCuzOfInvalidEmail() {
        UserRequestDto userWithInvalidEmail = createUserRequestDto().withEmail("asd123@.com").build();
        assertThrows(ValidationException.class,()->userService.saveUser(userWithInvalidEmail));
    }

    @Test
    void testSaveUserShouldThrowCuzOfInvalidFirstName() {
        UserRequestDto userWithInvalidFirstName = createUserRequestDto().withFirstName("Илья").build();
        assertThrows(ValidationException.class,()->userService.saveUser(userWithInvalidFirstName));
    }

    @Test
    void testSaveUserShouldThrowCuzOfNullValue() {
        UserRequestDto userRequestDtoWithNullValue = createUserRequestDto().withLastName(null).build();
        assertThrows(ValidationException.class,()->userService.saveUser(userRequestDtoWithNullValue));
    }

    @Test
    void testGetUserShouldReturnAllUsers() {
        List<User> users = List.of(createUser(), createUser(), createUser(), createUser(), createUser());
        userRepository.saveAll(users);
        assertEquals(5,userService.getAllUsers(Pageable.ofSize(5)).getSize());
    }

}
