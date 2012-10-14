package com.zilverline.tdd.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Embeddable;

import com.google.common.base.Preconditions;

@Embeddable
public class Money implements Comparable<Money> {

    private static final int SCALE = 2;
    private static final BigDecimal CENTS_PER_EURO = BigDecimal.ONE.scaleByPowerOfTen(SCALE);

    public static final Money ZERO = fromCents(0);

    private final BigDecimal amount;

    public static Money fromEuros(long euros) {
        return fromAmount(BigDecimal.valueOf(euros), RoundingMode.UNNECESSARY);
    }

    public static Money fromCents(long cents) {
        return new Money(BigDecimal.valueOf(cents).divide(CENTS_PER_EURO, 2, RoundingMode.UNNECESSARY));
    }

    public static Money fromEurosAndCents(int euros, int cents) {
        return fromEuros(euros).add(fromCents(cents));
    }

    public static Money fromAmount(BigDecimal amount, RoundingMode roundingMode) {
        return new Money(amount.setScale(SCALE, roundingMode));
    }

    private Money(BigDecimal amount) {
        Preconditions.checkArgument(amount.scale() == SCALE, "Incorrect scale {}", amount.scale());
        this.amount = amount;
    }

    public Money add(Money that) {
        return new Money(this.amount.add(that.amount));
    }

    public Money subtract(Money that) {
        return new Money(this.amount.subtract(that.amount));
    }

    public Money multiply(long multiplicand, RoundingMode roundingMode) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(multiplicand)).setScale(SCALE, roundingMode));
    }

    public Money divide(long divisor, RoundingMode roundingMode) {
        return new Money(this.amount.divide(BigDecimal.valueOf(divisor), SCALE, roundingMode));
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Money that) {
        return this.amount.compareTo(that.amount);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + amount.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money that = (Money) obj;
        return this.amount.equals(that.amount);
    }

    @Override
    public String toString() {
        return "Money [amount=" + amount + "]";
    }
}
