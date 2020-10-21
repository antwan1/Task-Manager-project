package model.utility;

import java.util.Collection;
import java.util.Optional;

/**
 * This class is designed to handle the results of data validation after error handling such that
 * when the data is not valid, no object is returned; instead applicable error message is returned.
 * And if the object is valid applicable object is returned.
 *
 * @author manmohansingh
 * 17/10/2020 14:00
 *
 * ********************************************************************
 * Title: TemperatureRecording
 * Author: Thompson, E (@thompel1)
 * Date: 2020
 * Code Version: N/A
 * Availability: https://gitlab.com/FoOOSD/temperaturerecording.git
 * ********************************************************************
 * [Source Code] https://gitlab.com/FoOOSD/temperaturerecording.git
 *
 */

public class Validation<T> {
    private T object;
    private Errors errorMessage;

    /**
     * Constructor used to return a valid object.
     *
     * @param object a valid object
     */
    public Validation(T object) {
        this.object = object;
        errorMessage = new Errors();
    }

    /**
     * this constructor returns applicable error message when the object is not valid.
     * Thus, no object is returned(i.e., null is returned)
     *
     * @param errorMessage stores the specific error message
     */
    public Validation(Errors errorMessage) {
        this.errorMessage = errorMessage;
        this.object = null;
    }

    /**
     * method used to gather the error messages in the data entry (if applicable)
     *
     * @return empty errorMessage string if no errors found, otherwise error message(s) are returned.
     */
    public Collection<? extends String> getErrorMessage() {
        return errorMessage;
    }

    public boolean noMessages() {
        return errorMessage.size() == 0;
    }

    /**
     * This returns an Optional<T> object in order to conform with functional
     * style thinking.
     *
     * @return An Optional<T> object
     */
    public Optional<T> get() {
        return Optional.ofNullable(object);
    }

    /**
     * This returns the object or null if no object exists. <b>Note: </b> this is added just to
     * reduce coding when you want the object reference rather than use the optional features.
     *
     * @return a T object
     */
    public T getObject() {
        return object;
    }

    /**
     * If a Person object is present, returns {@code true}, otherwise {@code false}.
     *
     * @return {@code true} if a Person object is present, otherwise {@code false}
     */
    public boolean isPresent() {
        return object != null;
    }

    /**
     * If a Person object is not present, returns {@code true}, otherwise
     * {@code false}.
     *
     * @return  {@code true} if a Person object is not present, otherwise {@code false}
     */
    public boolean isEmpty() {
        return object == null;
    }

    /**
     * Function to verify whether a string is present and return an error message if it is not.
     *
     * @param string the string that should be present
     * @param errorMessage the error message to be used if it is not present
     * @return return and empty string if the string being validated exists and the error message
     * if it does not.
     */
    public static String isPresent(String string, String errorMessage) {
        if(string == null || string.trim().length() == 0) {
            return errorMessage;
        }
        return null;
    }

}
