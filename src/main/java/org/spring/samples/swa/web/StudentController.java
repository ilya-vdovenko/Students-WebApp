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
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.model.Student;
import org.spring.samples.swa.service.InstituteService;
import org.spring.samples.swa.web.editor.CathedraEditor;
import org.spring.samples.swa.web.editor.FacultyEditor;
import org.spring.samples.swa.web.editor.GroupClassEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * A controller that return view's of student's pages.
 *
 * @author Ilya Vdovenko
 */

@Controller
@RequestMapping(value = "/students")
public class StudentController {

  private final InstituteService service;
  private final String studentCreateOrUpdateForm = "StudentCreateOrUpdateForm";

  @Autowired
  public StudentController(InstituteService is) {
    this.service = is;
  }

  /**
   * Use for proper binding model with id.
   *
   * @param binder {@link WebDataBinder}
   */
  @InitBinder("student")
  public void facultyBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Faculty.class, "faculty", new FacultyEditor(service));
    binder.registerCustomEditor(Cathedra.class, "cathedra", new CathedraEditor(service));
    binder.registerCustomEditor(GroupClass.class, "groupClass", new GroupClassEditor(service));
  }

  @RequestMapping(value = "/{studentId}", method = GET)
  public String showStudentProfile(@PathVariable int studentId, Model model) {
    model.addAttribute(service.findStudentById(studentId));
    return "studentProfile";
  }

  @RequestMapping(method = GET)
  public String showAllStudents(Model model) {
    model.addAttribute("student_list", service.getStudents());
    return "studentList";
  }

  @RequestMapping(value = "/new", method = GET)
  public String initCreationForm(Model model) {
    model.addAttribute(new Student());
    return studentCreateOrUpdateForm;
  }

  /**
   * Post new student to db.
   *
   * @param student model with data.
   * @param errors errors that can be.
   * @param model model with same student if errors happen.
   * @return redirect.
   */
  @RequestMapping(value = "/new", method = POST)
  public String processCreationForm(@Valid Student student, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute(student);
      return studentCreateOrUpdateForm;
    }
    service.saveStudent(student);
    return "redirect:/students";
  }

  @RequestMapping(value = "/{studentId}/edit", method = GET)
  public String initUpdateOwnerForm(@PathVariable int studentId, Model model) {
    model.addAttribute(service.findStudentById(studentId));
    return studentCreateOrUpdateForm;
  }

  /**
   * Post updated student to db.
   *
   * @param student model with data.
   * @param result of editing.
   * @param studentId id of student.
   * @param model model with same student if errors happen.
   * @return redirect.
   */
  @RequestMapping(value = "/{studentId}/edit", method = POST)
  public String processUpdateOwnerForm(@Valid Student student, BindingResult result,
      @PathVariable int studentId, Model model) {
    if (result.hasErrors()) {
      model.addAttribute(student);
      return studentCreateOrUpdateForm;
    } else {
      student.setId(studentId);
      service.saveStudent(student);
      return "redirect:/students/{studentId}";
    }
  }
}
