package com.ssm.framework.exception;

public class CustomerEhCacheException extends RuntimeException {

    /** serialVersionUID */
    private static final long serialVersionUID = 7908103497733805206L;

    public CustomerEhCacheException() {
        super();
    }

    public CustomerEhCacheException(String message) {
        super(message);
    }

    public CustomerEhCacheException(Throwable throwable) {
        super(throwable);
    }

    public CustomerEhCacheException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
