package io.funraise;

/**
 * Created by jason on 6/22/17.
 */
public class WealthEngineException extends Exception {

    public WealthEngineException(String message) {
        super(message);
    }

    public WealthEngineException(String message, Throwable t) {
        super(message,t);
    }

}
