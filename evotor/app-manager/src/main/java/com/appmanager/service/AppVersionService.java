package com.appmanager.service;

import com.appmanager.model.AppVersionStatus;
import com.appmanager.model.AppVersion;
import com.appmanager.model.BusinessApp;
import com.appmanager.repository.AppVersionRepository;
import com.appmanager.repository.AppVersionStatusRepository;
import com.appmanager.service.exception.NotFoundException;
import com.appmanager.service.exception.UniqueStatusException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(
    onConstructor = @__(@Autowired)
)
public class AppVersionService {

  private final AppVersionRepository appVersionRepository;
  private final AppVersionStatusRepository appVersionStatusRepository;
  private final BusinessAppService businessAppService;

  public Optional<AppVersion> findById(Long versionId) {
    return appVersionRepository.findById(versionId);
  }

  public AppVersion create(Long appId, String login, AppVersion appVersion) {
    return appVersionRepository.save(
        appVersion
            .setStatus(getDefaultStatus())
            .setBusinessApp(findAppAndValidateAccess(appId, login))
    );
  }

  public AppVersion update(
      Long appId,
      Long versionId,
      String login,
      AppVersion updateVersion
  ) {
    AppVersion appVersion = findVersionAndValidateAccess(
        appId,
        login,
        versionId
    );

    if (updateVersion.getStatus().getName().equals(getUniqueForOneAppStatus())) {

      findAllAppVersionByAppId(appId)
          .forEach(item -> {
            if (item.getStatus().getName().equals(getUniqueForOneAppStatus())) {
              throw new UniqueStatusException(
                  String.format(
                      "App[%s] has already been version with unique status[%s]",
                      updateVersion.getBusinessApp().getId(),
                      getDefaultStatus().getName()
                  )
              );
            }
          });
    }

    return appVersionRepository.save(
        updateVersion.setId(appVersion.getId())
    );
  }

  public BusinessApp findAppAndValidateAccess(Long appId, String login) {
    return businessAppService.findAppAndValidateAccess(appId, login);
  }

  public AppVersion findVersionAndValidateAccess(
      Long appId,
      String login,
      Long versionId
  ) {
    return findById(
        versionId
    ).map(
        appVersion -> {
          assert appVersion.getBusinessApp().equals(
              findAppAndValidateAccess(
                  appId,
                  login
              )
          );

          return appVersion;
        }
    ).orElseThrow(
        () -> new NotFoundException(
            String.format(
                "Cannot find appVersion [%s] ", versionId
            )
        )
    );
  }

  public Optional<AppVersionStatus> findStatusByName(String name) {
    return appVersionStatusRepository.findByName(name);
  }

  public List<AppVersion> findAllAppVersionByAppId(Long appId) {
    return appVersionRepository.findAllByBusinessApp(appId);
  }

  public AppVersionStatus getDefaultStatus() {
    return findStatusByName(Status.DRAFT.toString())
        .orElseThrow(
            () -> new NotFoundException(
                String.format("Cannot find status [%s]", Status.DRAFT.toString())
            )
        );
  }

  public AppVersionStatus getUniqueForOneAppStatus() {
    return findStatusByName(Status.PUBLISHED.toString())
        .orElseThrow(
            () -> new NotFoundException(
                String.format("Cannot find status [%s]", Status.PUBLISHED.toString())
            )
        );
  }

  public enum Status {

    PUBLISHED,

    DRAFT

  }
}
