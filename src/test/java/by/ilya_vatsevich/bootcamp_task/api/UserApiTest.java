package by.ilya_vatsevich.bootcamp_task.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static by.ilya_vatsevich.bootcamp_task.util.UserGenerator.createUserRequestDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
class UserApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSaveUserShouldSave() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createUserRequestDto().build())))
                .andExpect(status().isCreated());
    }

    @Test
    void testSaveUserShouldThrowCuzOfInvalidEmail() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createUserRequestDto()
                                .withEmail("asd123@.com")
                                .build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveUserShouldThrowCuzOfInvalidFirstName() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createUserRequestDto()
                                .withFirstName("Илья")
                                .build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveUserShouldThrowCuzOfInvalidRole() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToJson(createUserRequestDto()
                                .withRoles(Set.of("Agent"))
                                .build())))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllUsersShouldReturnAllUsers() throws Exception {
        mockMvc.perform(get("/api/v1/users")).andExpect(status().isOk());
    }

    @Test
    void testGetAllUsersShouldThrowCuzOfBadSortProperty() throws Exception {
        mockMvc.perform(get("/api/v1/users")
                        .param("sort","asd,DESC"))
                .andExpect(status().isBadRequest());
    }

    private String objectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
