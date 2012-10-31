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

/**
 * CachedJpaHibernateDaoTest.java
 * 
 */
public class CachedJpaHibernateDaoTest {

	private CachedJpaHibernateDao dao;

	private ParticipantDao delegate;

	@Before
	public void before() {
		delegate = mock(ParticipantDao.class);
		dao = new CachedJpaHibernateDao();
		dao.setDelegate(delegate);
	}

	/**
	 * Test method for
	 * {@link com.zilverline.tdd.dao.CachedJpaHibernateDao#findParticipantsWithMinimumBalanceOf(com.zilverline.tdd.domain.Money)}
	 * .
	 * @throws InterruptedException
	 */
	@Test
	public void testFindParticipantsWithMinimumBalanceOf() throws InterruptedException {
		Money euro10 = Money.fromEuros(10);
		when(delegate.findParticipantsWithMinimumBalanceOf(euro10)).thenReturn(
				Arrays.asList(new Participant("1234", 15)));
		List<Participant> participants = dao.findParticipantsWithMinimumBalanceOf(euro10);
		assertEquals("1234", participants.get(0).getBankAccountNumber());

		when(delegate.findParticipantsWithMinimumBalanceOf(euro10)).thenThrow(
				new RuntimeException("cache should have kicked in"));

		//participants = dao.findParticipantsWithMinimumBalanceOf(euro10);
		//assertEquals("1234", participants.get(0).getBankAccountNumber());
	}
}
