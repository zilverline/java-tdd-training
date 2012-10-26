package com.zilverline.tdd.service;

import java.util.Collection;

import javax.inject.Inject;

import com.zilverline.tdd.domain.Participant;
import com.zilverline.tdd.domain.PaymentProcessor;

public class PayoutService {

    private final PaymentProcessor paymentProcessor;

    @Inject
    public PayoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void pay(Collection<Participant> participants) {
        for (Participant participant : participants) {
            paymentProcessor.pay(participant.getBankAccountNumber(), participant.getBalance());
            participant.resetBalance();
        }
    }
}
