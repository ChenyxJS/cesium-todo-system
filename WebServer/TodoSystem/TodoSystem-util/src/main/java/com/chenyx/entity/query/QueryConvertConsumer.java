package com.chenyx.entity.query;
@FunctionalInterface
public interface QueryConvertConsumer<T> {
    Object convert(T t);
}
