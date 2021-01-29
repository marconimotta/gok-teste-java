package br.com.gok.templateapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestException extends APIException {
  private static final long serialVersionUID = 1L;
  
  public BadRequestException(Throwable cause) {
    super(cause);
  }

  public BadRequestException(ErrorMessage error) {
    super(error);
  }

  public BadRequestException(String error) {
    super(error);
  }

}
