package com.github.sdu8080.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {

  public static void main(String[] args) {
    String[] strArray = {"foo", "bar", "blah"};

    // iterate through the array
    Arrays.stream(strArray).forEach(s -> {
      System.out.println(s);
    });

    // map to convert each element to upper case
    List<String> list = Arrays.stream(strArray).filter(v -> !v.equals("bar"))
        .map(String::toUpperCase).collect(Collectors.toList());
    System.out.println(list);

    // use predicate class to replace lambda function
    List<String> list2 = Arrays.stream(strArray).filter(new MyPredicate<>())
        .map(new MyFunction()).collect(Collectors.toList());
    System.out.println(list2);

  }

  static class MyPredicate<T> implements Predicate<T> {
    @Override
    public boolean test(T t) {
      System.out.println("inside predicate t=" + t);
      return !t.equals("bar");
    }
  }
  
  static class MyFunction implements Function<String, String>{
    @Override
    public String apply(String t) {
      System.out.println("inside function: " +t);
      return t.toUpperCase().replace('O', '$');
    }
  }
}
