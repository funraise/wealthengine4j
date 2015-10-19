package io.funraise.we.Request;

public class AddressMatchRequest extends MatchRequest {
    
    public String address1;
    public String state;
    public String zip;
    
    public boolean validate() {
        return super.validate() &&
               address1 != null && !address1.isEmpty() &&
               state != null && !state.isEmpty() &&
               zip != null && !zip.isEmpty() &&
               zip.length() == 5 && state.length() == 2 && 
               zip.matches("\\d+");
    }
    
    static {
        _endpoint = "profile/find_one/by_address/basic";
    }

}
