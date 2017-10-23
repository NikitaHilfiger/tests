package com.appmanager.repository;

import com.appmanager.model.AppType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppTypeRepository extends JpaRepository<AppType, Long> {

  Optional<AppType> findByName(String name);

}