package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.ui.*;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIComponent01 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
      return new HorizontalLayout(new MyComponentA(), new MyComponentB());
    }
  }

  public static class MyComponentA extends VerticalLayout {
    private TextField name        = new TextField("Name");
    private TextField description = new TextField("Description");

    public MyComponentA() {
      addComponents(name, description);
    }

    public MyComponentA(Component... children) {
      throw new RuntimeException("hhmm lack of inheritance ..");
    }
  }

  public static class MyComponentB extends Composite {
    private TextField name        = new TextField("Name");
    private TextField description = new TextField("Description");

    public MyComponentB() {
      setCompositionRoot(new VerticalLayout(name, description));
    }

  }
}
