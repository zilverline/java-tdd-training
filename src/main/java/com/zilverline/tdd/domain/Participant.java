package com.zilverline.tdd.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Participant {
    @Id
    @GeneratedValue
    private Long id;

    @Basic
    private long shares;

    @Basic
    private String bankAccountNumber;

    @ManyToOne
    private InvestmentAccount investmentAccount;

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "balance"))
    private Money balance = Money.ZERO;

    public Participant() {
    }

    public Participant(String bankAccountNumber, long shares) {
        this.bankAccountNumber = bankAccountNumber;
        this.shares = shares;
    }

    public long getShares() {
        return shares;
    }

    @Transient
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

    public void setInvestmentAccount(InvestmentAccount investmentAccount) {
        this.investmentAccount = investmentAccount;
    }

    @Override
    public String toString() {
        return "Participant(id=" + id + ")";
    }

}
