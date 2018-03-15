package org.rapidpm.vaadin.helloworld.server.p02;

import com.vaadin.data.HasValue;
import com.vaadin.data.provider.AbstractBackEndDataProvider;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.Query;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.rapidpm.vaadin.helloworld.server.CoreUIService;
import org.rapidpm.vaadin.helloworld.server.shared.DataHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.System.setProperty;
import static org.rapidpm.vaadin.helloworld.server.CoreUIService.MyUI.COMPONENT_SUPPLIER_TO_USE;
import static org.rapidpm.vaadin.helloworld.server.p02.MyUIGrid02.DataSource.*;

/**

 */
public class MyUIGrid02 extends CoreUIService {

  static {
    setProperty(COMPONENT_SUPPLIER_TO_USE, MyUIGrid02.MySupplier.class.getName());
  }

  // must be able to paging data and
  // possible to give max amount of items
  public static class DataSource {

    private static List<DataHolder> storage = new ArrayList<>();

    private static Function<Integer, String> strFN() {
      return (i) -> i < 0 ? "" : strFN().apply((i / 26) - 1) + (char) (65 + i % 26);
    }

    public static void resetData() {
      storage.clear();
      IntStream
          .range(0, 1_000)
          .mapToObj(value -> new DataHolder(value + "", strFN().apply(value)))
          .forEach(storage::add);
    }


    public static int count(Optional<String> filter) {
      return (int) ((filter.isPresent())
                    ? storage
                        .stream()
                        .filter(dataHolder -> dataHolder.getLastName().contains(filter.get()))
                        .count()
                    : storage.size());
    }

    public static Stream<DataHolder> find(int offset, int limit) {
      return storage
          .stream()
          .skip(offset)
          .limit(limit);
    }

    public static Stream<DataHolder> find(int offset, int limit, Optional<String> filter) {

      return (filter.isPresent())
             ? storage
                 .stream()
                 .filter(dataHolder -> dataHolder.getLastName().contains(filter.get()))
                 .skip(offset)
                 .limit(limit)
             : storage
                 .stream()
                 .skip(offset)
                 .limit(limit);
    }


  }

  public static class MySupplier implements CoreUIService.ComponentSupplier {

    @Override
    public Component get() {
      resetData(); // not nice !!!

      final Grid<DataHolder> grid = new Grid<>();
      grid.addColumn(DataHolder::getFirstName).setCaption("First Name");
      grid.addColumn(DataHolder::getLastName).setCaption("Last Name");
      grid.setSelectionMode(Grid.SelectionMode.SINGLE);
      grid.setFooterVisible(true);
      grid.setHeight(100, Sizeable.Unit.PERCENTAGE);
      grid.setDescriptionGenerator(dataHolder -> "Description of " + dataHolder.getFirstName());
//      grid.setItems(values());

      final ConfigurableFilterDataProvider<DataHolder, Void, String> dataProvider
          = new DataHolderProvider()
          .withConfigurableFilter();

      grid.setDataProvider(dataProvider);

      final TextField filterField = new TextField();
      filterField.setPlaceholder("filter...");
      filterField.setCaption("Filter");

      filterField.addValueChangeListener((HasValue.ValueChangeListener<String>) event -> {
        final String eventValue = event.getValue();
        dataProvider.setFilter(eventValue);
      });


      final VerticalLayout components = new VerticalLayout(filterField, grid);
      components.setExpandRatio(grid, 1);
      components.setSizeFull();
      return components;
    }
  }

  public static class DataHolderProvider extends AbstractBackEndDataProvider<DataHolder, String> {
    @Override
    protected Stream<DataHolder> fetchFromBackEnd(Query<DataHolder, String> query) {
      final Optional<String> filter = query.getFilter();
      final int              limit  = query.getLimit();
      final int              offset = query.getOffset();

      return find(offset, limit, filter);
    }

    @Override
    protected int sizeInBackEnd(Query<DataHolder, String> query) {
      return count(query.getFilter());
    }
  }
}
