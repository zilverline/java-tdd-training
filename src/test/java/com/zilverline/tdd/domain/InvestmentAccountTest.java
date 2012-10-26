package com.zilverline.tdd.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class InvestmentAccountTest {

    private InvestmentAccount subject;

    @Before
    public void setUp() {
        subject = new InvestmentAccount(Money.fromEuros(10));
    }

    @Test
    public void should_distribute_full_amount_to_sole_participant() {
        Participant participant = new Participant("1234", 1);
        subject.addParticipantShares(participant);

        subject.distributeBalance();

        assertEquals(Money.fromEuros(10), participant.getBalance());
        assertEquals(Money.fromEuros(0), subject.getBalance());
    }

    @Test
    public void should_distribute_balance_according_to_participant_shares() {
        Participant participant1 = new Participant("1234",3);
        Participant participant2 = new Participant("1234",1);

        subject.addParticipantShares(participant1);
        subject.addParticipantShares(participant2);

        subject.distributeBalance();

        assertEquals(Money.fromEurosAndCents(7, 50), participant1.getBalance());
        assertEquals(Money.fromEurosAndCents(2, 50), participant2.getBalance());
        assertEquals(Money.ZERO, subject.getBalance());
    }

    @Test
    public void should_keep_remainder_in_account() {
        Participant participant1 = new Participant("1234",2);
        Participant participant2 = new Participant("1234",1);

        subject.addParticipantShares(participant1);
        subject.addParticipantShares(participant2);

        subject.distributeBalance();

        assertEquals(Money.fromEurosAndCents(6, 66), participant1.getBalance());
        assertEquals(Money.fromEurosAndCents(3, 33), participant2.getBalance());
        assertEquals(Money.fromCents(1), subject.getBalance());
    }

    @Test
    public void should_not_distribute_when_amount_per_share_is_less_than_one_cent() {
        Participant participant = new Participant("1234",1001);
        subject.addParticipantShares(participant);

        subject.distributeBalance();

        assertEquals(Money.fromEuros(0), participant.getBalance());
        assertEquals(Money.fromEuros(10), subject.getBalance());
    }
}
