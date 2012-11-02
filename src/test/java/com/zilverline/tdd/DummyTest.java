/*
 * Copyright 2012 SURFnet bv, The Netherlands
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zilverline.tdd;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import com.zilverline.tdd.adapter.DueDilligenceParticipantService;
import com.zilverline.tdd.domain.Liability;
import com.zilverline.tdd.domain.Participant;

/**
 * DummyTest.java
 *
 */
public class DummyTest {
  
  @Test
  public void test_mock_versus_anonymous_instantiation() {
    Participant participant = new Participant();
    final Collection<Liability> liabilities = 
          Arrays.asList(new Liability());
    //use mocking
    DueDilligenceParticipantService service =
        mock(DueDilligenceParticipantService.class);
    when(service.dueDilligence(participant)).thenReturn(liabilities);
    //use anonymous class
    DueDilligenceParticipantService service2 = 
          new DueDilligenceParticipantService() {
      @Override
      public Collection<Liability> dueDilligence(Participant participant) {
        return liabilities;
      }
    };
  
  } 
  
}
