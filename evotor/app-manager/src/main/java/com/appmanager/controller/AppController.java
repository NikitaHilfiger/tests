package com.appmanager.controller;

import com.appmanager.model.BusinessApp;
import com.appmanager.model.views.BasicJsonView;
import com.appmanager.service.BusinessAppService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(
    onConstructor = @__(@Autowired)
)
@RestController
@RequestMapping("/apps")
public class AppController {

  final private BusinessAppService businessAppService;

  @PostMapping("/create")
  @JsonView(BasicJsonView.class)
  public ResponseEntity<?> createApp(
      @RequestHeader String login,
      @RequestBody BusinessApp app
  ) {

    return ResponseEntity.ok(
        businessAppService.create(app, login)
    );
  }

  @PostMapping("/{id}/update")
  public ResponseEntity<?> update(
      @PathVariable Long id,
      @RequestHeader String login,
      @RequestBody BusinessApp app) {

    return ResponseEntity.ok(
        businessAppService.update(id, app, login)
    );
  }
}


