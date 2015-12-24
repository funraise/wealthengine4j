package io.funraise.models;

public class AddressMatchRequest extends MatchRequest {
    
    public String address_line1;
    public String city;
    public String state;
    public String zip;
  
    public boolean validate() {
    
        return super.validate() &&
               notEmpty(address_line1) &&
               notEmpty(state) &&
               notEmpty(city) &&
               notEmpty(zip) &&
               zip.length() == 5 && state.length() == 2 && 
               zip.matches("\\d+");
    }
}
