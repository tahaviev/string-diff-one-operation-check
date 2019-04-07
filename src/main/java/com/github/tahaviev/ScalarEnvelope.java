package com.github.tahaviev;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.cactoos.Scalar;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class ScalarEnvelope<T> implements Scalar<T> {

    private final Scalar<T> origin;

    @Override
    public final T value() throws Exception {
        return this.origin.value();
    }

}
