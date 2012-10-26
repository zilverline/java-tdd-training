package com.zilverline.tdd.dao;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.zilverline.tdd.config.PersistenceConfig;
import com.zilverline.tdd.domain.Money;
import com.zilverline.tdd.domain.Participant;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ DatabaseConfig.class, PersistenceConfig.class})
@TestExecutionListeners({TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@Transactional
public class JpaHibernateDaoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private JpaHibernateDao subject;

    @Test
    public void test_find_minimum_balance() {
        Participant participant1 = givenParticipantWithBalance(Money.fromEuros(10));
        Participant participant2 = givenParticipantWithBalance(Money.fromEuros(5));

        List<Participant> actual = subject.findParticipantsWithMinimumBalanceOf(Money.fromEuros(8));

        assertEquals(Arrays.asList(participant1), actual);
    }

    @Test
    public void test_minimum_balance_zero() {
        Participant participant = givenParticipantWithBalance(Money.ZERO);

        List<Participant> actual = subject.findParticipantsWithMinimumBalanceOf(Money.ZERO);

        assertEquals(Arrays.asList(participant), actual);
    }

    private Participant givenParticipantWithBalance(Money balance) {
        Participant result = new Participant();
        result.addBalance(balance);
        entityManager.persist(result);
        return result;
    }
}
