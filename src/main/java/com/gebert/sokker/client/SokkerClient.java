package com.gebert.sokker.client;


import feign.Headers;
import feign.Response;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "sokkerClient", url = "https://sokker.org/")
public interface SokkerClient {

  @PostMapping(value = "/start.php?session=xml")
  ResponseEntity<String> logIn(@RequestParam String ilogin, @RequestParam String ipassword);

  @GetMapping(value = "/xml/players-4573.xml")
  Response getPlayers(@RequestHeader String authToken);

}

