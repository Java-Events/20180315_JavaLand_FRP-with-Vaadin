package org.rapipm.vaadin.industrial.demo.i18n;

/**
 *
 */
public interface PropertyService {

  String resolve(String key);

  boolean hasKey(String key);
}
