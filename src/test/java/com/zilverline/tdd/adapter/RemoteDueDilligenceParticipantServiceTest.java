package com.zilverline.tdd.adapter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import com.zilverline.tdd.domain.Liability;
import com.zilverline.tdd.domain.Participant;

/**
 * RemoteDueDilligenceParticipantServiceTest.java
 * 
 */
public class RemoteDueDilligenceParticipantServiceTest implements HttpRequestHandler {

  private static LocalTestServer localTestServer;
  private static RemoteDueDilligenceParticipantService service;

  @BeforeClass
  public static void beforeClass() throws Exception {
    localTestServer = new LocalTestServer(null, null);
    localTestServer.start();
    String uri = String.format("http://%s:%d", localTestServer.getServiceAddress().getHostName(), localTestServer.getServiceAddress()
        .getPort());
    service = new RemoteDueDilligenceParticipantService(uri);
  }

  @Before
  public void before() {
    // we can't do this statically
    localTestServer.register("/*", this);
  }
  @Test
  public void test() {
    Collection<Liability> liabilities = service.dueDilligence(new Participant("P1234",0));
    assertEquals(2, liabilities.size());
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * org.apache.http.protocol.HttpRequestHandler#handle(org.apache.http.HttpRequest
   * , org.apache.http.HttpResponse, org.apache.http.protocol.HttpContext)
   */
  @Override
  public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
    response.setEntity(new StringEntity(IOUtils.toString(new ClassPathResource("liabilities.json").getInputStream())));
  }

}
