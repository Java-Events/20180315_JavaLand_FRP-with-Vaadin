package org.rapidpm.vaadin.helloworld.server.shared;

/**
 *
 */
public class DataHolder {
  private String firstName;
  private String lastName;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public DataHolder(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}
