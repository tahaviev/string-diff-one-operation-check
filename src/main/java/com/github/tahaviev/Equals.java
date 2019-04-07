package com.github.tahaviev;

import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.cactoos.Scalar;

@RequiredArgsConstructor
public final class Equals<T> implements Predicate<Scalar<T>> {

    private final T target;

    @Override
    @SneakyThrows
    public boolean test(final Scalar<T> scalar) {
        return scalar.value().equals(this.target);
    }

}
