package edu.uniquindio.taskflow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TaskControllerTest.TestApplication.class)
@AutoConfigureMockMvc
class TaskControllerTest {

    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan(basePackages = "edu.uniquindio.taskflow")
    static class TestApplication {
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthReportsTheApprovedBaselineVersion() throws Exception {
        mockMvc.perform(get("/api/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.version").value("1.5.0"));
    }

    @Test
    void createsAndListsTasksWithoutDeadlineInBaseline() throws Exception {
        mockMvc.perform(post("/api/tasks")
                        .contentType("application/json")
                        .content("{\"title\":\"Preparar línea base\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Preparar línea base"));

        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Preparar línea base"));
    }

    @Test
    void createsTaskWithOptionalDueDate() throws Exception {
        mockMvc.perform(post("/api/tasks")
                        .contentType("application/json")
                        .content("{\"title\":\"Preparar release\",\"dueDate\":\"2026-09-15\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Preparar release"))
                .andExpect(jsonPath("$.dueDate").value("2026-09-15"));
    }
}
