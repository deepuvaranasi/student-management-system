package se.iths.service;

import se.iths.entity.Subject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public void updateSubject(Subject subject) {
        entityManager.merge(subject);
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT i from Subject i", Subject.class).getResultList();
    }

    public void deleteSubject(Long id) {
        Subject subjectFound = entityManager.find(Subject.class, id);
        entityManager.remove(subjectFound);
    }

    public List<Subject> findBySubjectName(String name) {
        String query = "SELECT i FROM Subject i WHERE i.name = :name";
        return entityManager.createQuery(query, Subject.class)
                .setParameter("name", name).getResultList();
    }

    public Subject findBySubjectId(Long id) {
        return entityManager.find(Subject.class, id);
    }

}
