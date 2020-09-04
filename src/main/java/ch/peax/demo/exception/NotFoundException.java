package ch.peax.demo.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

  private final Object value;

  public NotFoundException(String message, Object value) {
    super(message);
    this.value = value;
  }
}
