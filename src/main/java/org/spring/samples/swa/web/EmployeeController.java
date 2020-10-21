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

import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * A controller that return view's of employee's pages
 *
 * @author Ilya Vdovenko
 */

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
    model.addAttribute("employee", service.findEmployeeById(employeeId));
    return "employeeProfile";
  }

  @RequestMapping(method = GET)
  public String showAllEmployees(Model model) {
    model.addAttribute("employee_list", service.getEmployees());
    return "employees";
  }
}
