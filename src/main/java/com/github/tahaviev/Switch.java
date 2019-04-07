package com.github.tahaviev;

import java.util.function.Function;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.func.FuncOf;
import org.cactoos.iterable.Filtered;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.FirstOf;

public final class Switch<X, Y> extends ScalarEnvelope<Y> {

    public interface Case<X, Y> extends Predicate<X>, Function<X, Y> {

        @RequiredArgsConstructor
        final class Of<X, Y> implements Switch.Case<X, Y> {

            private final Predicate<X> predicate;

            private final Func<X, Y> function;

            public Of(
                final Predicate<X> predicate,
                final Scalar<Y> scalar) {
                this(predicate, new FuncOf<>(scalar));
            }

            @Override
            public boolean test(final X input) {
                return this.predicate.test(input);
            }

            @Override
            @SneakyThrows
            public Y apply(final X input) {
                return this.function.apply(input);
            }

        }

    }

    public Switch(
        final X target,
        final Iterable<? extends Switch.Case<X, Y>> cases,
        final Scalar<Y> fallback
    ) {
        super(
            new FirstOf<>(
                new FuncOf<>(true),
                new Mapped<>(
                    it -> it.apply(target),
                    new Filtered<>(
                        it -> it.test(target),
                        cases
                    )
                ),
                fallback
            )
        );
    }

}
