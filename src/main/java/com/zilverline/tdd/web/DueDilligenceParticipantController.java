package com.zilverline.tdd.web;

import static java.util.Arrays.asList;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zilverline.tdd.domain.Liability;

/**
 * DueDilligenceParticipantColtroller.java
 * 
 */
@Controller
public class DueDilligenceParticipantController {

  @RequestMapping(value = "duedilligence/{bankAccountNumber}", method = RequestMethod.GET, produces = "application/json")
  @ResponseBody
  Collection<Liability> dueDilligence(String bankAccountNumber) {
    return asList(new Liability("Bankruptcy"), new Liability("Bankruptcy again"));
  }

}
