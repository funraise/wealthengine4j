package io.funraise.we.Request;

public class PhoneMatchRequest extends MatchRequest {
	public String phone;
	static {
		_endpoint = "profile/find_one/by_phone/basic";
	}

}
