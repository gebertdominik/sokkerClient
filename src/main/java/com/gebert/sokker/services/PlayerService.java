package com.gebert.sokker.services;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Service
public class PlayerService {

  private static final String PLAYERS_URL = "https://sokker.org/xml/players-4573.xml";

  private final SessionService sessionFetcher;

  public PlayerService(final SessionService sessionFetcher) {
    this.sessionFetcher = sessionFetcher;
  }

  public Document fetch(String iLogin, String iPassword)
      throws IOException, ParserConfigurationException, SAXException {

    ResponseEntity<String> response = makeRequest(iLogin, iPassword);
    Document doc = makeDocument(response);

    return doc;
  }


  private Document makeDocument(ResponseEntity<String> response)
      throws ParserConfigurationException, SAXException, IOException {
    String xmlResponse = response.getBody();

    DocumentBuilderFactory factory =
        DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    ByteArrayInputStream inputStream = new ByteArrayInputStream(
        xmlResponse.getBytes());
    return builder.parse(inputStream);
  }

  private ResponseEntity<String> makeRequest(String iLogin, String iPassword) {
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = sessionFetcher.getSessionHeader(iLogin, iPassword);
    String url = String.format(PLAYERS_URL, 4573);
    HttpEntity entity = new HttpEntity(headers);
    return restTemplate.exchange(
        url, HttpMethod.GET, entity, String.class);
  }
}