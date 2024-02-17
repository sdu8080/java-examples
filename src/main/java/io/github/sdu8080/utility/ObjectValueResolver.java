package io.github.sdu8080.utility;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import java.util.Optional;
import java.util.function.Supplier;

public class ObjectValueResolver {
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return ofNullable(result);
        } catch (NullPointerException e) {
            return empty();
        }
    }
}
