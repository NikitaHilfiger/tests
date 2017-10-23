package com.appmanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@Accessors(chain = true)
@Data
@Entity
@Table(name = "app_status")
public class AppVersionStatus {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id", length = 6, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Long id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  public AppVersionStatus(String name) {
    this.name = name;
  }

}