package org.spring.samples.repository.JPA;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * JPA implementation of the {@link EmployeeRepository} interface.
 **/

@Repository
public class JPAEmployeeRepositoryImpl implements EmployeeRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Employee findById(int id) throws DataAccessException {
    Query query = this.em.createQuery("from Employee as e where e.id =:id");
    query.setParameter("id", id);
    return (Employee) query.getSingleResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Employee> getAllEmployees() throws DataAccessException {
    Query query = this.em.createQuery("from Employee");
    return query.getResultList();
  }

  @SuppressWarnings("unchecked")
  private Collection<Employee> getList(Query query, int Id) throws DataAccessException {
    query.setParameter(1, Id);
    Collection<Employee> list_of_employeers = new ArrayList<>();
    List<Integer> list_of_employeersId = query.getResultList();
    for (int id : list_of_employeersId) {
      query = this.em.createQuery("from Employee as e where e.id =:id");
      query.setParameter("id", id);
      list_of_employeers.add((Employee) query.getSingleResult());
    }
    return list_of_employeers;
  }

  //TODO: find more best solution
  @Override
  public Collection<Employee> getFacultyEmployees(int facultyId) {
    Query query = this.em.createNativeQuery("SELECT employee_id FROM facultyWorker WHERE faculty_id = ?");
    return getList(query, facultyId);
  }

  @Override
  public Collection<Employee> getFacultySoviet(int facultyId) {
    Query query = this.em.createNativeQuery("SELECT employee_id FROM facultySoviet WHERE faculty_id = ?");
    return getList(query, facultyId);
  }

  @Override
  public Collection<Employee> getCathedraLecturers(int cathedraId) {
    Query query = this.em.createNativeQuery("SELECT employee_id FROM cathedraLectures WHERE cathedra_id = ?");
    return getList(query, cathedraId);
  }
}
