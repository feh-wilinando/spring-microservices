package com.github.fehwilinando.alura.user.infra;

import java.util.NoSuchElementException;

interface Fail<T> extends Result<T> {
    @Override
    default boolean isSuccess() {
        return false;
    }

    static <R> Result<R> failure() {
        return (Fail<R>) () -> { throw new NoSuchElementException("Failed result has no getContent"); };
    }
}
