package org.rapidpm.vaadin.helloworld.server.p02;

import com.vaadin.shared.Registration;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.lang.System.setProperty;
import static java.util.concurrent.ConcurrentHashMap.newKeySet;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIAttach02 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static final Set<Updater> REGISTRATIONS = newKeySet();

  public interface Updater {
    void update(String name, Integer age);
  }

  public static Registration register(Updater updater) {
    REGISTRATIONS.add(updater);
    return (Registration) () -> REGISTRATIONS.remove(updater);
  }


  public static class MyFormLayout extends FormLayout {

    private TextField name = new TextField("name");
    private TextField age  = new TextField("age");

    public MyFormLayout() {
      addComponents(name, age);
    }

    private Registration registration;

    public void register() {
      registration = MyUIAttach02.register((name, age) -> {
        MyFormLayout.this.name.setValue(name);
        MyFormLayout.this.age.setValue(String.valueOf(age));
      });
    }

    @Override
    public void detach() {
      super.detach();
      registration.remove();
    }
  }


  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
      final TextField name = new TextField("name");
      final TextField age  = new TextField("age");
//
      final Button btn = new Button("sync now");
      btn.addClickListener(e -> REGISTRATIONS.forEach(r -> r.update(name.getValue(),
                                                                    parseInt(age.getValue())
      )));

      final MyFormLayout myForm = new MyFormLayout();

      myForm.register();

      return new FormLayout(name, age, btn, myForm);

    }
  }
}
