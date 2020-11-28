package com.bachriwalid.worldgdp.exception;

import com.bachriwalid.worldgdp.model.Country;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.function.Supplier;

public class CountryNotFoundException extends RuntimeException implements Supplier<Exception> {
    public CountryNotFoundException(String message) {
        super(message);

    }

    @Override
    public Exception get() {
        return null;
    }
}
