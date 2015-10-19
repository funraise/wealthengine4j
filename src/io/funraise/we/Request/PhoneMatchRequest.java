package io.funraise.we.Request;

public class PhoneMatchRequest extends MatchRequest {
    
    public String phone;
    
    public boolean validate() {
        return phone != null && !phone.isEmpty();
    }
    
    static {
        _endpoint = "profile/find_one/by_phone/basic";
    }
}
