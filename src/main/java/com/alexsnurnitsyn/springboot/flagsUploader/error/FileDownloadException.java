package com.alexsnurnitsyn.springboot.flagsUploader.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class FileDownloadException extends RuntimeException {

    public FileDownloadException() {
        super();
    }
    public FileDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
    public FileDownloadException(String message) {
        super(message);
    }
    public FileDownloadException(Throwable cause) {
        super(cause);
    }
}
