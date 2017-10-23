package com.appmanager.model;

import com.appmanager.model.views.BasicJsonView;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;
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
@Table(name = "publishers")
@Entity
public class Publisher {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id", length = 10, nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private Long id;

  @JsonView(BasicJsonView.class)
  @Column(name = "login", unique = true, nullable = false, length = 20)
  @JsonProperty(access = Access.READ_ONLY)
  private String login;

  @JsonView(BasicJsonView.class)
  @Column(name = "first_name", length = 10, nullable = false)
  private String firstName;

  @JsonView(BasicJsonView.class)
  @Column(name = "last_name", length = 10, nullable = false)
  private String lastName;

  public Publisher(String login, String fName, String lName) {
    this.login = login;
    this.firstName = fName;
    this.lastName = lName;
  }

}
