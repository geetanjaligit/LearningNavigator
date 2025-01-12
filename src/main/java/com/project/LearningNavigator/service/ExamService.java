package com.project.LearningNavigator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.LearningNavigator.entity.Exam;
import com.project.LearningNavigator.entity.Student;
import com.project.LearningNavigator.entity.Subject;
import com.project.LearningNavigator.exception.DatabaseOperationException;
import com.project.LearningNavigator.exception.InvalidOperationException;
import com.project.LearningNavigator.exception.ResourceNotFoundException;
import com.project.LearningNavigator.repository.ExamRepository;
import com.project.LearningNavigator.repository.StudentRepository;
import com.project.LearningNavigator.repository.SubjectRepository;
import com.project.LearningNavigator.service.StudentService;

public class ExamService {


    @Autowired
    private StudentService studentService;
    private ExamRepository examRepository;
    public StudentRepository studentRepository;
    public SubjectRepository subjectRepository;

    //Create a new exam
    public Exam createExam(Exam exam){
        try{
            return examRepository.save(exam);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while creating an exam", ex);
        }
    }

    //Get an exam by id
    public Exam getExamById(Long examId){
        try{
            Optional<Exam> examOptional=examRepository.findById(examId);
            if(examOptional.isPresent())
                return examOptional.get();
            else
                throw new ResourceNotFoundException("Exam not found with id: "+examId);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while retrieving exam by ID:"+examId, ex);
        }
    }

    //Get all exams
    public List<Exam> getAllExams(){
        try{
            return examRepository.findAll();
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while retrieving all exams", ex);
        }
    }

    //Update exam details
    public Exam updateExam(Long examId,Exam exam)
    {
        try{
            Exam existingExam=getExamById(examId);
            existingExam.setSubject(exam.getSubject());
            existingExam.setEnrolledStudents(exam.getEnrolledStudents());
            return examRepository.save(existingExam);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while updating exam details", ex);
        }
    }

    //Register student for an exam
    public Exam registerStudentForExam(Long examId,Long studentId){

        try{
            Exam exam=getExamById(examId);
            Student student = studentService.getStudentById(studentId);
            if(!student.getEnrolledSubjects().contains(exam.getSubject())){
                throw new InvalidOperationException("Student is not enrolled for the subject of the exam");
            }
            exam.getEnrolledStudents().add(student);
            return examRepository.save(exam);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while registering student of id "+studentId+ "for exam with Id "+examId, ex);
        }
    }

    //Delete exam by id
    public void deleteExam(Long examId){
        try{
            Exam exam=getExamById(examId);
            examRepository.delete(exam);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while deleting exam with ID: "+examId, ex);
        }
    }

    //Retrieve subject of a specific exam
    public Subject getSubjectByExamId(Long ExamId){
        try{
            Exam exam=getExamById(ExamId);
            return exam.getSubject();
        }
        catch(Exception ex)
        {
            throw new DatabaseOperationException("Error while retrieving subject for exam with id: " + ExamId, ex);
        }
    }

}

