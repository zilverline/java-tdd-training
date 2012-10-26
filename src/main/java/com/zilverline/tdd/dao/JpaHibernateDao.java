package com.zilverline.tdd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.zilverline.tdd.domain.Money;
import com.zilverline.tdd.domain.Participant;

@Repository
public class JpaHibernateDao implements ParticipantDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Participant> findParticipantsWithMinimumBalanceOf(Money minimumBalance) {
        return entityManager
                .createQuery("select p from Participant p where p.balance >= :minimumBalance order by p.id", Participant.class)
                .setParameter("minimumBalance", minimumBalance)
                .getResultList();
    }
}
