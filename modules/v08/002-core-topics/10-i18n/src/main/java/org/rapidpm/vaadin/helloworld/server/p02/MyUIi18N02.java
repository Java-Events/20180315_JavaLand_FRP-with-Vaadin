package org.rapidpm.vaadin.helloworld.server.p02;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**

 */
public class MyUIi18N02 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
      return new Label("not yet implemented..");
    }
  }
}
