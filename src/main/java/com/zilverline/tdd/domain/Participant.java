package com.zilverline.tdd.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Participant {
    @Id
    private Long id;

    @Basic
    private long shares;

    @Basic
    private String bankAccountNumber;

    private Money balance = Money.ZERO;

    public Participant() {
    }

    public Participant(long shares) {
        this.shares = shares;
    }

    public long getShares() {
        return shares;
    }

    public Money getBalance() {
        return balance;
    }

    public void addBalance(Money amount) {
        balance = balance.add(amount);
    }

    public void resetBalance() {
        balance = Money.ZERO;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }
}
