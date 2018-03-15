package org.rapidpm.vaadin.helloworld.server.p01;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.ValueProvider;
import com.vaadin.server.Setter;
import com.vaadin.ui.*;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;

import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;

/**
 *
 */
public class MyUIServiceBinder01 extends CoreUIService {

  static {
    System.setProperty(COMPONENT_SUPPLIER_TO_USE, MySupplier.class.getName());
  }

  public static class MySupplier implements ComponentSupplier {

    public static class DataHolder {
      private String  name;
      private Integer age;

      public DataHolder(String name, Integer age) {
        this.name = name;
        this.age = age;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public Integer getAge() {
        return age;
      }

      public void setAge(Integer age) {
        this.age = age;
      }

      @Override
      public String toString() {
        return "DataHolder{" +
               "name='" + name + '\'' +
               ", age=" + age +
               '}';
      }
    }

    @Override
    public Component get() {

      final Binder<DataHolder> binder     = new Binder<>();
      final TextField          nameField  = new TextField("--name--");
      final TextField          ageField   = new TextField("--age--");
      final DataHolder         dataHolder = new DataHolder("Initial Name", -1);
      final Label              message    = new Label();

      final Button button = new Button("work");

      button.addClickListener(e -> {
                                try {
                                  binder.writeBean(dataHolder);
                                  System.out.println("dataHolder= " + dataHolder);
                                  message.setCaption(dataHolder.toString());
                                } catch (ValidationException e1) {
                                  e1.printStackTrace();
                                }
                              }
      );
      binder.forField(nameField).bind(DataHolder::getName, DataHolder::setName);
      binder.forField(ageField)
            .bind((ValueProvider<DataHolder, String>) value -> value.getAge().toString(),
                  (Setter<DataHolder, String>) (value, age) -> value.setAge(Integer.parseInt(age))
            );
      binder.readBean(dataHolder);

      return new FormLayout(nameField, ageField, button, message);
    }
  }
}
