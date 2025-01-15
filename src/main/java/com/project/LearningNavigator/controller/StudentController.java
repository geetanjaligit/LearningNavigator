package com.project.LearningNavigator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.project.LearningNavigator.entity.Student;
import com.project.LearningNavigator.entity.Subject;
import com.project.LearningNavigator.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    //get all the students
    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students=studentService.getAllStudents();
        return ResponseEntity.ok(students);

    }

    //get student by id
    @GetMapping("/{Id}")
    public ResponseEntity<?> getById(@PathVariable Long Id){

        Student student=studentService.getStudentById(Id);
        return ResponseEntity.ok(student);
    }

    //add new student
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        Student newStudent=studentService.createStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    //update student
    @PutMapping("/{Id}")
    public ResponseEntity<?> update(@PathVariable Long Id, @RequestBody Student student){
        Student updatedStudent=studentService.updateStudent(Id,student);
        return ResponseEntity.ok(updatedStudent);
    }

    //delete a student
    @DeleteMapping("/{Id}")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        studentService.deleteStudent(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Enroll a student to a subject
    @PostMapping("/{studentId}/enroll/{subjectId}")
    public ResponseEntity<?> enrollStudents(@PathVariable Long studentId,@PathVariable Long subjectId){
        Subject enrollStudents=studentService.enrollStudentToSubject(studentId, subjectId);
        return ResponseEntity.ok(enrollStudents);
    }
    
}
