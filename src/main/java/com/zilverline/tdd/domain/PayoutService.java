package com.zilverline.tdd.domain;

import java.util.Collection;

import javax.inject.Inject;

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
