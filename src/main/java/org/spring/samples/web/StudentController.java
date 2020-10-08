package org.spring.samples.web;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.model.Student;
import org.spring.samples.service.InstituteService;
import org.spring.samples.web.Editor.CathedraEditor;
import org.spring.samples.web.Editor.FacultyEditor;
import org.spring.samples.web.Editor.GroupClassEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping(value = "/students")
public class StudentController {

  private final InstituteService service;
  private final String Student_CREATE_OR_UPDATE_FORM = "StudentCreateOrUpdateForm";

  @Autowired
  public StudentController(InstituteService is) {
    this.service = is;
  }

  @InitBinder("student")
  public void facultyBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Faculty.class, "faculty", new FacultyEditor(service));
    binder.registerCustomEditor(Cathedra.class, "cathedra", new CathedraEditor(service));
    binder.registerCustomEditor(Group_class.class, "group_class", new GroupClassEditor(service));
  }

  @RequestMapping(value = "/{studentId}", method = GET)
  public String showStudentProfile(@PathVariable int studentId, Model model) {
    model.addAttribute(service.findStudentById(studentId));
    return "studentProfile";
  }

  @RequestMapping(method = GET)
  public String showAllStudents(Model model) {
    model.addAttribute("student_list", service.getStudents());
    return "students";
  }

  @RequestMapping(value = "/new", method = GET)
  public String initCreationForm(Model model) {
    model.addAttribute(new Student());
    return Student_CREATE_OR_UPDATE_FORM;
  }

  @RequestMapping(value = "/new", method = POST)
  public String processCreationForm(@Valid Student student, Errors errors, Model model) {
    if (errors.hasErrors()) {
      model.addAttribute(student);
      return Student_CREATE_OR_UPDATE_FORM;
    }
    service.saveStudent(student);
    return "redirect:/students";
  }

  @RequestMapping(value = "/{studentId}/edit", method = GET)
  public String initUpdateOwnerForm(@PathVariable int studentId, Model model) {
    model.addAttribute(service.findStudentById(studentId));
    return Student_CREATE_OR_UPDATE_FORM;
  }

  @RequestMapping(value = "/{studentId}/edit", method = POST)
  public String processUpdateOwnerForm(@Valid Student student, BindingResult result,
                                       @PathVariable int studentId, Model model) {
    if (result.hasErrors()) {
      model.addAttribute(student);
      return Student_CREATE_OR_UPDATE_FORM;
    } else {
      student.setId(studentId);
      service.saveStudent(student);
      return "redirect:/students/{studentId}";
    }
  }
}
