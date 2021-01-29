package br.com.gok.templateapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenException extends APIException{
  private static final long serialVersionUID = 1L;

  public ForbiddenException(final Throwable cause) {
    super(cause);
  }

  public ForbiddenException(final ErrorMessage error) {
    super(error);
  }

  public ForbiddenException(final String error) {
    super(error);
  }
}
