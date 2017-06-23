package io.funraise.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AddressMatchRequest extends MatchRequest {
    
    public String address_line1;
    public String address_line2;
    public String city;
    public String state;
    public String zip;

    @JsonIgnore
    public boolean isValid() {
    
        return super.isValid() &&
               notEmpty(address_line1) &&
               notEmpty(state) &&
               notEmpty(city) &&
               notEmpty(zip) &&
               zip.length() == 5 && state.length() == 2 && 
               zip.matches("\\d+");
    }
}
