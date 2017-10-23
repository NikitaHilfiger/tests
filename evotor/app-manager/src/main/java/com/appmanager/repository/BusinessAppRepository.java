package com.appmanager.repository;

import com.appmanager.model.BusinessApp;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessAppRepository extends JpaRepository<BusinessApp, Long> {

  Optional<BusinessApp> findById(Long id);

  Optional<BusinessApp> findByPublisher(Long id);

  List<BusinessApp> findAllByPublisher(Long id);

}