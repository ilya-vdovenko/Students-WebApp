/*
 * Copyright 2019-2020, Ilya Vdovenko and the Students-WebApp contributors.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spring.samples.repository.JDBC.Extractor;

import org.spring.samples.model.Employee;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation mapping data from a {@link ResultSetExtractor} to the
 * corresponding properties of the entity classes.
 *
 * @author Ilya Vdovenko
 */

@Component
public class EmployeeExtractor implements ResultSetExtractor<List<Employee>> {

  private final InstituteRepository instituteRepo;

  @Autowired
  public EmployeeExtractor(@Qualifier("JDBCInstituteRepositoryImpl") InstituteRepository instituteRepo) {
    this.instituteRepo = instituteRepo;
  }

  @Override
  public List<Employee> extractData(ResultSet rs) throws SQLException, DataAccessException {
    List<Employee> employeeList = new ArrayList<>();
    while (rs.next()) {
      Employee employee = new Employee();
      employee.setId(rs.getInt("id"));
      employee.setFio(rs.getString("fio"));
      employee.setPosition(rs.getString("position"));
      employee.setDegree(rs.getString("degree"));
      employee.setFaculty(instituteRepo.findFacultyById(rs.getInt("faculty_id")));
      if (rs.getInt("cathedra_id") != 0) {
        employee.setCathedra(instituteRepo.findCathedraById(rs.getInt("cathedra_id")));
      }
      employeeList.add(employee);
    }
    return employeeList;
  }
}
