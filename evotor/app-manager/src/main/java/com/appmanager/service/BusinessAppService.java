package com.appmanager.service;

import com.appmanager.model.BusinessApp;
import com.appmanager.repository.BusinessAppRepository;
import com.appmanager.service.exception.NotFoundException;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(
    onConstructor = @__(@Autowired)
)
public class BusinessAppService {

  private final BusinessAppRepository businessAppRepository;
  private final PublisherService publisherService;


  public List<BusinessApp> findAppsByPublisherLogin(String login) {
    Long publisherId = publisherService.findByLogin(login)
        .orElseThrow(
            () -> new NotFoundException(
                String.format("Cannot find publisher by login [%s] " + login)
            )
        ).getId();

    return businessAppRepository.findAllByPublisher(publisherId);
  }

  public Optional<BusinessApp> findAppById(Long id) {
    return businessAppRepository.findById(id);
  }

  public BusinessApp create(BusinessApp app, String login) {
    return businessAppRepository.save(
        findAppAndValidateAccess(app.getId(), login)
            .setPublisher(publisherService.findByLogin(login)
                .orElseThrow(
                    () -> new IllegalArgumentException(
                        String.format("Cannot create app [%s] ", app.getId()
                        )
                    )
                )
            )
    );
  }

  public BusinessApp update(Long id, BusinessApp updateApp, String login) {
    return businessAppRepository.save(updateApp.setId(
        findAppAndValidateAccess(id, login).getId()
        )
    );
  }

  public BusinessApp findAppAndValidateAccess(Long id, String login) {
    BusinessApp app = findAppById(id)
        .orElseThrow(
            () -> new NotFoundException(
                String.format("Cannot find app [%s] ", id)
            )
        );
    app.getPublisher().getLogin().equals(login);

    return app;
  }
}
