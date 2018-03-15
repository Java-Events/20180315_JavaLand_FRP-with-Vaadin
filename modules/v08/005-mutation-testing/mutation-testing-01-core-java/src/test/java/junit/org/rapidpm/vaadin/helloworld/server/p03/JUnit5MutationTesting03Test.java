package junit.org.rapidpm.vaadin.helloworld.server.p03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.helloworld.server.p03.JUnit5MutationTesting03;

/**
 *
 */
public class JUnit5MutationTesting03Test {

  @Test
  void test01() {
    final int add = new JUnit5MutationTesting03().add(0, 0);
    Assertions.assertEquals(0, add);
  }
}
