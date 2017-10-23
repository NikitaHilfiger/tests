package com.appmanager.service.exception;

import com.appmanager.model.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccessException extends RuntimeException {

  public AccessException(Publisher user) {
    super("no permission for '" + user + "'.");
  }

}
