package org.spring.samples.repository.JPA;

import org.spring.samples.model.Student;
import org.spring.samples.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class JPAStudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Student> getAllStudents() {
        @SuppressWarnings("SyntaxError") Query query = this.em.createQuery("from Student");
        return query.getResultList();
    }

    @Override
    public void save(Student student) {
        if (student.getId() == null) {
            this.em.persist(student);
        } else {
            this.em.merge(student);
        }
    }
}