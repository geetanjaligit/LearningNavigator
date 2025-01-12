package com.project.LearningNavigator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LearningNavigator.entity.Exam;
import com.project.LearningNavigator.entity.Subject;
import com.project.LearningNavigator.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {
    

    @Autowired
    private ExamService examService;

    //Create a new exam
    @PostMapping
    public ResponseEntity<Exam> addExam(@RequestBody Exam exam) {
        Exam createdExam = examService.createExam(exam);
        return ResponseEntity.ok(createdExam);
    }

    //Get an exam by ID
    @GetMapping("/{id}")
    public ResponseEntity<Exam> getById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        return ResponseEntity.ok(exam);
    }

    //Get all exams
    @GetMapping
    public ResponseEntity<List<Exam>> getAll() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    //Update exam details
    @PutMapping("/{id}")
    public ResponseEntity<Exam> update(@PathVariable Long id, @RequestBody Exam updatedExam) {
        Exam exam = examService.updateExam(id, updatedExam);
        return ResponseEntity.ok(exam);
    }

    //Delete an exam
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        examService.deleteExam(id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Register a student for an exam
    @PostMapping("/{examId}/students/{studentId}")
    public ResponseEntity<Exam> registerStudentForExam(@PathVariable Long examId, @PathVariable Long studentId) {
        Exam exam = examService.registerStudentForExam(examId, studentId);
        return ResponseEntity.ok(exam);
    }

    //Retrieve the subject for a specific exam
    @GetMapping("/{examId}/subject")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long examId) {
        Subject subject = examService.getSubjectByExamId(examId);
        return ResponseEntity.ok(subject);
    }
}
