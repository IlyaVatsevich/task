package by.ilya_vatsevich.bootcamp_task.api;

import by.ilya_vatsevich.bootcamp_task.dto.UserRequestDto;
import by.ilya_vatsevich.bootcamp_task.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/users")
public interface UserApi {

    @PostMapping
    ResponseEntity<Long> createUser(@RequestBody UserRequestDto userRequestDto);

    @GetMapping
    ResponseEntity<Page<UserResponseDto>> getAllUsers(@PageableDefault(sort = "email",direction = Sort.Direction.ASC) Pageable pageable);

}
