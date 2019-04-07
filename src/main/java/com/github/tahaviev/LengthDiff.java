package com.github.tahaviev;

import org.cactoos.scalar.StickyScalar;

public final class LengthDiff extends ScalarEnvelope<Integer> {

    public LengthDiff(final CharSequence before, final CharSequence after) {
        super(
            new StickyScalar<>(
                () -> after.length() - before.length()
            )
        );
    }

}
