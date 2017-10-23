package com.appmanager.service;

import com.appmanager.model.Publisher;
import com.appmanager.repository.PublisherRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(
    onConstructor = @__(@Autowired)
)
public class PublisherService {

  private final PublisherRepository publisherRepository;

  public Publisher save(Publisher publisher) {
    return publisherRepository.save(
        publisher
    );
  }

  public Optional<Publisher> findByLogin(String login) {
    return publisherRepository.findByLogin(
        login
    );
  }

  public Optional<Publisher> findById(Long id) {
    return publisherRepository.findById(
        id
    );
  }

  public Publisher create(Publisher publisher) {
    return save(publisher);
  }

  public Publisher update(Publisher updatePublisher, String login) {
    return findByLogin(
        login
    ).map(
        publisher -> {
          updatePublisher.setId(publisher.getId());
          updatePublisher.setLogin(publisher.getLogin());

          return publisherRepository.save(updatePublisher);
        }
    ).orElseThrow(
        () -> new IllegalArgumentException(
            String.format(
                "Cannot find publisher by login [%s] ", login
            )
        )
    );
  }
}
