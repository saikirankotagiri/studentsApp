package com.restcrud.studentsApp.DAO;

import com.restcrud.studentsApp.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAO {

    EntityManager entityManager;

    @Autowired
    public StudentDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Student> findAll(){
        TypedQuery<Student> theQuery = entityManager.createQuery("from Student", Student.class);
        List<Student> students = theQuery.getResultList();
        return students;
    }

    public Student findOne(int theId) {
        return entityManager.find(Student.class, theId);
    }

    // below merge method will save theStudent as new entry if id = 0 else it will update the existing entry with the id.
    public Student  addStudent(Student theStudent){
      Student tempStudent =  entityManager.merge(theStudent);
      return tempStudent;
    }

    public void deleteStudent(int theId){
       Student tempStudent = entityManager.find(Student.class, theId);
       entityManager.remove(tempStudent);
    }
}
