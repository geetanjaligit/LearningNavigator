package com.project.LearningNavigator.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
public class Exam {
    
   @Id
    @SequenceGenerator(
            name = "exam_sequence",
            sequenceName = "exam_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "exam_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long examId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_exam_map",
        joinColumns = @JoinColumn(
                name = "exam_id",
                referencedColumnName = "examId"
        ),
        inverseJoinColumns = @JoinColumn(
                name = "student_id",
                referencedColumnName = "studentId" 
        )
    )
    private List<Student> enrolledStudents;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(
        name = "subject_id",
        referencedColumnName = "subjectId" 
        )
    private Subject subject;
}