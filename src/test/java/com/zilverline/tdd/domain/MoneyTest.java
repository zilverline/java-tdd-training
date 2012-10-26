package com.zilverline.tdd.domain;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;


public class MoneyTest {

    @Test
    public void should_add_amounts() {
        assertEquals("3+2 = 5", Money.fromEuros(5), Money.fromEuros(3).add(Money.fromEuros(2)));
    }

    @Test
    public void should_round_to_cents() {
        assertEquals(Money.fromEurosAndCents(3, 14), Money.fromAmount(new BigDecimal("3.1416"), RoundingMode.DOWN));
        assertEquals(Money.fromEurosAndCents(3, 15), Money.fromAmount(new BigDecimal("3.1416"), RoundingMode.UP));
    }

}
