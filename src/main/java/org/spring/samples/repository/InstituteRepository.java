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

package org.spring.samples.repository;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;

import java.util.Collection;
import java.util.Map;

/**
 * Repository class for {@link org.spring.samples.model.UnitEntity} domain objects.
 *
 * @author Ilya Vdovenko
 */

public interface InstituteRepository {

  Map<Integer, Faculty> getInternalFaculties();

  Map<Integer, Cathedra> getInternalCathedras();

  Map<Integer, Group_class> getInternalGroup_classes();

  Faculty findFacultyById(int id);

  Collection<Faculty> findAllFaculties();

  Cathedra findCathedraById(int id);

  Group_class findGroup_classById(int id);
}
