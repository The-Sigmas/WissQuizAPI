package ch.wiss.m295.block3_intro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import ch.wiss.m295.block3_intro.controller.QuestionController;
import ch.wiss.m295.block3_intro.repositories.QuestionRepository; 

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = QuestionController.class)
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc; // Autowire MockMvc

    @MockBean
    private QuestionRepository questionRepository; // Mock the repository

    @Test
    public void whenPostRequestToQuestionAndInvalidQuestion_thenCorrectResponse() throws Exception {
        // Create a JSON string with invalid data
        String invalidQuestionJson = "{\"question\": null, \"answers\": []}";

        mockMvc.perform(MockMvcRequestBuilders.post("/questions/")
                .content(invalidQuestionJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()) // Print the response content for debugging
                .andExpect(status().isOk()) // Expect 400 Bad Request
                .andExpect(jsonPath("$.question").value("Question is mandatory")) // Check expected error message
                .andExpect(jsonPath("$.answers").value("Answers should be provided")) // Check another expected error message
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)); // Check content type
    }
}
