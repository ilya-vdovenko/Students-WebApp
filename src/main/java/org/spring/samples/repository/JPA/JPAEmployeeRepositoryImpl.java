package org.spring.samples.repository.JPA;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class JPAEmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Employee findById(int id) throws DataAccessException {
        Query query = this.em.createQuery("FROM Employee WHERE Employee.id =:id");
        query.setParameter("id", id);
        return (Employee) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<Employee> getAllEmployees() throws DataAccessException {
        Query query = this.em.createQuery("FROM Employee");
        return query.getResultList();
    }
}