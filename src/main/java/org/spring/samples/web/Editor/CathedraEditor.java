package org.spring.samples.web.Editor;

import org.spring.samples.service.InstituteService;

import java.beans.PropertyEditorSupport;

public class CathedraEditor extends PropertyEditorSupport {

  private final InstituteService service;

  public CathedraEditor(InstituteService is) {
    this.service = is;
  }

  @Override
  public void setAsText(String text) throws IllegalArgumentException {
    setValue(service.findCathedraById(Integer.parseInt(text)));
  }
}
