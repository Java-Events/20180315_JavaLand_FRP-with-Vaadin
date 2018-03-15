package junit.org.rapidpm.vaadin.helloworld.server.p03;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.rapidpm.dependencies.core.logger.HasLogger;
import org.rapidpm.frp.memoizer.Memoizer;
import org.rapidpm.frp.model.Pair;
import org.rapidpm.vaadin.helloworld.server.p03.JUnit5Basics03;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.deepEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 *
 */
public class JUnit5Basics03Test implements HasLogger {


  // total 225_866_364_516
//  public static final int END_EXCLUSIVE_ZZZZ = 475_254; //ZZZZ
  public static final int END_EXCLUSIVE_ZZZZ = 10_000; //ZZZZ

  @Test
  void test001() {
    final JUnit5Basics03 service = new JUnit5Basics03();
    assertArrayEquals(service.discardCommonPrefixOne("aaa", "aabb"),
                      service.discardCommonPrefixTwo("aaa", "aabb")
    );
  }

  private Function<Integer, String> strFN() {
    return (i) -> i < 0 ? "" : strFN().apply((i / 26) - 1) + (char) (65 + i % 26);
  }

//  private Function<Integer, String> str = Memoizer.memoize(strFN());


  @Test
  void test002() {
    IntStream
        .range(0, END_EXCLUSIVE_ZZZZ) //ZZZZ
        .mapToObj(value -> strFN().apply(value))
        .forEach(System.out::println);
  }

  @Test
  void test003() {

    final List<Pair<String, String>> result = IntStream
        .range(0, END_EXCLUSIVE_ZZZZ)
//        .parallel()
        .mapToObj(value -> strFN().apply(value))
        .flatMap((Function<String, Stream<Pair<String, String>>>) a -> IntStream
            .range(0, END_EXCLUSIVE_ZZZZ)
            .mapToObj(i -> new Pair<>(a, strFN().apply(i))))
        .filter(p -> {
          final JUnit5Basics03 service = new JUnit5Basics03();
          return !deepEquals(
              service.discardCommonPrefixOne(p.getT1(), p.getT2()),
              service.discardCommonPrefixTwo(p.getT1(), p.getT2())
          );
        })
        .peek(e -> logger().info(e.toString()))
        .collect(Collectors.toList());

    Assertions.assertTrue(result.isEmpty());


  }


}
