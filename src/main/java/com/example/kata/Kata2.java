package com.example.kata;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public record Kata2() {

  public int add(String... numbers) {
    return Optional.of(Stream.of(numbers)
            .filter(stream -> !stream.isEmpty())
            .flatMapToInt(stringNumber -> Arrays.stream(stringNumber.split(","))
                .mapToInt(Integer::valueOf))
            .sum())
        .orElse(0);


  }
}
//    return Arrays.stream(numbers).flatMap(stringNumber -> stringNumber.split(","))
//        Arrays.stream(numbers.split(","))
//            .filter(stream -> !stream.isEmpty())
//            .mapToInt(Integer::valueOf)
//            .sum())
//        .orElse(0);
//  }

//}
