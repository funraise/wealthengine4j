package io.funraise.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmailMatchRequest extends MatchRequest {
    
    public String email;

    @JsonIgnore
    public boolean isValid() {
        return super.isValid() && notEmpty(email);
    }
}
