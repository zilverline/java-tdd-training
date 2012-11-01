package com.zilverline.tdd.domain;

import java.math.RoundingMode;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class InvestmentAccount {
    @Id @GeneratedValue
    private Long id;

    @OneToMany(cascade={CascadeType.PERSIST}, mappedBy="investmentAccount")
    private Set<Participant> participants = new HashSet<Participant>();

    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "balance"))
    private Money balance;

    public InvestmentAccount() {
        this(Money.ZERO);
    }

    public InvestmentAccount(Money initialBalance) {
        this.balance = initialBalance;
    }

    public void addParticipant(Participant participant) {
        participant.setInvestmentAccount(this);
        participants.add(participant);
    }

    public void removeParticipant(Participant participant) {
        if (participants.remove(participant)) {
            participant.setInvestmentAccount(null);
        }
    }

    public Money getBalance() {
        return balance;
    }

    public void addBalance(Money balance) {
        this.balance = this.balance.add(balance);
    }

    public void distributeBalance() {
        long totalShares = 0;
        for (Participant participation: participants) {
            totalShares += participation.getShares();
        }
        Money amountPerShare = balance.divide(totalShares, RoundingMode.DOWN);
        Money distributed = Money.ZERO;
        for (Participant participation: participants) {
            Money amount = amountPerShare.multiply(participation.getShares(), RoundingMode.UNNECESSARY);
            participation.addBalance(amount);
            distributed = distributed.add(amount);
        }
        balance = balance.subtract(distributed);
    }

    public Set<Participant> getParticipants() {
      return Collections.unmodifiableSet(participants);
    }

    public long getTotalShares() {
        long result = 0;
        for (Participant participant : participants) {
            result += participant.getShares();
        }
        return result;
    }

    @Override
    public String toString() {
        return "InvestmentAccount(id=" + id + ", balance=" + balance + ")";
    }


}