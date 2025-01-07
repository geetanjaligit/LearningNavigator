package com.project.LearningNavigator.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
public class Subject {
    
    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "subject_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long subjectId;
    private String subjectName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_subject_map",
        joinColumns = @JoinColumn(
                name = "subject_id",
                referencedColumnName = "subjectId"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "student_id",
                referencedColumnName = "studentId" 
        )
    )
    private List<Student> enrolledStudents;
   

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private List<Exam> exams;
}
