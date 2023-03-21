package by.ilya_vatsevich.bootcamp_task.config;

import by.ilya_vatsevich.bootcamp_task.dto.MultiplyErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MultiplyErrorResponse handle(ConstraintViolationException e) {

        final Map<String,String> errorMap = new HashMap<>();

        e.getConstraintViolations().forEach(constraintViolation ->
                constraintViolation.getPropertyPath()
                        .forEach(node -> errorMap.put(constraintViolation.getMessage(), node.getName())));

        final List<Map<String, String>> errors = errorMap
                .entrySet()
                .stream()
                .map(entryString -> Map.of("error", entryString.getKey(),"field_name", entryString.getValue()))
                .collect(Collectors.toUnmodifiableList());

        return MultiplyErrorResponse.builder()
                .withHttpStatus(HttpStatus.BAD_REQUEST)
                .withMessages(errors)
                .withTimestamp(LocalDateTime.now())
                .build();
    }


}
