package org.rapidpm.vaadin.helloworld.server.p03;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIBasic03 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {

    public Button btn(String caption) {
      Button button = new Button(caption);
      button.setSizeFull();
      return button;
    }

    @Override
    public Component get() {
      final GridLayout grid4X4 = new GridLayout(4, 4);

      grid4X4.addComponent(btn("AA"), 0, 0);
      grid4X4.addComponent(btn("BA"), 1, 0);
      grid4X4.addComponent(btn("CA"), 2, 0);
      grid4X4.addComponent(btn("DA"), 3, 0);

      grid4X4.addComponent(btn("AB"), 0, 1);
      grid4X4.addComponent(btn("BB"), 1, 1);
      grid4X4.addComponent(btn("CB"), 2, 1);
      grid4X4.addComponent(btn("DB"), 3, 1);
      return grid4X4;
    }
  }
}
