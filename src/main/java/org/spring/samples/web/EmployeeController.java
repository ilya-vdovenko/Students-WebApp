package org.spring.samples.web;

import org.spring.samples.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {

  private final InstituteService service;

  @Autowired
  EmployeeController(InstituteService is) {
    this.service = is;
  }

  @RequestMapping(value = "/{employeeId}", method = GET)
  public String showEmployeeProfile(@PathVariable int employeeId, Model model) {
    model.addAttribute(service.findEmployeeById(employeeId));
    return "employeeProfile";
  }

  @RequestMapping(method = GET)
  public String showAllEmployees(Model model) {
    model.addAttribute("employee_list", service.getEmployees());
    return "employees";
  }
}
