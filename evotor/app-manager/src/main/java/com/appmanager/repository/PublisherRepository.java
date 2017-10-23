package com.appmanager.repository;

import com.appmanager.model.Publisher;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

  Optional<Publisher> findById(long id);

  Optional<Publisher> findByLogin(String login);

}