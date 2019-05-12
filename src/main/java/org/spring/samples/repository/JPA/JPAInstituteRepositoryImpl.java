package org.spring.samples.repository.JPA;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class JPAInstituteRepositoryImpl implements InstituteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Faculty findFacultyById(int id) throws DataAccessException {
        Query query = this.em.createQuery("FROM Faculty WHERE Faculty.id =:id");
        query.setParameter("id", id);
        return (Faculty) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Faculty> getAllFaculties() throws DataAccessException {
        Query query = this.em.createQuery("FROM Faculty");
        return query.getResultList();
    }

    //TODO: проверить запрос
    @Override
    public Cathedra findCathedraById(int facultyId, int cathedraId) throws DataAccessException {
        Query query = this.em.createQuery("SELECT f FROM Faculty f left join fetch f.cathedras WHERE Faculty.id =:fid and Cathedra.id =:cid");
        query.setParameter("fid", facultyId).setParameter("cid", cathedraId);
        return (Cathedra) query.getSingleResult();
    }

    //TODO: проверить запрос
    @Override
    @SuppressWarnings("unchecked")
    public Collection<Cathedra> getAllCathedras(int facultyId) throws DataAccessException {
        Query query = this.em.createQuery("SELECT f FROM Faculty f left join fetch f.cathedras WHERE Faculty.id =:id");
        query.setParameter("id", facultyId);
        return query.getResultList();
    }
}