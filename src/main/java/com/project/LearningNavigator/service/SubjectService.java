package com.project.LearningNavigator.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.LearningNavigator.entity.Subject;
import com.project.LearningNavigator.exception.DatabaseOperationException;
import com.project.LearningNavigator.exception.ResourceNotFoundException;
import com.project.LearningNavigator.repository.SubjectRepository;

public class SubjectService {

    @Autowired
    public SubjectRepository subjectRepository;

    //Add a subject
    public Subject createSubject(Subject subject){
        try{
            return subjectRepository.save(subject);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while adding subject:"+subject.getSubjectName(), ex);
        }
    }

    //Get a subject by id
    public Subject getSubjectById(Long subjectId){

        try{
            Optional<Subject> subjectOptional=subjectRepository.findById(subjectId);
            if(subjectOptional.isPresent())
                return subjectOptional.get();
            else
               throw new ResourceNotFoundException("Subject not found with id: "+subjectId);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while retrieving subject by ID:"+subjectId,ex);
        }
    }

    //Get all the subjects
    public List<Subject> getAllSubjects(){
        try{
            return subjectRepository.findAll();
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Error while retrieving all subjects", ex);
        }
    }

    //Update subject details
    public Subject updateSubject(Long subjectId,Subject subject)
    {
        try{
            Subject existingSubject=getSubjectById(subjectId);
            existingSubject.setSubjectName(subject.getSubjectName());
            return subjectRepository.save(existingSubject);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Failed to update subject with id: "+subjectId, ex);
        }
    }

    //Delete a subject by id
    public void deleteSubject(Long subjectId){
        try{
            Subject existingSubject=getSubjectById(subjectId);
            subjectRepository.delete(existingSubject);
        }
        catch(Exception ex){
            throw new DatabaseOperationException("Failed to delete subject with ID: "+subjectId, ex);
        }
    }

}
