package ch.townsend.jamie.electro.solarmax.exception;

public class SolarMaxException extends Exception {

    public SolarMaxException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public SolarMaxException(final String message) {
        super(message);
    }
}
