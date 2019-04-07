package com.github.tahaviev;

import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.cactoos.Scalar;

@RequiredArgsConstructor
public final class IsOneCharRemoved implements Scalar<Boolean> {

    private final String before;

    private final String after;

    @Override
    public Boolean value() {
        return IntStream
            .range(0, this.after.length())
            .boxed()
            .filter(
                index -> this.before.charAt(index) != this.after.charAt(index)
            )
            .findFirst()
            .map(
                mismatch -> this.before.substring(mismatch + 1).equals(
                    this.after.substring(mismatch)
                )
            )
            .orElseGet(() -> this.before.length() - this.after.length() == 1);
    }

}
