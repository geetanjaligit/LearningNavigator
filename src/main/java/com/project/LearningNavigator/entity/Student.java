package com.project.LearningNavigator.entity;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "student_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long StudentId;
    private String StudentName;
     
    @ManyToMany(mappedBy = "enrolledStudents", fetch = FetchType.LAZY)
    private List<Subject> enrolledSubjects;

    @ManyToMany(mappedBy = "enrolledStudents", fetch = FetchType.LAZY)
    private List<Exam> registeredExams;
}
