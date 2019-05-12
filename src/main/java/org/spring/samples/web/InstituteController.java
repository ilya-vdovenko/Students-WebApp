package org.spring.samples.web;

import org.spring.samples.repository.InstituteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/faculties")
public class InstituteController {

    private final InstituteRepository ir;

    @Autowired
    public InstituteController(InstituteRepository ir) {this.ir = ir;}

    @RequestMapping(value = "/{facultyId}", method = GET)
    public String showFacultyProfile(@PathVariable int facultyId, Model model) {
        model.addAttribute(ir.findFacultyById(facultyId));
        return "facultyProfile";
    }

    @RequestMapping(method = GET)
    public String showAllFaculties(Model model) {
        model.addAttribute("faculty_list", ir.getAllFaculties());
        return "faculties";
    }

    @RequestMapping(value = "/{facultyId}/{cathedraId}", method = GET)
    public String showCathedraProfile(@PathVariable int facultyId, @PathVariable int cathedraId, Model model) {
        model.addAttribute(ir.findCathedraById(facultyId, cathedraId));
        return "cathedraProfile";
    }

    @RequestMapping(value = "/{facultyId}/cathedras", method = GET)
    public String showAllCathedras(@PathVariable int facultyId, Model model) {
        model.addAttribute("cathedra_list", ir.getAllCathedras(facultyId));
        return "cathedras";
    }
}