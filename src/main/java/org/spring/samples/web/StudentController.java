package org.spring.samples.web;

import org.spring.samples.service.InstituteService;
import org.spring.samples.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.spring.samples.repository.StudentRepository;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/students")
public class StudentController {

    private final StudentRepository sr;
    private final InstituteService is;
    private final String Student_CREATE_OR_UPDATE_FORM = "StudentCreateOrUpdateForm";

    @Autowired
    public StudentController(StudentRepository sr, InstituteService is) {
        this.sr = sr;
        this.is = is;
    }

    @RequestMapping(value = "/{studentId}", method = GET)
    public String showStudentProfile(@PathVariable int studentId, Model model) {
        model.addAttribute(sr.findById(studentId));
        return "studentProfile";
    }

    @RequestMapping(method = GET)
    public String showAllStudents(Model model) {
        model.addAttribute("student_list", sr.getAllStudents());
        return "students";
    }

    @RequestMapping(value = "/new", method = GET)
    public String initCreationForm(Model model) {
        model.addAttribute(new Student());
        return Student_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/new", method = POST)
    public String processCreationForm(@Valid Student student, Errors errors) {
        if (errors.hasErrors()) return Student_CREATE_OR_UPDATE_FORM;
        is.saveStudent(student);
        return "redirect:/students";
    }

    @RequestMapping(value = "/{studentId}/edit", method = GET)
    public String initUpdateOwnerForm(@PathVariable int studentId, Model model) {
        model.addAttribute(sr.findById(studentId));
        return Student_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/{studentId}/edit", method = POST)
    public String processUpdateOwnerForm(@Valid Student student, Errors errors, @PathVariable int studentId) {
        if (errors.hasErrors()) {
            return Student_CREATE_OR_UPDATE_FORM;
        } else {
            student.setId(studentId);
            is.saveStudent(student);
            return "redirect:/students/{studentId}";
        }
    }
}