package com.zilverline.tdd.dao;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.joda.time.DateTime;

import com.zilverline.tdd.domain.Money;
import com.zilverline.tdd.domain.Participant;

/**
 * CachedJpaHibernateDao.java
 *
 */
public class CachedJpaHibernateDao implements ParticipantDao {

    private static final int CACHE_DURATION = 60;

    @Resource(name = "jpaHibernateDao")
    private ParticipantDao delegate;

    private final Map<Money, AbstractMap.SimpleEntry<DateTime, List<Participant>>> cache = new HashMap<Money, AbstractMap.SimpleEntry<DateTime, List<Participant>>>();

    @Override
    public List<Participant> findParticipantsWithMinimumBalanceOf(Money minimumBalance) {
        SimpleEntry<DateTime, List<Participant>> entry = cache.get(minimumBalance);
        // bug !!!
        if (entry == null || entry.getKey().isAfter(new DateTime())) {
            List<Participant> participants = delegate.findParticipantsWithMinimumBalanceOf(minimumBalance);
            entry = new SimpleEntry<DateTime, List<Participant>>(new DateTime().plusSeconds(CACHE_DURATION), participants);
            cache.put(minimumBalance, entry);
        }
        return entry.getValue();
    }

    public void setDelegate(ParticipantDao delegate) {
        this.delegate = delegate;
    }

}
