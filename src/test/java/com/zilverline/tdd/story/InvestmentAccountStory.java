package com.zilverline.tdd.story;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.zilverline.tdd.domain.InvestmentAccount;
import com.zilverline.tdd.domain.Money;
import com.zilverline.tdd.domain.Participant;


public class InvestmentAccountStory extends AbstractStory {

    private InvestmentAccount subject;
    
    @BeforeScenario
    public void setUp() {
        subject = new InvestmentAccount(Money.fromEuros(10));
    }
    
    @Given("Participant $bankAccountNumber with $number shares")
    public void participant_with_shares(String bankAccountNumber, Long shares) {
        subject.addParticipantShares(new Participant(bankAccountNumber, shares));
    }

    @When("the balance is distributed")
    public void distribute_balance() {
        subject.distributeBalance();
    }
    
    @Then("Participant $bankAccountNumber has $euro euros and $cents cents")
    public void checkBalance(String bankAccountNumber, Integer euros, Integer cents) {
      assertThat(getParticipant(bankAccountNumber).getBalance(), is(Money.fromEurosAndCents(euros, cents)));
    }
    
    private Participant getParticipant(String bankAccountNumber) {
      for (Participant p : subject.getParticipants()) {
        if (p.getBankAccountNumber().equalsIgnoreCase(bankAccountNumber)) {
          return p;
        }
      }
      throw new RuntimeException(String.format("No participant found for bankAcount '%s'",bankAccountNumber));
    }
   
}
