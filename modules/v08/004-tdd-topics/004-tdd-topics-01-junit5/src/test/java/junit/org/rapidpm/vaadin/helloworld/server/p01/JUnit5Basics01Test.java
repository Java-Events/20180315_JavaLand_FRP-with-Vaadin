package junit.org.rapidpm.vaadin.helloworld.server.p01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.vaadin.helloworld.server.p01.JUnit5Basics01;

/**
 *
 */
public class JUnit5Basics01Test {


  @Test
  void test001() {
    Assertions.assertEquals("HELLO WORLD", new JUnit5Basics01().uppercase("hello world"));
  }
}
