package com.appmanager.controller;

import com.appmanager.model.Publisher;
import com.appmanager.model.views.BasicJsonView;
import com.appmanager.service.PublisherService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(
    onConstructor = @__(@Autowired)
)
@RestController
@RequestMapping("/publishers")
public class PublisherController {

  private final PublisherService publisherService;

  @JsonView(BasicJsonView.class)
  @PostMapping("/create")
  public ResponseEntity<?> create(@RequestBody Publisher publisher) {

    return ResponseEntity.ok(
        this.publisherService.create(publisher)
    );
  }

  @PostMapping("/update")
  public ResponseEntity<?> update(
      @RequestHeader String login,
      @RequestBody Publisher publisher
  ) {

    return ResponseEntity.ok(
        this.publisherService.update(publisher, login)
    );
  }
}
