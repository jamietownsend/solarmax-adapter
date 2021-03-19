package com.servebeer.please.solarmax.connector.exception;

public class SolarmaxException extends Exception {

    private static final long serialVersionUID = 1L;

    public SolarmaxException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SolarmaxException(final Throwable cause) {
        super(cause);
    }

    public SolarmaxException(final String message) {
        super(message);
    }
}
