package by.ilya_vatsevich.bootcamp_task.service_unit;

import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.dto.UserResponseDto;
import by.ilya_vatsevich.bootcamp_task.entity.User;
import by.ilya_vatsevich.bootcamp_task.mapper.UserMapper;
import by.ilya_vatsevich.bootcamp_task.repository.UserRepository;
import by.ilya_vatsevich.bootcamp_task.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static by.ilya_vatsevich.bootcamp_task.util.UserGenerator.createUser;
import static by.ilya_vatsevich.bootcamp_task.util.UserGenerator.createUserRequestDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    private User user;

    private UserRequestDto userRequestDto;

    private List<User> users;

    @BeforeEach
    void setup() {
        user = createUser();
        user.setId(1L);
        userRequestDto = createUserRequestDto().build();
        users = List.of(user);
    }

    @Test
    void saveUserShouldSaveUser() {
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapToEntity(userRequestDto)).thenReturn(user);
        assertEquals(1L,userService.saveUser(userRequestDto));
    }

    @Test
    void getUserShouldReturnUsers() {
        Page<User> userPage = new PageImpl<>(users);
        when(userRepository.findAll(isA(Pageable.class))).thenReturn(userPage);
        Page<UserResponseDto> allUsers = userService.getAllUsers(Pageable.ofSize(1));
        assertNotNull(allUsers);
        assertEquals(1,allUsers.getSize());
    }

}
