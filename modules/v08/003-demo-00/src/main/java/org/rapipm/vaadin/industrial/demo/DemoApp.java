package org.rapipm.vaadin.industrial.demo;

import com.vaadin.ui.Component;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;
import org.rapipm.vaadin.industrial.demo.i18n.PropertyServiceInMemory;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class DemoApp extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
    PropertyServiceInMemory.instance().postConstruct();
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
//      final PropertyService propertyService = new PropertyServiceInMemory();
//      ((PropertyServiceInMemory) propertyService).postConstruct();

      //TODO create Instance of your component here...
      return null;
    }
  }
}
