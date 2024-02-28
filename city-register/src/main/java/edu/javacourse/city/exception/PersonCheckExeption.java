package edu.javacourse.city.exception;

public class PersonCheckExeption extends Exception {
    public PersonCheckExeption() {
    }

    public PersonCheckExeption(String message) {
        super(message);
    }

    public PersonCheckExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonCheckExeption(Throwable cause) {
        super(cause);
    }

    public PersonCheckExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
