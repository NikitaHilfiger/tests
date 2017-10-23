package com.appmanager.repository;

import com.appmanager.model.AppVersion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {

  Optional<AppVersion> findById(Long id);

  List<AppVersion> findAllByBusinessApp(Long appId);

}