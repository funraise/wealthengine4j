package io.funraise.we.Request;

public class EmailMatchRequest extends MatchRequest {
    
    public String email;
 
    public boolean validate() {
        return email != null && !email.isEmpty();
    }
    
    static {
        _endpoint = "profile/find_one/by_email/basic";
    }
}
