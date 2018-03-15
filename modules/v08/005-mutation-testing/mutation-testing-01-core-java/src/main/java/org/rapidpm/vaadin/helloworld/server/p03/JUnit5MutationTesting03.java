package org.rapidpm.vaadin.helloworld.server.p03;

/**
 *
 */
public class JUnit5MutationTesting03 {

  public int add(int a, int b) {
    if (a < 2) {
      return (a + b) * -1;
    } else {
      return a + b;
    }
  }
}
