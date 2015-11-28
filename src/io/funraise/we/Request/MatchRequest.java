package io.funraise.we.Request;

public abstract class MatchRequest extends ApiRequest {
    
    public String first_name;
    public String last_name;
  
    public boolean validate() {
        return notEmpty(first_name) && notEmpty(last_name);
    }
    
    protected boolean notEmpty(String v) {
    	return v != null && !v.isEmpty();
    }
}