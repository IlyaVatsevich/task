package by.ilya_vatsevich.bootcamp_task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(setterPrefix = "with")
@Jacksonized
@Getter
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime timestamp;

}
