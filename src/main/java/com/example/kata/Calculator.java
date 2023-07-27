package com.example.kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class Calculator {

  public Integer add(String input) throws Exception {

    Pair<List<String>, String> parsed = parse(input);

    List<String> delim = parsed.getLeft();
    String numbers = parsed.getRight();

    if (StringUtils.isNumeric(numbers)) {
      return Integer.parseInt(numbers);
    }
    if (numbers.isEmpty()) {
      return 0;
    }
    if (numbers.matches(".*[^0-9]")) {
      throw new Exception();
    }
    return tokenize(delim, numbers).sum();
  }

  private static IntStream tokenize(List<String> delim, String numbers) {
    return Arrays.stream(numbers.split(delim.toString()))
        .filter(s -> !s.isEmpty())
        .mapToInt(Integer::parseInt);
  }

  private Pair<List<String>, String> parse(String input) {
    List<String> delim = List.of(",", "\n");
    if (input.startsWith("//")) {
      delim = List.of(input.substring(2).split("\n")[0]);
      input = input.substring(2).split("\n")[1];
    }
    return Pair.of(delim, input);
  }
}
