package inauri.alex.library.management.common.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {
  RESOURCE_NOT_FOUND("Resource not found");

  private final String message;
}
