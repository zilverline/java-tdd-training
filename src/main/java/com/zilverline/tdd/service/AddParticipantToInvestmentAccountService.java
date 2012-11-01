package com.zilverline.tdd.service;

import com.zilverline.tdd.adapter.DueDilligenceParticipantService;
import com.zilverline.tdd.domain.InvestmentAccount;
import com.zilverline.tdd.domain.Participant;
import com.zilverline.tdd.domain.SignificantShareholderWithLiabiliesException;

public class AddParticipantToInvestmentAccountService {

    private final DueDilligenceParticipantService dueDilligenceParticipantService;

    public AddParticipantToInvestmentAccountService(DueDilligenceParticipantService dueDilligenceParticipantService) {
        this.dueDilligenceParticipantService = dueDilligenceParticipantService;
    }

    public void execute(InvestmentAccount investmentAccount, Participant participant) throws SignificantShareholderWithLiabiliesException {
        throw new RuntimeException("TODO");
    }
}
