package com.restcrud.studentsApp.service;

import com.restcrud.studentsApp.DAO.StudentDAO;
import com.restcrud.studentsApp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    StudentDAO studentDAO;

    @Autowired
    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> findAll(){
        return studentDAO.findAll();
    }

    public Student findOne(int theId){
        return studentDAO.findOne(theId);
    }

    @Transactional
    public Student save(Student theStudent){
        return studentDAO.addStudent(theStudent);
    }
    @Transactional
    public void deleteStudent(int theId){
        studentDAO.deleteStudent(theId);
    }
}
