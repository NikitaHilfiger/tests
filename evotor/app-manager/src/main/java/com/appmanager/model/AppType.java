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
@Table(name = "app_type")
@Entity
public class AppType {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id", nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  public AppType(String name) {
    this.name = name;
  }
}
