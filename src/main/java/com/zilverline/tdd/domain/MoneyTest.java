package com.zilverline.tdd.domain;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;


public class MoneyTest {

    @Test
    public void should_add_amounts() {
        assertThat("3+2 = 5", Money.fromEuros(3).add(Money.fromEuros(2)), is(Money.fromEuros(5)));
    }

    @Test
    public void should_round_to_cents() {
        assertThat(Money.fromAmount(new BigDecimal("3.1416"), RoundingMode.DOWN), is(Money.fromEurosAndCents(3, 14)));
        assertThat(Money.fromAmount(new BigDecimal("3.1416"), RoundingMode.UP), is(Money.fromEurosAndCents(3, 15)));
    }

}
