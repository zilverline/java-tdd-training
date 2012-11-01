package com.zilverline.tdd.adapter;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

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
  public void todo() {
      fail("todo");
  }

  @Override
  public void handle(HttpRequest request, HttpResponse response, HttpContext context) throws HttpException, IOException {
    response.setEntity(new StringEntity(IOUtils.toString(new ClassPathResource("liabilities.json").getInputStream())));
  }
}
