package junit.org.rapidpm.vaadin.helloworld.server.p02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Store;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.model.Pair;
import org.rapidpm.vaadin.helloworld.server.p02.JUnit5Basics02;

import java.util.function.Function;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.create;

/**
 *
 */
@ExtendWith(JUnit5Basics02Test.LifeCycle.class)
public class JUnit5Basics02Test {


  public static class Value extends Pair<String, String> {
    public Value(String key, String value) {
      super(key, value);
    }

    public String key() { return getT1();}

    public String value() { return getT2(); }
  }


  private static Function<ExtensionContext, Store> storeFn() {
    return (exCtx) -> exCtx.getStore(create(JUnit5Basics02Test.class.getSimpleName()));
  }

  public static class LifeCycle implements BeforeEachCallback, AfterEachCallback, HasLogger {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
      final Store store = storeFn().apply(extensionContext);
      logger().info("afterEach" + store.get("key").toString());
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
      final Store store = storeFn().apply(extensionContext);
      store.put("key", new Value("my Key", "my Value"));
    }
  }

  @Test
  void test001() {
    Assertions.assertEquals("HELLO WORLD", new JUnit5Basics02().uppercase("hello world"));
  }

}
