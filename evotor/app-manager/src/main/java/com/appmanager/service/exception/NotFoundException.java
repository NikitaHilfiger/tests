package com.appmanager.service.exception;


import com.appmanager.model.AppType;
import com.appmanager.model.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

  public NotFoundException(AppType type) {
    super("could not find type '" + type + "'.");
  }

  public NotFoundException(Publisher user) {
    super("could not find user '" + user.getLogin() + "'.");
  }

  public NotFoundException(String message) {
    super(message);
  }

}
