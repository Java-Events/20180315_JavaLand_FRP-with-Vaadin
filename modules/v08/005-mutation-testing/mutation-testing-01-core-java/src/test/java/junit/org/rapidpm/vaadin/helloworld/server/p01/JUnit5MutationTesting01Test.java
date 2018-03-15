package junit.org.rapidpm.vaadin.helloworld.server.p01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.helloworld.server.p01.JUnit5MutationTesting01;

/**
 *
 */
public class JUnit5MutationTesting01Test {


  @Test
  void test001() {
    Assertions.assertEquals("HELLO WORLD", new JUnit5MutationTesting01().uppercase("hello world"));
  }
}
