package com.example.kata;

import com.example.kata.validators.*;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public record PasswordValidation(PasswordValidator... validators) implements PasswordValidator {


    @Override
    public void validate(String password) throws IllegalArgumentException {

        List<String> errors = Arrays.stream(validators)
                .map(validator -> {
                    try {
                        validator.validate(password);
                    } catch (IllegalArgumentException e) {
                        return e.getMessage();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .toList();

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }
}
