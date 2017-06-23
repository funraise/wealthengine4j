package io.funraise.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PhoneMatchRequest extends MatchRequest {
    
    public String phone;

    @JsonIgnore
    public boolean isValid() {
        return super.isValid() && notEmpty(phone);
    }
}
