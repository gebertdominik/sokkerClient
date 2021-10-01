package com.gebert.sokker;

import com.gebert.sokker.services.PlayerService;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.xml.sax.SAXException;

@Component
public class SokkerController {


  private PlayerService playerFetcher;

  SokkerController(final PlayerService playerFetcher) {
    this.playerFetcher = playerFetcher;
  }

  public void logIn() throws IOException, ParserConfigurationException, SAXException {
    final Document document = playerFetcher.fetch("", "");
    final DocumentType doctype = document.getDoctype();


  }



}
