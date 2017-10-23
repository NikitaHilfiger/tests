package com.appmanager.repository;

import com.appmanager.model.AppVersionStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppVersionStatusRepository extends JpaRepository<AppVersionStatus, Long> {

  Optional<AppVersionStatus> findByName(String name);

}