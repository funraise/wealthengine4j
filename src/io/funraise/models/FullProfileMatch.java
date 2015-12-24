package io.funraise.models;

import java.util.List;

public class FullProfileMatch extends BasicProfileMatch {
    
     public static class Identity extends BasicProfileMatch.Identity{
        
        public static class MaritalStatus {
            public String value;
            public String text;
        }
        
        public static class Email {
            public String email;
        }
        
        public List<Email> emails;
    }
     
    public static class Demographics {
        public boolean has_children;
    }
    
    public static class Relationship {
        public static class Spouse {
            public String first_name;
            public String full_name;
            public String last_name;
            public String middle_name;
            public String suffix;
        }
        
        public Spouse spouse;
    }
    
    public static class Wealth extends BasicProfileMatch.Wealth {
        
        public static class CashOnHand extends MinMax {}
        
        public static class BusinessOwnership extends MinMax {
             public static class BusinessSalesVolume extends MinMax{}
        }
        
        public static class TotalStock extends MinMax {}
        public static class StockHoldingsDirect extends MinMax {}
        public static class StockHoldingsIndirect extends MinMax {}
        public static class InvestableAssets extends MinMax {}
        public static class TotalAssets extends MinMax {}
        public static class TotalPensions extends MinMax {}
        
        public TotalStock total_stock;
        public StockHoldingsDirect stock_holdings_direct;
        public StockHoldingsIndirect stock_holdings_;
        public InvestableAssets investable_assets;
        public TotalAssets total_assets;
        public TotalAssets total_pensions;
        
        public CashOnHand cash_on_hand;
        public BusinessOwnership business_ownership;
    }
    
    public static class Giving extends BasicProfileMatch.Giving {
        public static class AffiliationInclination {
            public String text;
            public String value;
        }
        
        public static class PlannedGiving {
            public String text;
            public String value;
        }
        
        public static class InfluenceRating {
            public String text;
            public String value;
        }
        
        public static class CharitableDonations extends MinMax {}
        public static class TotalPoliticalDonations extends MinMax {}
        
        public List<PlannedGiving> planned_giving;
        public AffiliationInclination afilliation_inclination;
        public InfluenceRating influence_rating;
        public CharitableDonations charitable_donations;
        public TotalPoliticalDonations total_political_donations;
    }
    
    public static class Locations extends BasicProfileMatch.Locations {
        public String personal_phone;
    }
    
    public static class Professional {
        public boolean board_member;
    }
    
    public static class Vehicles {
        public static class Ownership {
            public String text;
            public String value;
        }
        public Ownership ownership;
    }
    
    public static class Jobs {
        
        public static class OrgType {
            public String text;
            public String value;
        }
        
        public String org_name;
        public OrgType org_type;
        public String title;
        public String phone;
        public String email;
        public Address address;
    }
    
    
    
    public Relationship relationship;
    public Demographics demographics;
    public Professional professional;
    public Vehicles vechicles;
    public List<Jobs> jobs;

}
