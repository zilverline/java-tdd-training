package com.zilverline.tdd.service;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import com.zilverline.tdd.domain.DueDilligenceParticipantService;
import com.zilverline.tdd.domain.InvestmentAccount;
import com.zilverline.tdd.domain.Liability;
import com.zilverline.tdd.domain.Participant;
import com.zilverline.tdd.domain.SignificantShareholderWithLiabiliesException;


public class AddParticipantToInvestmentAccountServiceTest {

    private AddParticipantToInvestmentAccountService subject;

    private DueDilligenceParticipantService dueDilligenceParticipantService;
    private InvestmentAccount investmentAccount;


    @Before
    public void setUp() {
        dueDilligenceParticipantService = mock(DueDilligenceParticipantService.class);

        subject = new AddParticipantToInvestmentAccountService(dueDilligenceParticipantService);

        investmentAccount = new InvestmentAccount();
        investmentAccount.addParticipant(new Participant("bank", 95));
    }

    @Test(expected=SignificantShareholderWithLiabiliesException.class)
    public void participant_with_liabilities_should_not_be_able_to_have_significant_share() {
        Participant participant = new Participant("bank2", 5);
        when(dueDilligenceParticipantService.dueDilligence(participant)).thenReturn(Arrays.asList(new Liability("test")));

        subject.execute(investmentAccount, participant);
    }

    @Test
    public void should_not_check_for_liabilities_when_participant_does_not_have_a_significant_share() {
        Participant participant = new Participant("bank", 4);
        subject.execute(investmentAccount, participant);

        verify(dueDilligenceParticipantService, never()).dueDilligence(participant);
    }

    @Test
    public void should_allow_significant_share_when_participant_does_not_have_any_liabilities() {
        Participant participant = new Participant("bank", 10);
        when(dueDilligenceParticipantService.dueDilligence(participant)).thenReturn(Collections.<Liability>emptyList());

        subject.execute(investmentAccount, participant);

        assertTrue("participant not added", investmentAccount.getParticipants().contains(participant));
    }
}
