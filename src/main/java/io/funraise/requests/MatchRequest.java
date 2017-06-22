package io.funraise.requests;

/**
 * Created by jason on 6/22/17.
 */
public abstract class MatchRequest {

    public String first_name;
    public String last_name;

    public boolean isValid() {
        return notEmpty(first_name) && notEmpty(last_name);
    }

    protected boolean notEmpty(String val) {
        return val != null && !val.trim().isEmpty();
    }
}
