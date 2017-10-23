package com.appmanager.controller;

import com.appmanager.model.AppVersion;
import com.appmanager.service.AppVersionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(
    onConstructor = @__(@Autowired)
)
@RestController
@RequestMapping("/apps/{appId}")
public class AppVersionController {

  final private AppVersionService appVersionService;

  @RequestMapping("/create")
  public ResponseEntity<?> create(
      @PathVariable Long appId,
      @RequestHeader String login,
      @RequestBody AppVersion appVersion
  ) {
    return ResponseEntity.ok(
        appVersionService.create(appId, login, appVersion)
    );
  }

  @RequestMapping("/{versionId}/update")
  public ResponseEntity<?> update(
      @PathVariable Long appId,
      @PathVariable Long versionId,
      @RequestHeader String login,
      @RequestBody AppVersion appVersion
  ) {
    return ResponseEntity.ok(
        appVersionService.update(appId, versionId, login, appVersion)
    );
  }
}
