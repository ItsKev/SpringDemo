package ch.peax.demo.exception;

import java.util.Date;
import lombok.Data;

@Data
public class ExceptionResponse {

  private final Date timestamp;
  private final String message;
  private final String details;
}
