package by.ilya_vatsevich.bootcamp_task.service.impl;

import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.dto.UserResponseDto;
import by.ilya_vatsevich.bootcamp_task.entity.User;
import by.ilya_vatsevich.bootcamp_task.mapper.UserMapper;
import by.ilya_vatsevich.bootcamp_task.repository.UserRepository;
import by.ilya_vatsevich.bootcamp_task.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Long saveUser(UserRequestDto userRequestDto) {
        User savedUser = userRepository.save(userMapper.mapToEntity(userRequestDto));
        log.debug("New user with id - {}, is successfully saved.",savedUser.getId());
        return savedUser.getId();
    }

    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::mapToDto);
    }

}
