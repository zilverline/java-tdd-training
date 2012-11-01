package com.zilverline.tdd.dao;

import java.util.List;

import com.zilverline.tdd.domain.Money;
import com.zilverline.tdd.domain.Participant;

public interface ParticipantDao {

    List<Participant> findParticipantsWithMinimumBalanceOf(Money minimumBalance);
}
