package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.annotations.Push;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import java.time.Instant;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.setProperty;
import static java.util.concurrent.ConcurrentHashMap.newKeySet;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
@Push
public class MyUIUIAccess01 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }


  public static final Set<Updater> REGISTRATIONS = newKeySet();

  public interface Updater {
    void update(String message);
  }

  public static Registration register(Updater updater) {
    REGISTRATIONS.add(updater);
    return (Registration) () -> REGISTRATIONS.remove(updater);
  }

  public static class MyFormLayout extends FormLayout {

    private TextField message = new TextField("message");

    public MyFormLayout() {
      addComponents(message);
    }

    private Registration registration;

    public MyFormLayout register() {
      registration = MyUIUIAccess01.register((msg) -> MyFormLayout.this.message.getUI().access(() -> message.setValue(msg)));
      return this;
    }

    @Override
    public void detach() {
      super.detach();
      registration.remove();
    }
  }

  private static final Timer TIMER = new Timer(true);

  static {
    TIMER.scheduleAtFixedRate(
        new TimerTask() {
          @Override
          public void run() {
            REGISTRATIONS.forEach(updater -> updater.update(Instant.now().toString()));
          }
        },
        3_000,
        2_000
    );
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    @Override
    public Component get() {
      return new MyFormLayout().register();
    }
  }
}
