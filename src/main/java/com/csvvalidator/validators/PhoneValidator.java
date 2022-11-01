package com.csvvalidator.validators;

import com.csvvalidator.validators.data.Line;
import com.csvvalidator.validators.data.Violation;

import java.util.Optional;

public class PhoneValidator implements Validator {

    @Override
    public Optional<Violation> validate(Line line) {
        return Optional.empty();
    }

}
