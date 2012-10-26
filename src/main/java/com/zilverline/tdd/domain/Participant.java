package com.zilverline.tdd.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public boolean isSignificantShareholder() {
        BigDecimal limit = new BigDecimal("0.05");
        long totalShares = investmentAccount.getTotalShares();
        BigDecimal share = BigDecimal.valueOf(shares).divide(BigDecimal.valueOf(totalShares), 2, RoundingMode.DOWN);
        return share.compareTo(limit) >= 0;
    }

    @Override
    public String toString() {
        return "Participant(id=" + id + ")";
    }

}
