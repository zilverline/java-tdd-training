package com.zilverline.tdd.domain;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

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
        Participant participant = new Participant(1);
        subject.addParticipantShares(participant);

        subject.distributeBalance();

        assertThat(participant.getBalance(), is(Money.fromEuros(10)));
        assertThat(subject.getBalance(), is(Money.fromEuros(0)));
    }

    @Test
    public void should_distribute_balance_according_to_participant_shares() {
        Participant participant1 = new Participant(3);
        Participant participant2 = new Participant(1);

        subject.addParticipantShares(participant1);
        subject.addParticipantShares(participant2);

        subject.distributeBalance();

        assertThat(participant1.getBalance(), is(Money.fromEurosAndCents(7, 50)));
        assertThat(participant2.getBalance(), is(Money.fromEurosAndCents(2, 50)));
        assertThat(subject.getBalance(), is(Money.ZERO));
    }

    @Test
    public void should_keep_remainder_in_account() {
        Participant participant1 = new Participant(2);
        Participant participant2 = new Participant(1);

        subject.addParticipantShares(participant1);
        subject.addParticipantShares(participant2);

        subject.distributeBalance();

        assertThat(participant1.getBalance(), is(Money.fromEurosAndCents(6, 66)));
        assertThat(participant2.getBalance(), is(Money.fromEurosAndCents(3, 33)));
        assertThat(subject.getBalance(), is(Money.fromCents(1)));
    }

    @Test
    public void should_not_distribute_when_amount_per_share_is_less_than_one_cent() {
        Participant participant = new Participant(1001);
        subject.addParticipantShares(participant);

        subject.distributeBalance();

        assertThat(participant.getBalance(), is(Money.fromEuros(0)));
        assertThat(subject.getBalance(), is(Money.fromEuros(10)));
    }
}
