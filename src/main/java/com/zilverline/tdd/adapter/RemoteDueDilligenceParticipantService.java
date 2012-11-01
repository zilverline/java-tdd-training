package com.zilverline.tdd.adapter;

import java.io.IOException;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zilverline.tdd.domain.Liability;
import com.zilverline.tdd.domain.Participant;

/**
 * RemoteDueDilligenceParticipantService.java
 *
 */
public class RemoteDueDilligenceParticipantService implements DueDilligenceParticipantService {

  private HttpClient client = new DefaultHttpClient();

  private ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY).setSerializationInclusion(
      Include.NON_NULL);

  private String uri;

  private TypeReference<Collection<Liability>> typeReference = new TypeReference<Collection<Liability>>() {
  };

  public RemoteDueDilligenceParticipantService(String uri) {
    this.uri = uri;
  }

  @Override
  public Collection<Liability> dueDilligence(Participant participant) {
    try {
      return doDueDilligence(participant);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Collection<Liability> doDueDilligence(Participant participant) throws JsonParseException, JsonMappingException,
      IllegalStateException, IOException {
    HttpUriRequest request = new HttpGet(uri.concat("/").concat(participant.getBankAccountNumber()));
    HttpResponse response = client.execute(request);
    if (response.getStatusLine().getStatusCode() == 200) {
      return mapper.readValue(response.getEntity().getContent(), typeReference);
    } else {
      throw new RuntimeException(response.getAllHeaders().toString());
    }
  }
}
