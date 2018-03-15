package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;
import org.rapidpm.vaadin.helloworld.server.shared.DataHolder;

import java.util.stream.Stream;

import static java.lang.System.setProperty;
import static java.util.stream.Stream.of;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUICombobox01 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }


  public static class MySupplier implements CoreUIService.ComponentSupplier {
    public Stream<DataHolder> values() {
      return of(
          new DataHolder("AF", "AL"),
          new DataHolder("BF", "BL"),
          new DataHolder("CF", "CL"),
          new DataHolder("DF", "DL")
      );
    }

    @Override
    public Component get() {
      new com.vaadin.ui.ComboBox<org.rapidpm.vaadin.helloworld.server.shared.DataHolder>()
    }
  }
}

