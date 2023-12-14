package com.pozhenskii.task1;

import java.time.LocalDate;

public class DateImplementation implements SomeInterface<LocalDate> {
    private LocalDate currentDate;

    @Override
    public LocalDate getData() {
        return null;
    }

    @Override
    public boolean validate(LocalDate data) {
        return false;
    }

    public boolean validate(String Date) {
        return true;
    }
}
