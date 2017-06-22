package io.funraise.requests;

public class PhoneMatchRequest extends MatchRequest {
    
    public String phone;
    
    public boolean isValid() {
        return super.isValid() && notEmpty(phone);
    }
}
