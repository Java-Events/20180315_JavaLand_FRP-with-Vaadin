package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.ui.*;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import java.util.function.Supplier;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIAttach01 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {
    private Supplier<Layout> fields() {
      return () -> new FormLayout(new MyTextfield("--name--"),
                                  new MyTextfield("--age--")
      );
    }

    private boolean visible = true;

    @Override
    public Component get() {
      final VerticalLayout   layout = new VerticalLayout();
      final Supplier<Layout> fields = fields();
      final Layout           f      = fields.get();

      f.setId("fieldsID");

      final Button button = new Button("work");
      button.addClickListener(e -> {
                                visible = !visible;
//                                fields.setVisible(visible); //attach/deattach ?
                                if (!visible) {
                                  for (final Component next : layout) {
                                    final String id = next.getId();
                                    if (id != null &&
                                        id.equals("fieldsID"))
                                      layout.removeComponent(next);
                                  }
                                } else {
                                  layout.addComponentAsFirst(fields.get());
                                }
                              }
      );

      layout.addComponents(f, button);
      return layout;
    }
  }

  public static class MyTextfield extends TextField implements HasLogger {
    public MyTextfield(String caption) {
      super(caption);
    }

    @Override
    public void attach() {
      super.attach();
      logger().warning("uiuiui I am touched....");
    }

    @Override
    public void detach() {
      super.detach();
      logger().warning("dammmmnnnn     feeling soooo lonely now..");
    }
  }
}

