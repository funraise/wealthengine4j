package io.funraise.we.Response;

import java.util.List;

public class BasicProfileMatch {
    
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

        public static class NetWorth {

            public int min;
            public int max;
            public int value;
            public String text;
            public String text_low;
            public String text_high;

        }

        
        public static class Income {
            public int min;
            public int max;
            public int value;
            public String text;
            public String text_low;
            public String text_high;
        }

        public NetWorth networth;
        public Income total_income;
        public boolean accredited_investor;   
    }

    
    public static class Giving 
    {
        public static class P2G {
            public int value;
            public String text;
        }

        public static class GiftCapacity {
           
            public int min;
            public int max;
            public int value;
            public String text;
            public String text_low;
            public String text_high;
        }

        
        public static class EstimatedAnnualDonations {
            public int min;
            public int max;
            public int value;
            public String text;
            public String text_low;
            public String text_high;
        }

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

    
    public static class RealEstate {

        public static class TotalValue {
            public int min;
            public int max;
            public int value;
            public String text;
            public String text_low;
            public String text_high;
        }

        public int total_num_properties;
        public TotalValue total_realestate_value;

    }

    public int id;
    public Identity identity;
    public Wealth wealth;
    public Giving giving;
    public List<Address> locations;
    public RealEstate realestate;
    
}
