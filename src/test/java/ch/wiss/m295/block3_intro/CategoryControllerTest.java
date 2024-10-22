package ch.wiss.m295.block3_intro;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.wiss.m295.block3_intro.controller.CategoryController;
import ch.wiss.m295.block3_intro.model.Category;
import ch.wiss.m295.block3_intro.repositories.AnswerRepository;
import ch.wiss.m295.block3_intro.repositories.CategoryRepository;
import ch.wiss.m295.block3_intro.repositories.QuestionRepository;



@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CategoryController.class) // Only load the CategoryController
@AutoConfigureMockMvc
public class CategoryControllerTest {

    @MockBean
    private QuestionRepository questionRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private AnswerRepository answerRepository;

    @Autowired
    private CategoryController categoryController; // SUT - System Under Test

    @Autowired
    private MockMvc mockMvc; // Used for making requests to your controllers

    @Test
    public void whenCategoryControllerInjected_thenNotNull() throws Exception {
        assertThat(categoryController).isNotNull(); // Check if SUT is loaded properly
    }
    @Test
    public void whenPostRequestToCategoryAndValidCategory() throws Exception {
        Category requestCategory = new Category();
        requestCategory.setName("Test Category");
        String requestJson = new ObjectMapper().writeValueAsString(requestCategory);
        mockMvc.perform(MockMvcRequestBuilders.post("/category/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(requestJson))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Test Category"));
        
    }


    @Test
    public void whenPostRequestToCategoryAndInvalidCategory_thenCorrectResponse() throws Exception {
        // Invalid POST request with missing required data
        mockMvc.perform(MockMvcRequestBuilders.post("/category")
                .contentType(MediaType.APPLICATION_JSON)) // Specify JSON content type
                .andExpect(status().is4xxClientError()); // Expect HTTP 400 Bad Request
                //.andExpect(MockMvcResultMatchers.jsonPath("$.error").isNotEmpty()) // Expect error message
                //.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetAllCategories_getValidCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(res -> System.out.println(res.getResponse().getContentAsString())) // Print response
                .andExpect(status().isOk()) // Expect HTTP 200 OK
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON)); // Expect JSON content type
    }
}
