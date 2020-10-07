package org.spring.samples.web;

import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.service.InstituteService;
import org.spring.samples.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/faculties")
public class InstituteController {

  //TODO убрать кэш реализацию
  private Faculty loadFac;
  private Cathedra loadCat;
  private Collection<Faculty> facultyList;

  private final InstituteService service;

  @Autowired
  public InstituteController(InstituteService is) {
    this.service = is;
  }

  @RequestMapping(value = "/{facultyId}", method = GET)
  public String showFacultyProfile(@PathVariable int facultyId, Model model) {
    getFacModel(facultyId, model);
    return "facultyProfile";
  }

  @RequestMapping(method = GET)
  public String showAllFaculties(Model model) {
    facultyList = service.getFaculties();
    model.addAttribute("faculty_list", facultyList);
    return "faculties";
  }

  @RequestMapping(value = "/{facultyId}/cathedras/{cathedraId}", method = GET)
  public String showCathedraProfile(@PathVariable int facultyId, @PathVariable int cathedraId, Model model) {
    getCatModal(facultyId, cathedraId, model);
    return "cathedraProfile";
  }

  @RequestMapping(value = "/{facultyId}/cathedras", method = GET)
  public String showAllCathedras(@PathVariable int facultyId, Model model) {
    getFacModel(facultyId, model);
    model.addAttribute(loadFac);
    model.addAttribute("cathedra_list", loadFac.getCathedras());
    return "cathedras";
  }

  @RequestMapping(value = "/{facultyId}/employees", method = GET)
  public String showFacultyEmployees(@PathVariable int facultyId, Model model) {
    getFacModel(facultyId, model);
    model.addAttribute("employee_list", service.getFacultyEmployees(loadFac.getEmployees(), facultyId));
    model.addAttribute("soviet", false);
    return "employees";
  }

  @RequestMapping(value = "/{facultyId}/soviet", method = GET)
  public String showFacultySoviet(@PathVariable int facultyId, Model model) {
    getFacModel(facultyId, model);
    model.addAttribute("employee_list", service.getFacultySoviet(loadFac.getEmployees(), facultyId));
    model.addAttribute("soviet", true);
    return "employees";
  }

  @RequestMapping(value = "/{facultyId}/cathedras/{cathedraId}/lecturers", method = GET)
  public String showCathedraLecturers(@PathVariable int facultyId, @PathVariable int cathedraId, Model model) {
    getCatModal(facultyId, cathedraId, model);
    model.addAttribute("employee_list", service.getCathedraLecturers(loadCat.getEmployees(), cathedraId));
    return "employees";
  }

  @RequestMapping(value = "/{facultyId}/cathedras/{cathedraId}/group_classes", method = GET)
  public String showAllGroup_class(@PathVariable int facultyId, @PathVariable int cathedraId, Model model) {
    getCatModal(facultyId, cathedraId, model);
    model.addAttribute("group_class_list", loadCat.getGroup_classes());
    return "group_classes";
  }

  @RequestMapping(value = "/{facultyId}/cathedras/{cathedraId}/group_classes/{group_classId}", method = GET)
  public String showGroup_classProfile(@PathVariable int cathedraId, @PathVariable int group_classId, Model model) {
    Group_class group_class = null;
    if (EntityUtils.equalsLoad(loadCat, cathedraId)) {
      for (Group_class grp : loadCat.getGroup_classes()) {
        if (grp.getId().equals(group_classId)) {
          group_class = grp;
          break;
        }
      }
    } else {
      group_class = service.findGroup_classById(group_classId);
    }
    if (group_class != null) {
      model.addAttribute(group_class);
      model.addAttribute("group_students_list", group_class.getGroup_students());
    }
    return "group_classProfile";
  }

  private void getFacModel(int facultyId, Model model) {
    if (EntityUtils.equalsLoad(loadFac, facultyId)) {
      model.addAttribute("faculty", loadFac);
      return;
    } else {
      if (facultyList != null && !facultyList.isEmpty()) {
        for (Faculty facInList : facultyList) {
          if (EntityUtils.equalsLoad(facInList, facultyId)) {
            loadFac = facInList;
            model.addAttribute("faculty", loadFac);
            return;
          }
        }
      } else {
        loadFac = service.findFacultyById(facultyId);
      }
    }
    model.addAttribute("faculty", loadFac);
  }

  private void getCatModal(int facultyId, int cathedraId, Model model) {
    if (EntityUtils.equalsLoad(loadCat, cathedraId)) {
      model.addAttribute("cathedra", loadCat);
      return;
    }
    if (EntityUtils.equalsLoad(loadFac, facultyId)) {
      for (Cathedra cat : loadFac.getCathedras()) {
        if (EntityUtils.equalsLoad(cat, cathedraId)) {
          loadCat = cat;
          break;
        }
      }
    } else {
      loadCat = service.findCathedraById(cathedraId);
    }
    model.addAttribute("cathedra", loadCat);
  }

}
