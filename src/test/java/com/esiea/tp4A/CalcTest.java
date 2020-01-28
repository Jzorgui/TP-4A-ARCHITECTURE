package com.esiea.tp4A;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CalcTest {

    private final Calc calc = new Calc();

    @ParameterizedTest
    @CsvSource({
        "1, 1, 2",
        "4, 7, 11"
    })
    void add_cases(int a, int b, int expectedResult) {
        assertThat(calc.add(a, b)).isEqualTo(expectedResult);
    }
}
