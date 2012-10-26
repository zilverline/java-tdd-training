package com.zilverline.tdd.domain;

import java.util.Collection;


public class SignificantShareholderWithLiabiliesException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Participant participant;
    private final Collection<Liability> liabilities;

    public SignificantShareholderWithLiabiliesException(Participant participant, Collection<Liability> liabilities) {
        super("significant shareholder with liabilities");
        this.participant = participant;
        this.liabilities = liabilities;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Collection<Liability> getLiabilities() {
        return liabilities;
    }
}
