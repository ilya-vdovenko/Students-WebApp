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

package org.spring.samples.swa.model;

import org.junit.jupiter.api.Test;
import org.spring.samples.swa.config.RootAppConfig;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * Test class for equalse methods of models.
 *
 * @author Ilya Vdovenko
 */
@SpringJUnitConfig(RootAppConfig.class)
@ActiveProfiles("jpa")
class ModelEqualsTests {

    @Test
    void equalsHashCodeContracts() {
        Faculty fac1 = new Faculty();
        fac1.setId(1);
        fac1.setTitle("First");
        Faculty fac2 = new Faculty();
        fac2.setId(2);
        fac2.setTitle("Second");
        Cathedra cat1 = new Cathedra();
        cat1.setId(1);
        cat1.setTitle("First");
        Cathedra cat2 = new Cathedra();
        cat2.setId(2);
        cat2.setTitle("Second");
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setFullName("First");
        Employee emp2 = new Employee();
        emp2.setId(2);
        emp2.setFullName("Second");
        Student stud1 = new Student();
        stud1.setId(1);
        stud1.setFullName("First");
        Student stud2 = new Student();
        stud2.setId(2);
        stud2.setFullName("Second");
        GroupClass groupClass1 = new GroupClass();
        groupClass1.setId(1);
        groupClass1.setTitle("First");
        GroupClass groupClass2 = new GroupClass();
        groupClass2.setId(2);
        groupClass2.setTitle("Second");
        EqualsVerifier.simple()
                .forClasses(Faculty.class, Cathedra.class, GroupClass.class, Employee.class, Student.class)
                .suppress(Warning.IDENTICAL_COPY_FOR_VERSIONED_ENTITY).withPrefabValues(Cathedra.class, cat1, cat2)
                .withPrefabValues(Employee.class, emp1, emp2).withPrefabValues(Student.class, stud1, stud2)
                .withPrefabValues(Faculty.class, fac1, fac2)
                .withPrefabValues(GroupClass.class, groupClass1, groupClass2).verify();
    }

}
