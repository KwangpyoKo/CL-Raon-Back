package coLaon.ClaonBack.common.exception;

import java.time.LocalDateTime;

public class ExceptionDto {
    private final Integer errorCode;
    private final String message;
    private final LocalDateTime timeStamp;

    public ExceptionDto(ErrorCode errorCode, String message) {
        this.errorCode = errorCode.getCode();
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
}