package br.com.gok.templateapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends APIException{
  private static final long serialVersionUID = 1L;

  public UnauthorizedException(final Throwable cause) {
    super(cause);
  }

  public UnauthorizedException(final ErrorMessage error) {
    super(error);
  }

  public UnauthorizedException(final String error) {
    super(error);
  }
}
