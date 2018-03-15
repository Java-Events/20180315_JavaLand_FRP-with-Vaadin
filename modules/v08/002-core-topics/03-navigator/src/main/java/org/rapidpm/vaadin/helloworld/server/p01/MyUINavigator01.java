package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUINavigator01 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MyViewA extends Composite implements View {

    public MyViewA(Navigator navigator) {
      final Button buttonBack = new Button("Back");
      buttonBack.addClickListener(e -> navigator.navigateTo(""));

      final Button buttonB = new Button("B");
      buttonB.addClickListener(e -> navigator.navigateTo("B"));
      setCompositionRoot(new HorizontalLayout(buttonBack, buttonB));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
      Notification.show("My Viw A ", Notification.Type.WARNING_MESSAGE);
    }
  }

  public static class MyViewB extends Composite implements View {

    public MyViewB(Navigator navigator) {
      final Button buttonBack = new Button("Back");
      buttonBack.addClickListener(e -> navigator.navigateTo(""));
      final Button buttonA = new Button("A");
      buttonA.addClickListener(e -> navigator.navigateTo(""));
      setCompositionRoot(new VerticalLayout(buttonBack, buttonA));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
      Notification.show("My Viw B ", Notification.Type.WARNING_MESSAGE);
    }
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
      final Navigator navigator = new Navigator(UI.getCurrent(), UI.getCurrent());
      navigator.addView("", new MyViewA(navigator));
      navigator.addView("B", new MyViewB(navigator));
      return navigator.getCurrentView().getViewComponent();
    }
  }
}
