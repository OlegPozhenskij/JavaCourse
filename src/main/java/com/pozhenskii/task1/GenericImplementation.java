package com.pozhenskii.task1;

public class GenericImplementation<T> implements SomeInterface<T>{
    protected T data;

    @Override
    public T getData() {
        return null;
    }

    @Override
    public boolean validate(T data) {
        return false;
    }
}
