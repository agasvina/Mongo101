package com.lucareto.model;

public interface BeanConverter<T> {

    public T copyPropertyTo(T other);
    
}
