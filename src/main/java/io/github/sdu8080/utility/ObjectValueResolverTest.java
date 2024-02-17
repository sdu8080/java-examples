package io.github.sdu8080.utility;

import lombok.Data;

import java.util.Optional;


public class ObjectValueResolverTest {

    private static @Data
    class Inner {
        private String value;
    }

    private static @Data
    class Nested {
        private Inner inner;
    }

    private static @Data
    class Outer {
        private Nested nested;
    }

    public static void main(String[] args) {

        Outer outer = new Outer();
        Nested nested = new Nested();
        Inner inner = new Inner();
        inner.setValue("this is a string");
        nested.setInner(inner);
        outer.setNested(nested);

        // pass in the lambda function as the resolver, ObjectValueResolver will evaluate the entire function,
        // resolve.get(), will return the value or NPE, ObjectValueResolver will catch the NPE, then return an
        // empty optional class
        Optional<String> o = ObjectValueResolver.resolve(() -> outer.getNested().getInner().getValue());
        String value = o.isPresent() ? o.get() : "";

        System.out.println(value);

    }
}
