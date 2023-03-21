package by.ilya_vatsevich.bootcamp_task.repository;

import by.ilya_vatsevich.bootcamp_task.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaSystemException;

import static by.ilya_vatsevich.bootcamp_task.util.UserGenerator.createUser;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(properties = "spring.profiles.active = test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void saveUserShouldSaveUser() {
        User validUser = createUser();
        assertDoesNotThrow(()->userRepository.save(validUser));
    }

    @Test
    void saveUserShouldThrowCuzOfInvalidEmail() {
        User userWithInvalidEmail = createUser();
        userWithInvalidEmail.setEmail("user1.com");
        assertThrows(JpaSystemException.class,()->userRepository.save(userWithInvalidEmail));
    }

    @Test
    void saveUserShouldThrowCuzOfFirstNameContainsNonLatinCharacters() {
        User userWithNonLatinCharactersInFirstName = createUser();
        userWithNonLatinCharactersInFirstName.setFirstName("Илья");
        assertThrows(JpaSystemException.class,()->userRepository.save(userWithNonLatinCharactersInFirstName));
    }

}
