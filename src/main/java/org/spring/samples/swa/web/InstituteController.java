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

package org.spring.samples.swa.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller that return view's of UnitEntity's pages.
 *
 * @author Ilya Vdovenko
 */

@Controller
@RequestMapping(value = "/faculties")
public class InstituteController {

  private final InstituteService service;

  @Autowired
  public InstituteController(InstituteService is) {
    this.service = is;
  }

  @RequestMapping(value = "/{facultyId}", method = GET)
  public String showFacultyProfile(@PathVariable int facultyId, Model model) {
    model.addAttribute(service.findFacultyById(facultyId));
    return "facultyProfile";
  }

  @RequestMapping(method = GET)
  public String showAllFaculties(Model model) {
    model.addAttribute("faculty_list", service.getFaculties());
    return "facultyList";
  }

  @RequestMapping(value = "/*/cathedras/{cathedraId}", method = GET)
  public String showCathedraProfile(@PathVariable int cathedraId, Model model) {
    model.addAttribute(service.findCathedraById(cathedraId));
    return "cathedraProfile";
  }

  /**
   * Request all cathedras of faculty.
   *
   * @param facultyId id of faculty.
   * @param model     model with faculty and cathedra_list.
   * @return name of view.
   */
  @RequestMapping(value = "/{facultyId}/cathedras", method = GET)
  public String showAllCathedras(@PathVariable int facultyId, Model model) {
    Faculty faculty = service.findFacultyById(facultyId);
    model.addAttribute(faculty);
    model.addAttribute("cathedra_list", faculty.getCathedras());
    return "cathedraList";
  }

  private void getEmployeesModel(Model model, int id, boolean isSoviet) {
    Faculty faculty = service.findFacultyById(id);
    model.addAttribute(faculty);
    if (isSoviet) {
      model.addAttribute("employee_list", service.getFacultySoviet(faculty.getEmployees(), id));
      model.addAttribute("soviet", true);
    } else {
      model.addAttribute("employee_list", service.getFacultyEmployees(faculty.getEmployees(), id));
      model.addAttribute("soviet", false);
    }
  }

  @RequestMapping(value = "/{facultyId}/employees", method = GET)
  public String showFacultyEmployees(@PathVariable int facultyId, Model model) {
    getEmployeesModel(model, facultyId, false);
    return "employeeList";
  }

  @RequestMapping(value = "/{facultyId}/soviet", method = GET)
  public String showFacultySoviet(@PathVariable int facultyId, Model model) {
    getEmployeesModel(model, facultyId, true);
    return "employeeList";
  }

  /**
   * Request cathedra lecturers.
   *
   * @param cathedraId id of cathedra.
   * @param model      model with cathedra and employee_list.
   * @return name of view.
   */
  @RequestMapping(value = "/*/cathedras/{cathedraId}/lecturers", method = GET)
  public String showCathedraLecturers(@PathVariable int cathedraId, Model model) {
    Cathedra cathedra = service.findCathedraById(cathedraId);
    model.addAttribute(cathedra);
    model.addAttribute("employee_list",
        service.getCathedraLecturers(cathedra.getEmployees(), cathedraId));
    return "employeeList";
  }

  /**
   * Request cathedra groups.
   *
   * @param cathedraId id of cathedra.
   * @param model      model with cathedra and group_class_list.
   * @return name of view.
   */
  @RequestMapping(value = "/*/cathedras/{cathedraId}/groupClasses", method = GET)
  public String showAllGroupClass(@PathVariable int cathedraId, Model model) {
    Cathedra cathedra = service.findCathedraById(cathedraId);
    model.addAttribute(cathedra);
    model.addAttribute("group_class_list", cathedra.getGroupClasses());
    return "groupClassList";
  }

  /**
   * Request to show group class profile.
   *
   * @param groupClassId id of group class.
   * @param model        model with groupClass and group_students_list.
   * @return name of view.
   */
  @RequestMapping(value = "/*/cathedras/*/groupClasses/{groupClassId}", method = GET)
  public String showGroupClassProfile(@PathVariable int groupClassId, Model model) {
    GroupClass groupClass = service.findGroupClassById(groupClassId);
    model.addAttribute(groupClass);
    model.addAttribute("group_students_list", groupClass.getGroupStudents());
    return "groupClassProfile";
  }

}
