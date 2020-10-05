package org.spring.samples.web.Editor;

import org.spring.samples.service.InstituteService;

import java.beans.PropertyEditorSupport;

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
