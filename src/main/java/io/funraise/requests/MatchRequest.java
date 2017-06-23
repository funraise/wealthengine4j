package io.funraise.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by jason on 6/22/17.
 */
public abstract class MatchRequest {

    public String first_name;
    public String last_name;

    @JsonIgnore
    public boolean isValid() {
        return notEmpty(first_name) && notEmpty(last_name);
    }

    @JsonIgnore
    protected boolean notEmpty(String val) {
        return val != null && !val.trim().isEmpty();
    }
}
