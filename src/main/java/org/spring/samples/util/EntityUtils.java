package org.spring.samples.util;

import org.spring.samples.model.BaseEntity;

public class EntityUtils {

  private EntityUtils() {
    // Utility class
  }

  public static boolean equalsLoad(BaseEntity obj, int id) {
    return obj != null && obj.getId().equals(id);
  }

}
