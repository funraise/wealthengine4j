package io.funraise.requests;

public class EmailMatchRequest extends MatchRequest {
    
    public String email;
 
    public boolean isValid() {
        return super.isValid() && notEmpty(email);
    }
}
