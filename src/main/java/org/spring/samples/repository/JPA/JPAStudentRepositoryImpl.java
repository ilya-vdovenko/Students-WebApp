package org.spring.samples.repository.JPA;

import org.spring.samples.model.Student;
import org.spring.samples.repository.StudentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of the {@link StudentRepository} interface.
 **/

@Repository
public class JPAStudentRepositoryImpl implements StudentRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Student> getAllStudents() throws DataAccessException {
    Query query = this.em.createQuery("from Student");
    return query.getResultList();
  }

  @Override
  public void save(Student student) throws DataAccessException {
    if (student.getId() == null) {
      this.em.persist(student);
    } else {
      this.em.merge(student);
    }
  }

  @Override
  public Student findById(int id) throws DataAccessException {
    Query query = this.em.createQuery("from Student as s where s.id =:id");
    query.setParameter("id", id);
    return (Student) query.getSingleResult();
  }
}
