package io.funraise.we.Request;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class MatchRequest {
	
	public String first_name;
	public String last_name;
	
	@JsonIgnore
	protected static String _endpoint = null;
	
	@JsonIgnore
	public String getEndpoint() {
		return _endpoint;
	}
	
	public void validate() {
		
	}
}