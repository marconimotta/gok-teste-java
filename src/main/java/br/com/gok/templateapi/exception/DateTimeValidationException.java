package br.com.gok.templateapi.exception;

public class DateTimeValidationException extends APIException {
	private static final long serialVersionUID = 1L;
  
  public DateTimeValidationException(Throwable cause) {
    super(cause);
  }

  public DateTimeValidationException(ErrorMessage error) {
    super(error);
  }

  public DateTimeValidationException(String error) {
    super(error);
  }



}
