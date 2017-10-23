package com.appmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@Accessors(chain = true)
@Data
@Table(
    name = "app_version",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"business_app_id", "version"})}
)
@Entity
public class AppVersion {

  @Id
  @GeneratedValue(generator = "increment")
  @GenericGenerator(name = "increment", strategy = "increment")
  @Column(name = "id", length = 10, nullable = false)
  private Long id;

  @ManyToOne(optional = false)
  private BusinessApp businessApp;

  @Column(name = "version", length = 10, nullable = false)
  private Double version;

  @ManyToOne(optional = false)
  private AppVersionStatus status;

  public AppVersion(BusinessApp businessApp, double version, AppVersionStatus status) {
    this.businessApp = businessApp;
    this.version = version;
    this.status = status;
  }
}
