package org.spring.samples.web.Editor;

import org.spring.samples.service.InstituteService;

import java.beans.PropertyEditorSupport;

public class GroupClassEditor extends PropertyEditorSupport {

  private final InstituteService service;

  public GroupClassEditor(InstituteService is) {
    this.service = is;
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(service.findGroup_classById(Integer.parseInt(text)));
  }
}
