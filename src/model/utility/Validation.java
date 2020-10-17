package model.utility;

import java.util.Optional;

/**
 * @author manmohansingh
 * 17/10/2020 14:00
 *
 * [1] (Thompson, 2020)
 */

public class Validation<T> {
    private T object;
    private Errors errorMessage;

    public Validation(T object) {
        this.object = object;
        errorMessage = new Errors();
    }

    public Validation(Errors errorMessage) {
        this.errorMessage = errorMessage;
        this.object = null;
    }

    public Errors getErrorMessage() {
        return errorMessage;
    }

    public boolean noMessages() {
        return errorMessage.size() == 0;
    }

    public Optional<T> get() {
        return Optional.ofNullable(object);
    }

    public T getObject() {
        return object;
    }

    public boolean isPresent() {
        return object != null;
    }

    public boolean isEmpty() {
        return object == null;
    }

    public static String isPresent(String string, String errorMessage) {
        if(string == null || string.trim().length() == 0) {
            return errorMessage;
        }
        return null;
    }
}
