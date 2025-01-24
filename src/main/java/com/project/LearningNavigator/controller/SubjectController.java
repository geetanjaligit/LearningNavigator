package com.project.LearningNavigator.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.LearningNavigator.entity.Exam;
import com.project.LearningNavigator.entity.Subject;
import com.project.LearningNavigator.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    //Add a subject
    @PostMapping
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject){
        Subject newSubject=subjectService.createSubject(subject);
        return ResponseEntity.ok(newSubject);
    }

    //Get a subject by id
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getById(@PathVariable Long id){
        Subject subject=subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    //Get all the subjects
    @GetMapping
    public ResponseEntity<List<Subject>> getAll(){
        List<Subject> subjects= subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    //update subject
    @PutMapping("/{id}")
    public ResponseEntity<Subject> update(@PathVariable Long id,@RequestBody Subject subject){

        Subject updatedSubject=subjectService.updateSubject(id,subject);
        return ResponseEntity.ok(updatedSubject);
    }

    //Delete a subject
    @DeleteMapping("/Id")
    public ResponseEntity<?> delete(@PathVariable Long Id){
        subjectService.deleteSubject(Id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Assign exam to a subject
    @PostMapping("/{examId}/assign-exam/{subId}")
    public ResponseEntity<Subject> assignExamToSubject(@PathVariable Long examId, @PathVariable Long subId)
    {
        Subject updateSubject=subjectService.assignExamToSubject(examId, subId);
        return ResponseEntity.ok(updateSubject);
    }

    //Retrieve all exams for a subject
    @GetMapping("/{id}/exams")
    public ResponseEntity<List<Exam>> retrievExamList(@PathVariable Long id){
        List<Exam> exams= subjectService.getExamBySubjectId(id);
        return ResponseEntity.ok(exams);
    }

}
