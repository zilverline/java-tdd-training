package com.zilverline.tdd.domain;

public interface PaymentProcessor {

    void pay(String bankAccountNumber, Money amount);

}
