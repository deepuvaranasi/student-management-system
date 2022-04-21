package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
     EntityManager entitymanager;

    public void createStudent(Student student){
        entitymanager.persist(student);
    }


    public void updateStudent(Student student){
        entitymanager.merge(student);
    }


    public Student findStudentById(Long id){
        return entitymanager.find(Student.class, id);

    }

    public List<Student> getAllStudents(){
        return entitymanager.createQuery("SELECT i from Student i", Student.class).getResultList();
    }


    public void deleteStudent(Long id){
        Student studentFound = entitymanager.find(Student.class, id);
        entitymanager.remove(studentFound);
    }

    public List<Student> findByLastname(String lastName){
        String query = "SELECT i FROM Student i WHERE i.lastName = :lastName";
        return entitymanager.createQuery(query, Student.class)
                .setParameter("lastName", lastName).getResultList();
    }




}
