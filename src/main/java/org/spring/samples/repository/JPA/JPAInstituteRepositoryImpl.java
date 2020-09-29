package org.spring.samples.repository.JPA;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

/**
 * JPA implementation of the {@link InstituteRepository} interface.
 **/

@Repository
public class JPAInstituteRepositoryImpl implements InstituteRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Faculty findFacultyById(int id) {
    Query query = this.em.createQuery("from Faculty as f where f.id =:id");
    query.setParameter("id", id);
    return (Faculty) query.getSingleResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Faculty> getAllFaculties() {
    Query query = this.em.createQuery("from Faculty");
    return query.getResultList();
  }

  @Override
  public Cathedra findCathedraById(int id) {
    Query query = this.em.createQuery("from Cathedra as c where c.id =:id");
    query.setParameter("id", id);
    return (Cathedra) query.getSingleResult();
  }

  @Override
  public Group_class findGroup_classById(int id) {
    Query query = this.em.createQuery("from Group_class as gc where gc.id =:id");
    query.setParameter("id", id);
    return (Group_class) query.getSingleResult();
  }
}
