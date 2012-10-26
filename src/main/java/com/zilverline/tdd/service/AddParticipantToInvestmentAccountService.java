package com.zilverline.tdd.service;

import java.util.Collection;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.zilverline.tdd.domain.DueDilligenceParticipantService;
import com.zilverline.tdd.domain.InvestmentAccount;
import com.zilverline.tdd.domain.Liability;
import com.zilverline.tdd.domain.SignificantShareholderWithLiabiliesException;
import com.zilverline.tdd.domain.Participant;

public class AddParticipantToInvestmentAccountService {

    private final DueDilligenceParticipantService dueDilligenceParticipantService;

    @Inject
    public AddParticipantToInvestmentAccountService(DueDilligenceParticipantService dueDilligenceParticipantService) {
        this.dueDilligenceParticipantService = dueDilligenceParticipantService;
    }

    @Transactional(rollbackFor=Exception.class)
    public void execute(InvestmentAccount investmentAccount, Participant participant) throws SignificantShareholderWithLiabiliesException {
        investmentAccount.addParticipant(participant);
        if (participant.isSignificantShareholder()) {
            Collection<Liability> liabilities = dueDilligenceParticipantService.dueDilligence(participant);
            if (!liabilities.isEmpty()) {
                investmentAccount.removeParticipant(participant);
                throw new SignificantShareholderWithLiabiliesException(participant, liabilities);
            }
        }
    }
}
