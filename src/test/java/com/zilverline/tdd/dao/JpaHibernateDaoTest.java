package com.zilverline.tdd.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.zilverline.tdd.config.PersistenceConfig;
import com.zilverline.tdd.domain.InvestmentAccount;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ TestDatabaseConfig.class, PersistenceConfig.class})
@TestExecutionListeners({TransactionalTestExecutionListener.class, DependencyInjectionTestExecutionListener.class})
@Transactional
public class JpaHibernateDaoTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    private JpaHibernateDao subject;

    private InvestmentAccount investmentAccount;

    @Before
    public void setUp() {
        investmentAccount = new InvestmentAccount();
        entityManager.persist(investmentAccount);
    }

    @Test
    public void todo() {
        fail("todo");
    }
}
