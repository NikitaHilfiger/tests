package com.appmanager.config;

import com.appmanager.model.AppVersionStatus;
import com.appmanager.model.AppType;
import com.appmanager.model.AppVersion;
import com.appmanager.model.BusinessApp;
import com.appmanager.model.Publisher;
import com.appmanager.repository.AppVersionStatusRepository;
import com.appmanager.repository.AppTypeRepository;
import com.appmanager.repository.AppVersionRepository;
import com.appmanager.repository.BusinessAppRepository;
import com.appmanager.repository.PublisherRepository;
import com.appmanager.service.AppVersionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@AllArgsConstructor(
    onConstructor = @__(@Autowired)
)
@Component
public class DataLoader implements ApplicationRunner {

  private PublisherRepository publisherRepository;
  private AppVersionStatusRepository appVersionStatusRepository;
  private AppTypeRepository appTypeRepository;
  private BusinessAppRepository businessAppRepository;
  private AppVersionRepository appVersionRepository;
  private AppVersionService appVersionService;

  public void run(ApplicationArguments args) {
    loadPublishersData();
    loadAppStatusData();
    loadAppTypeData();
    loadApplicationsData();
    loadAppVersionData();
  }

  public void loadPublishersData() {
    publisherRepository.save(new Publisher("all77", "Alise", "Jackson"));
    publisherRepository.save(new Publisher("ddd", "Nastya", "Varnova"));
  }

  public void loadAppStatusData() {
    appVersionStatusRepository.save(new AppVersionStatus("DRAFT"));
    appVersionStatusRepository.save(new AppVersionStatus("TEST"));
    appVersionStatusRepository.save(new AppVersionStatus("PUBLISHED"));
    appVersionStatusRepository.save(new AppVersionStatus("UNPUBLISHED"));
    appVersionStatusRepository.save(new AppVersionStatus("DELETED"));
  }

  public void loadAppTypeData() {
    appTypeRepository.save(new AppType("ANDROID"));
    appTypeRepository.save(new AppType("CLOUD"));
  }

  public void loadApplicationsData() {
    businessAppRepository.save(
        new BusinessApp(
            "Telegram",
            appTypeRepository.findByName("CLOUD").orElseThrow(
                IllegalStateException::new
            ),
            publisherRepository.findOne(
                1L
            ),
            "desc"
        )
    );

    businessAppRepository.save(
        new BusinessApp(
            "VK",
            appTypeRepository.findByName("CLOUD").orElseThrow(
                IllegalStateException::new
            ),
            publisherRepository.findOne(
                2L
            ),
            "YYYY"
        )
    );
  }

  public void loadAppVersionData() {
    appVersionRepository.save(
        new AppVersion(
            businessAppRepository.findOne(1L),
            1.0,
            appVersionService.getDefaultStatus()
        )
    );

    appVersionRepository.save(
        new AppVersion(
            businessAppRepository.findOne(1L),
            2.0,
            appVersionService.getDefaultStatus()
        )
    );
  }
}
