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

import org.spring.samples.swa.model.StudentDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validator for Student form.
 *
 * @author Ilya Vdovenko
 */

public class StudentValidator implements Validator {

  public static final String ERROR_EMPTY = "error.empty";

  @Override
  public boolean supports(Class<?> clazz) {
    return StudentDto.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    StudentDto student = (StudentDto) target;

    if (student.getFullName().length() < 10 || student.getFullName().length() > 60) {
      errors.rejectValue("fullName", "error.min_max.size", new Object[]{10, 60}, null);
    }

    ValidationUtils.rejectIfEmpty(errors, "birthday", ERROR_EMPTY);
    ValidationUtils.rejectIfEmpty(errors, "sex", ERROR_EMPTY);

    if (student.getTelephone().length() < 11) {
      errors.rejectValue("telephone", "error.max.size", new Object[]{11}, null);
    }

    if (student.getActualAddress().length() < 20 || student.getActualAddress().length() > 100) {
      errors.rejectValue("actualAddress", "error.min_max.size", new Object[]{20, 100}, null);
    }

    ValidationUtils.rejectIfEmpty(errors, "faculty", ERROR_EMPTY);
    ValidationUtils.rejectIfEmpty(errors, "cathedra", ERROR_EMPTY);
    ValidationUtils.rejectIfEmpty(errors, "groupClass", ERROR_EMPTY);
  }
}
