package io.funraise.we.Request;

public class EmailMatchRequest extends MatchRequest {
    
    public String email;
    static {
        _endpoint = "profile/find_one/by_email/basic";
    }
}
