package com.github.tahaviev;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public final class IsOneOperationDiffTest {

    @ParameterizedTest
    @CsvSource(
        {
            "abc, abdc",
            "abc, abcd",
            "abc, dabc",
            "abc, ac",
            "abc, ab",
            "bc, abc",
            "abc, adc",
            "abc, abd",
            "abc, dbc",
        }
    )
    public void accept(final String before, final String after)
        throws Exception {
        MatcherAssert.assertThat(
            new IsOneOperationDiff(before, after).value(),
            Matchers.is(true)
        );
    }

    @ParameterizedTest
    @CsvSource(
        {
            "abc, abc",
            "abc, abcde",
            "abc, abdec",
            "abc, deabc",
            "abc, a",
            "abc, c",
            "abcd, ad",
            "abcd, aefd",
            "abcd, abef",
            "abcd, efcd",
        }
    )
    public void negate(final String before, final String after)
        throws Exception {
        MatcherAssert.assertThat(
            new IsOneOperationDiff(before, after).value(),
            Matchers.is(false)
        );
    }

}
