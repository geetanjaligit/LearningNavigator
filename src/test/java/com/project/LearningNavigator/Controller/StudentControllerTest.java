package com.project.LearningNavigator.Controller;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.LearningNavigator.controller.StudentController;
import com.project.LearningNavigator.entity.Student;
import com.project.LearningNavigator.service.StudentService;


@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON

    @Test
    public void testCreateStudent() throws Exception{

        Student student=new Student();
        student.setStudentId(1L);
        student.setStudentName("Anshu");

        Mockito.when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(student);

        String studentJson = objectMapper.writeValueAsString(student);
        mockMvc.perform(post("/students")
              .contentType(MediaType.APPLICATION_JSON)
              .content(studentJson))
        // Assert: Verify the response status and content
        .andExpect(status().isOk()) // Ensure the status is 200 OK
        .andExpect(jsonPath("$.studentId").value(1)) // Verify studentId in the response
        .andExpect(jsonPath("$.studentName").value("Anshu")); // Verify studentName in the response
    }
}
