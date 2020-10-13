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

package org.spring.samples.web.Editor;

import org.spring.samples.service.InstituteService;

import java.beans.PropertyEditorSupport;

/**
 * Property editor for managing conversion
 * between String id and Integer id of {@link org.spring.samples.model.Faculty}
 *
 * @author Ilya Vdovenko
 */

public class FacultyEditor extends PropertyEditorSupport {

  private final InstituteService service;

  public FacultyEditor(InstituteService is) {
    this.service = is;
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(service.findFacultyById(Integer.parseInt(text)));
  }
}
