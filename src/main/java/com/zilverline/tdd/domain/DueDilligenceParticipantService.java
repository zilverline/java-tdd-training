package com.zilverline.tdd.domain;

import java.util.Collection;

/**
 * DueDilligenceParticipantService.java
 *
 */
public interface DueDilligenceParticipantService {

   Collection<Liability> dueDilligence(Participant participant);
  
  
}
