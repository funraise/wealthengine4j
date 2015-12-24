package io.funraise.models;

public class PhoneMatchRequest extends MatchRequest {
    
    public String phone;
    
    public boolean validate() {
        return super.validate() && notEmpty(phone);
    }
}
