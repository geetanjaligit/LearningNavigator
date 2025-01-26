# Learning Navigator Application

**Learning Navigator** is a RESTful API service using Spring Boot to manage the exam enrollment process for a Learning Management System (LMS) using Mysql.

## Table of Contents
1. [Features](#features)
2. [Tech Stack](#tech-stack)
3. [Setup Instructions](#setup-instructions)
4. [Project Structure](#project-structure)
5. [Example Usage](#example-usage)
6. [Testing](#testing)

## Features
- **CRUD Operations**: Create, Read, Update, and Delete operations for:
    - Students
    - Subjects
    - Exams
- **Student Enrollment**: Enroll students in subjects and exams with validations.
- **Subject-Exam Association**: Assign exams to specific subjects.
- **Relationships Management**:
    - Many-to-Many: Students and Subjects
    - Many-to-One: Exams and Subjects
- **Hidden Easter Egg Feature**: Discover random facts about numbers using the Numbers API.

## Tech Stack
- **Backend**: Spring Boot
- **Database**: MySQL
- **Testing**: Mockito, MockMvc
- **Integration**: RestTemplate for external API calls (Numbers API)

## Setup Instructions
1. **Clone the Repository**:
    ```bash
    git clone https://github.com/geetanjaligit/LearningNavigator.git
    cd LearningNavigator
    ```

2. **Setup MySQL Database**:
   - Ensure MySQL is installed and running locally on `localhost:3306`.
   - Default database: `lms`.
   - Configure username and password in `src/main/resources/application.properties`.

3. **Configure Application Properties**:
    ```properties
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/lms
    spring.datasource.username=_____
    spring.datasource.password=_____
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.jpa.show-sql=true
    ```

4. **Run the Application**:
    ```bash
    ./mvnw spring-boot:run
    ```

5. **API Endpoints**:
   - **Student Operations**:
     - **Get All Students**: `GET /students`
     - **Get Student by ID**: `GET /students/{studentId}`
     - **Create Student**: `POST /students`
     - **Update Student**: `PUT /students/{studentId}`
     - **Delete Student**: `DELETE /students/{studentId}`
   - **Subject Operations**:
     - **Get All Subjects**: `GET /subjects`
     - **Get Subject by ID**: `GET /subjects/{subjectId}`
     - **Create Subject**: `POST /subjects`
     - **Update Subject**: `PUT /subjects/{subjectId}`
     - **Delete Subject**: `DELETE /subjects/{subjectId}`
     - **Enroll Student in Subject**: `POST /students/{studentId}/enroll/{subjectId}`
   - **Exam Operations**:
     - **Get All Exams**: `GET /exams`
     - **Get Exam by ID**: `GET /exams/{examId}`
     - **Create Exam**: `POST /exams`
     - **Update Exam**: `PUT /exams/{examId}`
     - **Delete Exam**: `DELETE /exams/{examId}`
     - **Assign Exam to Subject**: `POST /subjects/{subjectId}/assign-exam`
     - **Register Student for Exam**: `POST /exams/{examId}/students/{studentId}`
   - **Easter Egg**: `GET /easter-egg`

## Project Structure
- **Controller**: Manages API endpoints.
    - `StudentController.java`
    - `SubjectController.java`
    - `ExamController.java`
    - `EasterEggController.java`
- **Service**: Handles business logic.
    - `StudentService.java`
    - `SubjectService.java`
    - `ExamService.java`
- **Repository**: Interfaces with MySQL.
    - `StudentRepository.java`
    - `SubjectRepository.java`
    - `ExamRepository.java`
- **Entity**: Defines the data model.
    - `Student.java`
    - `Subject.java`
    - `Exam.java`
- **Testing**: Ensures reliability with JUnit, Mockito, and MockMvc.
    - `StudentControllerTest.java`
    - `SubjectControllerTest.java`
    - `ExamControllerTest.java`

## Example Usage
### Create a Student
```bash
POST /students
{
  "studentName": "Anshu"
}
