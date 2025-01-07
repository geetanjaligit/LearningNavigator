package com.project.LearningNavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.LearningNavigator.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
