package inauri.alex.library.management.common.exception;

import lombok.Getter;

import java.time.LocalDateTime;

import static inauri.alex.library.management.common.exception.ErrorCode.RESOURCE_NOT_FOUND;
import static inauri.alex.library.management.common.exception.ErrorType.*;

@Getter
public class LibraryException extends RuntimeException {

  private final ErrorType errorType;

  private final ErrorCode errorCode;

  private final LocalDateTime timestamp;

  public LibraryException(ErrorType errorType, ErrorCode errorCode) {
    super(errorCode.getMessage());
    this.errorType = errorType;
    this.errorCode = errorCode;
    timestamp = LocalDateTime.now();
  }

  public static LibraryException notFound() {
    return new LibraryException(NOT_FOUND, RESOURCE_NOT_FOUND);
  }

  public static LibraryException badRequest(ErrorCode errorCode) {
    return new LibraryException(BAD_REQUEST, errorCode);
  }

  public static LibraryException serverError(ErrorCode errorCode) {
    return new LibraryException(INTERNAL_SERVER_ERROR, errorCode);
  }
}
