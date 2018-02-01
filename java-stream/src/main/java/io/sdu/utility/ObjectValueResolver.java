package io.sdu.utility;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import java.util.Optional;
import java.util.function.Supplier;

public class ObjectValueResolver {
  public static <T> Optional<T> resolve(Supplier<T> resolver) {
    try {
      final T result = resolver.get();
      return ofNullable(result);
    } catch (final NullPointerException e) {
      return empty();
    }
  }
}
