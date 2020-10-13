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

package org.spring.samples.web;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * A controller that return data in json type
 *
 * @author Ilya Vdovenko
 */

@Controller
public class JsonInstituteController {

  private final InstituteService service;

  @Autowired
  public JsonInstituteController(InstituteService is) {
    this.service = is;
  }

  @RequestMapping(value = "/faculties/getFacList", produces = "application/json", method = GET)
  public @ResponseBody
  Collection<Faculty> getFacList() {
    return service.getFaculties();
  }

  @RequestMapping(value = "/cathedras/getCatList", produces = "application/json", method = GET)
  public @ResponseBody
  Set<Cathedra> getCatList(@RequestParam int facultyId) {
    Faculty faculty = service.findFacultyById(facultyId);
    return faculty.getCathedras();
  }

  @RequestMapping(value = "/group_classes/getGroupList", produces = "application/json", method = GET)
  public @ResponseBody
  Set<Group_class> getGroupList(@RequestParam int cathedraId) {
    Cathedra cathedra = service.findCathedraById(cathedraId);
    return cathedra.getGroup_classes();
  }
}
