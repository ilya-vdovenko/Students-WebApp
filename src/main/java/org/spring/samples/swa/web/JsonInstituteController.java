/*
 * Copyright 2019-2021, Ilya Vdovenko and the Students-WebApp contributors.
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

import java.util.Collection;
import java.util.Set;
import org.spring.samples.swa.model.Cathedra;
import org.spring.samples.swa.model.Faculty;
import org.spring.samples.swa.model.GroupClass;
import org.spring.samples.swa.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * A controller that return data in json type.
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

  @GetMapping(value = "/faculties/getFacList", produces = "application/json")
  public @ResponseBody
  Collection<Faculty> getFacList() {
    return service.getFaculties();
  }

  @GetMapping(value = "/cathedras/getCatList", produces = "application/json")
  public @ResponseBody
  Set<Cathedra> getCatList(@RequestParam int facultyId) {
    Faculty faculty = service.findFacultyById(facultyId);
    return faculty.getCathedras();
  }

  @GetMapping(value = "/groupClasses/getGroupList", produces = "application/json")
  public @ResponseBody
  Set<GroupClass> getGroupList(@RequestParam int cathedraId) {
    Cathedra cathedra = service.findCathedraById(cathedraId);
    return cathedra.getGroupClasses();
  }
}
