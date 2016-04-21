package io.funraise.models;

import java.util.List;

public class BasicProfileMatch extends ProfileMatch {
    
    public static class Identity {
        
        public static class Gender {
            public String value;
            public String text;
        }
        
        public int age;
        public Gender gender;
        public String first_name;
        public String last_name;
        public String middle_name;
        public String full_name;
    }

    public static class Wealth {

        public static class NetWorth extends MinMax {}
        public static class TotalIncome extends MinMax {}
        public NetWorth networth;
        public TotalIncome total_income;
        public boolean accredited_investor;   
    }

    public static class Giving {
        
        public static class P2G {
            public String value;
            public String text;
        }

        public static class GiftCapacity extends MinMax {}
        public static class EstimatedAnnualDonations extends MinMax {}
        public P2G p2g_score;
        public GiftCapacity gift_capacity;
        public EstimatedAnnualDonations estimated_annual_donations;

    }

    public static class Address {

        public static class State {
            public String value;
            public String text;
        }

        public String street_line1;
        public String street_line2;
        public String street_line3;
        public String city;
        public State state;
        public String postal_code;

    }
    
    public static class Locations {
        public Address address;
    }

    public static class RealEstate {

        public static class TotalValue extends MinMax {}
        public int total_num_properties;
        public TotalValue total_realestate_value;
    }

    public Long id;
    public Identity identity;
    public Wealth wealth;
    public Giving giving;
    public List<Locations> locations;
    public RealEstate realestate;
    
}
