package org.spring.samples.util;

import org.spring.samples.model.BaseEntity;

import java.util.Collection;

public class EntityUtils {

  private EntityUtils() {
    // Utility class
  }

  public static boolean equalsLoad(BaseEntity obj, int id) {
    return obj != null && obj.getId().equals(id);
  }

  public static <E> boolean isValidCollection(Collection<E> collection) {
    return collection != null && !collection.isEmpty();
  }

}
