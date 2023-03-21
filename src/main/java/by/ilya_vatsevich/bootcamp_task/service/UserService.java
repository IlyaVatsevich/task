package by.ilya_vatsevich.bootcamp_task.service;

import by.ilya_vatsevich.bootcamp_task.annotation.ValidPageable;
import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface UserService {

    Long saveUser(@Valid UserRequestDto userRequestDto);

    Page<UserResponseDto> getAllUsers(@Valid @ValidPageable Pageable pageable);

}
