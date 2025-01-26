package com.project.LearningNavigator.Controller;

import com.project.LearningNavigator.controller.SubjectController;
import com.project.LearningNavigator.service.SubjectService;
import com.project.LearningNavigator.entity.Subject;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private SubjectService subjectService;

    @Test
    public void testGetSubjectById() throws Exception{

        Subject subject=new Subject();
        subject.setSubjectId(1L);
        subject.setSubjectName("Mathematics");

        when(subjectService.getSubjectById(1L)).thenReturn(subject);

        mockMvc.perform(get("/subjects/1")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.subjectId").value(1))
               .andExpect(jsonPath("$.subjectName").value("Mathematics"));

    }
}
