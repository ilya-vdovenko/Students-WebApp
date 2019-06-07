package org.spring.samples.web;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
        return "faculties";
    }

    @RequestMapping(value = "/{facultyId}/cathedras/{cathedraId}", method = GET)
    public String showCathedraProfile(@PathVariable int cathedraId, Model model) {
        model.addAttribute(service.findCathedraById(cathedraId));
        return "cathedraProfile";
    }

    @RequestMapping(value = "/{facultyId}/cathedras", method = GET)
    public String showAllCathedras(@PathVariable int facultyId, Model model) {
        Faculty faculty = service.findFacultyById(facultyId);
        model.addAttribute(faculty);
        model.addAttribute("cathedra_list", faculty.getCathedras());
        return "cathedras";
    }

    @RequestMapping(value = "/{facultyId}/cathedras/{cathedraId}/group_classes/{group_classId}", method = GET)
    public String showGroup_classProfile(@PathVariable int group_classId, Model model) {
        Group_class group_class = service.findGroup_classById(group_classId);
        model.addAttribute(group_class);
        model.addAttribute("group_students_list", group_class.getGroup_students());
        return "group_classProfile";
    }

    @RequestMapping(value = "/{facultyId}/cathedras/{cathedraId}/group_classes", method = GET)
    public String showAllGroup_class(@PathVariable int cathedraId, Model model) {
        Cathedra cathedra = service.findCathedraById(cathedraId);
        model.addAttribute(cathedra);
        model.addAttribute("group_class_list", cathedra.getGroup_classes());
        return "group_classes";
    }
}