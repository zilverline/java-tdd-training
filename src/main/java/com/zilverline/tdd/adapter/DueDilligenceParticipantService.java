package com.zilverline.tdd.adapter;

import java.util.Collection;

import com.zilverline.tdd.domain.Liability;
import com.zilverline.tdd.domain.Participant;

/**
 * DueDilligenceParticipantService.java
 *
 */
public interface DueDilligenceParticipantService {

   Collection<Liability> dueDilligence(Participant participant);
  
  
}
