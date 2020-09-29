package org.spring.samples.repository.JPA;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * JPA implementation of the {@link EmployeeRepository} interface.
 **/

@Repository
public class JPAEmployeeRepositoryImpl implements EmployeeRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public Employee findById(int id) {
    Query query = this.em.createQuery("from Employee as e where e.id =:id");
    query.setParameter("id", id);
    return (Employee) query.getSingleResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Collection<Employee> getAllEmployees() {
    Query query = this.em.createQuery("from Employee");
    return query.getResultList();
  }

  @Override
  public Collection<Employee> getFacultyEmployees(Set<Employee> employees, int facultyId) {
    Query query = this.em.createNativeQuery("SELECT employee_id FROM facultyWorker WHERE faculty_id = ?");
    return getList(query, employees, facultyId);
  }

  @Override
  public Collection<Employee> getFacultySoviet(Set<Employee> employees, int facultyId) {
    Query query = this.em.createNativeQuery("SELECT employee_id FROM facultySoviet WHERE faculty_id = ?");
    return getList(query, employees, facultyId);
  }

  @Override
  public Collection<Employee> getCathedraLecturers(Set<Employee> employees, int cathedraId) {
    Query query = this.em.createNativeQuery("SELECT employee_id FROM cathedraLectures WHERE cathedra_id = ?");
    return getList(query, employees, cathedraId);
  }

  @SuppressWarnings("unchecked")
  private Collection<Employee> getList(Query query, Set<Employee> employees, int Id) {
    query.setParameter(1, Id);
    Collection<Employee> list_of_employers = new ArrayList<>();
    List<Integer> list_of_employersId = query.getResultList();
    for (int id : list_of_employersId) {
      for (Employee employee : employees) {
        if (employee.getId().equals(id)) {
          list_of_employers.add(employee);
        }
      }
    }
    return list_of_employers;
  }
}
