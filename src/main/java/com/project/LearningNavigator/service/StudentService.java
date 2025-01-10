package com.project.LearningNavigator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LearningNavigator.entity.Student;
import com.project.LearningNavigator.entity.Subject;
import com.project.LearningNavigator.exception.DatabaseOperationException;
import com.project.LearningNavigator.exception.ResourceNotFoundException;
import com.project.LearningNavigator.repository.StudentRepository;
import com.project.LearningNavigator.repository.SubjectRepository;

@Service
public class StudentService {

    @Autowired
    public StudentRepository studentRepository;
    public SubjectRepository subjectRepository;

    //Add a student
    public Student createStudent(Student student){
        try{
            return studentRepository.save(student);
        }
        catch(Exception ex)
        {
            throw new DatabaseOperationException("Error while adding student", ex);
        }
    }

    //Get a student by id
    public Student getStudentById(Long studentId){
        try{
            Optional<Student> studentOptional=studentRepository.findById(studentId);
            if(studentOptional.isPresent())
                return studentOptional.get();
            else
                throw new ResourceNotFoundException("Student not found with id: "+studentId);  
        }
        catch(Exception ex)
        {
            throw new DatabaseOperationException("Error while retrieving student by ID:"+studentId, ex);
        }
    }

    //Get all students
    public List<Student> getAllStudents(){
        try{
            return studentRepository.findAll();
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while retrieving all students", ex);
        }
    }

    //Update a student
    public Student updateStudent(Long studentId, Student updatedStudent){
        try{
        Student existingStudent=getStudentById(studentId);
        existingStudent.setStudentName(updatedStudent.getStudentName());
        return studentRepository.save(existingStudent);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while updating student with id: "+studentId, ex);
        }
    }

    //Delete a student
    public void deleteStudent(Long studentId) {
        try {
            Student student = getStudentById(studentId);
            studentRepository.delete(student);
        }
        catch (Exception ex) {
            throw new DatabaseOperationException("Failed to delete student with ID: " + studentId, ex);
        }
    }


    //Enroll a student to a subject
    public Student enrollStudentToSubject(Long studentId,Long subjectId){
        try{
            Student student=getStudentById(studentId);
            Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("Subject not found with id: "+subjectId));
            student.getEnrolledSubjects().add(subject);
            subject.getEnrolledStudents().add(student);
            return studentRepository.save(student);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while enrolling student with id: "+studentId+" to subject with id: "+subjectId, ex);
        }
    }
}
