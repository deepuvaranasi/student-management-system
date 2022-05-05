package se.iths.service;

import se.iths.entity.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

 @Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public Teacher createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
        return teacher;
    }

    public void updateTeacher(Teacher student) {
        entityManager.merge(student);
    }

     public List<Teacher> getAllTeachers() {
         return entityManager.createQuery("SELECT i from Teacher i", Teacher.class).getResultList();
     }

    public void deleteTeacher(Long id) {
        Teacher foundTeacher = entityManager.find(Teacher.class, id);
        entityManager.remove(foundTeacher);
    }

    public List<Teacher> findTeacherById(String id) {
        String query = "SELECT i FROM Teacher i WHERE i.id = :id";
        return entityManager.createQuery(query, Teacher.class)
                .setParameter("id", id).getResultList();
    }

}
