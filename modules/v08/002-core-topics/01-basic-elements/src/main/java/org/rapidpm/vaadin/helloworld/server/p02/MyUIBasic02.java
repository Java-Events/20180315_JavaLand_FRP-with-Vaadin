package org.rapidpm.vaadin.helloworld.server.p02;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIBasic02 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {

    @Override
    public Component get() {
      return new Button("I am a button");
    }
  }
}
