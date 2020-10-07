package org.spring.samples.repository.JDBC.Extractor;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Employee;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.spring.samples.util.EntityUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class FacultyExtractor implements ResultSetExtractor<List<Faculty>> {

  @Override
  public List<Faculty> extractData(ResultSet rs) throws SQLException, DataAccessException {
    List<Faculty> facultyList = new ArrayList<>();
    Map<Integer, List<Cathedra>> facultyData = new LinkedHashMap<>();
    Map<Integer, List<Group_class>> cathedraData = new LinkedHashMap<>();
    Map<Integer, List<Student>> groupData = new LinkedHashMap<>();
    Faculty faculty = null;
    Cathedra cathedra = null;
    Group_class group_class = null;
    while (rs.next()) {
      int facID = rs.getInt("facultyID");
      if (!facultyData.containsKey(facID)) {
        facultyData.put(facID, new ArrayList<>());
        faculty = new Faculty();
        faculty.setId(facID);
        faculty.setTitle(rs.getString("facultyTitle"));
        Employee facultyBoss = new Employee();
        facultyBoss.setId(rs.getInt("facultyBossID"));
        facultyBoss.setFio(rs.getString("facultyBossFio"));
        facultyBoss.setPosition(rs.getString("facultyBossPosition"));
        facultyBoss.setDegree(rs.getString("facultyBossDegree"));
        facultyBoss.setFaculty(faculty);
        faculty.setBoss(facultyBoss);
        faculty.setInformation(rs.getString("facultyInfo"));
        faculty.setContact_inf(rs.getString("facultyContInf"));
        facultyList.add(faculty);
      }
      int catID = rs.getInt("cathedraID");
      if (!cathedraData.containsKey(catID)) {
        cathedraData.put(catID, new ArrayList<>());
        cathedra = new Cathedra();
        cathedra.setId(catID);
        cathedra.setTitle(rs.getString("cathedraTitle"));
        Employee cathedraBoss = new Employee();
        cathedraBoss.setId(rs.getInt("cathedraBossID"));
        cathedraBoss.setFio(rs.getString("cathedraBossFio"));
        cathedraBoss.setPosition(rs.getString("cathedraBossPosition"));
        cathedraBoss.setDegree(rs.getString("cathedraBossDegree"));
        cathedraBoss.setFaculty(faculty);
        cathedraBoss.setCathedra(cathedra);
        cathedra.setBoss(cathedraBoss);
        cathedra.setInformation(rs.getString("cathedraInfo"));
        cathedra.setContact_inf(rs.getString("cathedraContInf"));
        cathedra.setEduPrograms(rs.getString("edu_programs"));
        cathedra.setFaculty(faculty);
        facultyData.get(facID).add(cathedra);
      }
      int grpID = rs.getInt("groupID");
      if (!groupData.containsKey(grpID)) {
        groupData.put(grpID, new ArrayList<>());
        group_class = new Group_class();
        group_class.setId(grpID);
        group_class.setTitle(rs.getString("groupTitle"));
        group_class.setEduForm(rs.getString("edu_form"));
        group_class.setCathedra(cathedra);
        cathedraData.get(catID).add(group_class);
      }
      Student student = new Student();
      student.setId(rs.getInt("studentID"));
      student.setFio(rs.getString("fio"));
      student.setBirthday(rs.getObject("birthday", LocalDate.class));
      student.setSex(rs.getString("sex"));
      student.setFact_address(rs.getString("fact_address"));
      student.setAddress(rs.getString("address"));
      student.setTelephone(rs.getString("telephone"));
      student.setGroup_class(group_class);
      student.setCathedra(cathedra);
      student.setFaculty(faculty);
      groupData.get(grpID).add(student);
    }

    if (EntityUtils.isValidCollection(facultyList)) {
      for (Faculty fac : facultyList) {
        List<Cathedra> cathedraList = facultyData.get(fac.getId());
        if (EntityUtils.isValidCollection(cathedraList)) {
          for (Cathedra cat : cathedraList) {
            List<Group_class> group_classList = cathedraData.get(cat.getId());
            if (EntityUtils.isValidCollection(group_classList)) {
              for (Group_class grp : group_classList) {
                grp.setGroup_students(new HashSet<>(groupData.get(grp.getId())));
              }
              cat.setGroup_classes(new HashSet<>(group_classList));
            }
          }
          fac.setCathedras(new HashSet<>(cathedraList));
        }
      }
    }
    return facultyList;
  }
}
