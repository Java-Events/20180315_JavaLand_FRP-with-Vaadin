package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;
import org.rapidpm.vaadin.helloworld.server.PropertyService;
import org.rapidpm.vaadin.helloworld.server.PropertyServiceInMemory;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIi18N01 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
      final PropertyService propertyService = new PropertyServiceInMemory();

      ((PropertyServiceInMemory) propertyService).postConstruct();

      final Button btnOK = new Button();
      btnOK.setCaption(propertyService.resolve("generic.ok"));
      return btnOK;

    }
  }
}
