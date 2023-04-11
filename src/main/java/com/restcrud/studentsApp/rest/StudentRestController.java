package com.restcrud.studentsApp.rest;

import com.restcrud.studentsApp.DAO.StudentDAO;
import com.restcrud.studentsApp.entity.Student;
import com.restcrud.studentsApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    StudentService studentService;

    @Autowired
    public StudentRestController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.findAll();
    }

    @GetMapping("/students/{studentId}")
    public Student getSingleStudent(@PathVariable int studentId){
        if(studentId < 0){
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        Student tempStudent = studentService.findOne(studentId);
        if(tempStudent == null){
            throw new StudentNotFoundException("Student id not found" + studentId);
        }
            return tempStudent;
    }

    @PostMapping("/students")
    public Student save(@RequestBody Student theStudent){
        theStudent.setId(0);
      Student tempStudent =   studentService.save(theStudent);
      return tempStudent;
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student theStudent){
        Student tempStudent =   studentService.save(theStudent);
        return tempStudent;
    }

    @DeleteMapping("/students/{studentId}")
    public String delete(@PathVariable int studentId){
        Student tempStudent = studentService.findOne(studentId);
        if(tempStudent == null){
            throw new StudentNotFoundException("Student id not found" + studentId);
        }
        studentService.deleteStudent(studentId);
        return "Deleted student id is " + studentId;
    }
}
