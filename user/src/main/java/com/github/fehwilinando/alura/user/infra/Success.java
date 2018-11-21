package com.github.fehwilinando.alura.user.infra;

interface Success<T> extends Result<T> {
    @Override
    default boolean isSuccess() {
        return true;
    }

    static <R> Result<R> succeed(R content) {
        return (Success<R>) () -> content;
    }
}
