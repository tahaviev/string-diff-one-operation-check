package com.github.tahaviev;

import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.False;

public final class IsOneOperationDiff extends ScalarEnvelope<Boolean> {

    public IsOneOperationDiff(final String before, final String after) {
        super(
            new Switch<>(
                new LengthDiff(before, after),
                new IterableOf<>(
                    new Switch.Case.Of<>(
                        new Equals<>(-1),
                        new IsOneCharRemoved(before, after)
                    ),
                    new Switch.Case.Of<>(
                        new Equals<>(0),
                        new IsOneCharReplaced(before, after)
                    ),
                    new Switch.Case.Of<>(
                        new Equals<>(1),
                        new IsOneCharAdded(before, after)
                    )
                ),
                new False()
            )
        );
    }

}
