package com.appmanager.model;

import com.appmanager.model.views.BasicJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;


@NoArgsConstructor
@Accessors(chain = true)
@Data
@Table(name = "applications")
@Entity
public class BusinessApp {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id", length = 10, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Long id;

  @JsonView(BasicJsonView.class)
  @Column(name = "name", length = 20, nullable = false)
  private String name;

  @JsonView(BasicJsonView.class)
  @ManyToOne(optional = false)
  private AppType appType;

  @ManyToOne(optional = false)
  private Publisher publisher;

  @JsonView(BasicJsonView.class)
  @Column(name = "description", length = 20)
  private String description;

  public BusinessApp(String name, AppType appType, Publisher publisher, String description) {
    this.name = name;
    this.appType = appType;
    this.publisher = publisher;
    this.description = description;
  }
}
