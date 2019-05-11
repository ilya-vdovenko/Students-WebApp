package org.spring.samples.web;

import org.spring.samples.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeRepository er;

    @Autowired
    EmployeeController(EmployeeRepository er) {this.er = er;}

    @RequestMapping(value = "/{employeeId}", method = GET)
    public String showEmployeeProfile(@PathVariable int employeeId, Model model) {
        model.addAttribute(er.findById(employeeId));
        return "employeeProfile";
    }

    @RequestMapping(method = GET)
    public String showAllEmployees(Model model) {
        model.addAttribute("employee_list", er.getAllEmployees());
        return "employees";
    }
}