package org.spring.samples.repository.SpringDataJpa;

import org.spring.samples.model.BaseEntity;
import org.spring.samples.model.Cathedra;
import org.spring.samples.model.Faculty;
import org.spring.samples.model.Group_class;
import org.spring.samples.repository.InstituteRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;
import java.util.Map;

public interface SpringDataInstituteRepository extends InstituteRepository, Repository<BaseEntity, Integer> {

  @Override
  @Query("from Faculty")
  Collection<Faculty> findAllFaculties();

  //TODO скорее вместе с реализацией кэша это будет не нужно
  default Map<Integer, Faculty> getInternalFaculties() {
    return null;
  }

  default Map<Integer, Cathedra> getInternalCathedras() {
    return null;
  }

  default Map<Integer, Group_class> getInternalGroup_classes() {
    return null;
  }
}
