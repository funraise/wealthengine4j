package io.funraise.we.Request;

public class AddressMatchRequest extends MatchRequest {
	
	public String address1;
	public String state;
	public String zip;
	static {
		_endpoint = "profile/find_one/by_address/basic";
	}

}
