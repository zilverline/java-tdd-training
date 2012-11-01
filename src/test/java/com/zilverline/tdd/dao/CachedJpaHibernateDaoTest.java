package com.zilverline.tdd.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.zilverline.tdd.domain.Money;
import com.zilverline.tdd.domain.Participant;

public class CachedJpaHibernateDaoTest {

    private CachedJpaHibernateDao dao;

    private ParticipantDao delegate;

    @Before
    public void before() {
        delegate = mock(ParticipantDao.class);
        dao = new CachedJpaHibernateDao();
        dao.setDelegate(delegate);
    }

    @Test
    public void testFindParticipantsWithMinimumBalanceOf() throws InterruptedException {
        Money euro10 = Money.fromEuros(10);
        when(delegate.findParticipantsWithMinimumBalanceOf(euro10)).thenReturn(Arrays.asList(new Participant("1234", 15)));
        List<Participant> participants = dao.findParticipantsWithMinimumBalanceOf(euro10);
        assertEquals("1234", participants.get(0).getBankAccountNumber());

        when(delegate.findParticipantsWithMinimumBalanceOf(euro10)).thenThrow(new RuntimeException("cache should have kicked in"));

        // participants = dao.findParticipantsWithMinimumBalanceOf(euro10);
        // assertEquals("1234", participants.get(0).getBankAccountNumber());
    }
}
