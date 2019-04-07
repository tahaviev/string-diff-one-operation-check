package com.github.tahaviev;

import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.cactoos.Scalar;

@RequiredArgsConstructor
public final class IsOneCharReplaced implements Scalar<Boolean> {

    private final String before;
    private final String after;

    @Override
    public Boolean value() {
        return IntStream
            .range(0, this.before.length())
            .boxed()
            .filter(
                index -> this.before.charAt(index) != this.after.charAt(index)
            )
            .findFirst()
            .map(
                mismatch -> this.after.substring(mismatch + 1).equals(
                    this.before.substring(mismatch + 1)
                )
            )
            .orElse(false);
    }

}
