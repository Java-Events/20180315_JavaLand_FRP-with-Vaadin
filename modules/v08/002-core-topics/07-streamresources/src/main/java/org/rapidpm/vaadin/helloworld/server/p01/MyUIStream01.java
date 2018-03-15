package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.server.ClassResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIStream01 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
      return new Image("Nasa Pic", new ClassResource("/nasa_pic_00085.jpg"));
    }
  }
}

