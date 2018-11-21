package com.github.fehwilinando.alura.user.infra;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Result<T> {
    boolean isSuccess();

    T getContent();

    default boolean isFail() {
        return !isSuccess();
    }

    default <R> Result<R> onSuccessGet(@NotNull  Supplier<R> mapping) {

        if (mapping == null || isFail()) {
            return Result.fail();
        }


        return Optional.ofNullable(mapping.get())
                    .map(Result::success)
                        .orElseGet(Result::fail);
    }

    default <R> Result<R> onSuccessMap(@NotNull Function<T, R> mapping) {

        if (mapping == null || isFail()) {
            return Result.fail();
        }

        return Optional.ofNullable(mapping.apply(getContent()))
                    .map(Result::success)
                        .orElseGet(Result::fail);
    }

    default T orIfFailGet(Supplier<T> onFail) {

        if (isSuccess()) {
            return getContent();
        }

        return onFail.get();
    }

    static <R> Result<R> success(R content) {
        return Success.succeed(content);
    }

    static <R> Result<R> fail() {
        return Fail.failure();
    }
}
