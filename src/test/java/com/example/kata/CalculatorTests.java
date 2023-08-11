package com.example.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CalculatorTests {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator(new Parser(), new Tokenizer());
//    ErrorMessage.errorMessages = new StringBuilder();
    }


    @ParameterizedTest
    @MethodSource("validInput")
    void call_add_withValidInput(String input, int output) {
        assertEquals(output, calculator.add(input));
    }

    static Stream<Arguments> validInput() {
        return Stream.of(
                Arguments.of("", 0),
                Arguments.of("1", 1),
                Arguments.of("1,2", 3),
                Arguments.of("456,7", 463),
                Arguments.of("1,2,2,3,4,7", 19),
                Arguments.of("1,2\n3", 6),
                Arguments.of("1\n2,3", 6),
                Arguments.of("//;\n1;3", 4),
                Arguments.of("//;\n1;3;5", 9),
                Arguments.of("//|\n1|2|3", 6),
                Arguments.of("//sep\n2sep5", 7),
                Arguments.of("//sep\nsep67sep98sep2sep34", 201)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidInput")
    void call_add_withInvalidInput(String input, String message) {
        Exception exception = assertThrows(NumberFormatException.class, () -> calculator.add(input));
        assertEquals(message, exception.getMessage());
    }

    static Stream<Arguments> invalidInput() {
        return Stream.of(
                Arguments.of("1,2,", "Separator at the end is not allowed."),
                Arguments.of("//;\n45;2;8;9;", "Separator at the end is not allowed."),
                Arguments.of("//sep\n1sep2,3", "'sep' expected but ',' found at position 3."),
                Arguments.of("//|\n1|2,3", "'|' expected but ',' found at position 3."),
                Arguments.of("//;\n45;2;8|9", "';' expected but '|' found at position 4."),
                Arguments.of("//;\n45;2;8|9;", "Separator at the end is not allowed.\n';' expected but '|' found at position 4.")
        );
    }


}