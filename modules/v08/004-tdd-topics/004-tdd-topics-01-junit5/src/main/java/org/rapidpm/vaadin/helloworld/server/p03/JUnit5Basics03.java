package org.rapidpm.vaadin.helloworld.server.p03;

/**
 * Refactoring example
 */
public class JUnit5Basics03 {


  public String[] discardCommonPrefixOne(String a, String b) {
    String[] ret = {a, b};
    int      l   = a.length() < b.length() ? a.length() : b.length();
    for (int i = 0; i < l; i++) {
      if (a.charAt(i) == b.charAt(i)) {
        if (i + 1 < l) {
          ret[0] = a.substring(i + 1);
          ret[1] = b.substring(i + 1);
        } else {
          if (a.length() < b.length()) {
            ret[0] = "";
            ret[1] = b.substring(i + 1);
          }
          if (a.length() == b.length()) {
            ret[0] = "";
            ret[1] = "";
          }
          if (a.length() > b.length()) {
            ret[0] = a.substring(i + 1);
            ret[1] = "";
          }
        }
      } else break;
    }
    return ret;
  }

  public String[] discardCommonPrefixTwo(String a, String b) {
    final String[] ret = new String[2];
    int            l;
    if (a.length() < b.length()) {
      l = a.length();
    } else {
      l = b.length();
    }

    int position = 0;
    for (; position < l; position++) {
      final char charA = a.charAt(position);
      final char charB = b.charAt(position);
      if (charA != charB) {
        break;
      }
    }

    if (position >= a.length()) {
      ret[0] = "";
    } else {
      ret[0] = a.substring(position);
    }

    if (position >= b.length()) {
      ret[1] = "";
    } else {
      ret[1] = b.substring(position);
    }
    return ret;
  }


}

