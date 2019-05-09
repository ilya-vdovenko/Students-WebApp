package org.spring.samples.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.spring.samples.repository.StudentRepository;

import java.util.Map;

@Controller
public class StudentController {

    private final StudentRepository sr;

    @Autowired
    public StudentController(StudentRepository sr) {this.sr = sr;}

    @RequestMapping(value = {"/home","/"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String allStudents(Map<String, Object> model) {
        model.put("student_list", sr.getAllStudents());
        return "students";
    }

    /*@RequestMapping(value = "/students/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Owner owner = new Owner();
        model.put("owner", owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @RequestMapping(value = "/owners/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            this.clinicService.saveOwner(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }*/
}