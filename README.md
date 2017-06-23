# wealthengine4j: A Wealth Engine Java SDK by [Funraise](https://funraise.io)

<p align="center">
<img src="https://funraise.io/wp-content/uploads/2016/12/funny_green.png" width="200">
</p>

<p align="center">
<img src="https://img.shields.io/badge/language-java-orange.svg" alt="Language: Java">
<img src="https://travis-ci.org/jmswenski/wealthengine4j.svg?branch=master" alt="Build">
<img src="https://img.shields.io/badge/license-Apache-000000.svg" alt="License">
</p>


## What is [Wealth Engine](http://www.wealthengine.com/products-services/products/screen)?

Wealth Engine profiles are proprietary wealth ratings and scores.
Ratings and scores, are developed leveraging wealth intelligence and provides deeper insight into a potential prospect.

WealthEngine’s ratings and scores give you insight on
your prospects, so you can build effective marketing and
business development strategies. This insight is useful to not only identify
your best customers, but to uncover those individuals who may show
potential for additional business.

Wealth Engine profiles contain information on an individual’s net worth, income, assets,
real estate, stock holding, charitable contributions and other financial
related data as well as business and personal contact information.

Some key indicators include:

+ Propensity to Give
+ Propensity to Spend
+ Estimated Spending Capacity
+ Total Assets
+ Net Worth
+ Cash on Hand
+ Estimated Annual Donations
+ Gift Capacity Range
+ Gift Capacity Rating
+ RFM
+ Planned Giving—Bequest, Annuity
+ Trust
+ Influence
+ Inclination: Giving
+ Inclination: Affiliation


## DEPENDENCIES

 + Retrofit2
 + Retrofit2 Jackson Converters


 ## Example

```java

    WealthEngine we = new WealthEngine("<Your API Key>");

    EmailMatchRequest emailRequest = new EmailMatchRequest();
    emailRequest.email = "someone@somewhere.com";
    emailRequest.first_name = "Joe";
    emailRequest.last_name = "Smith";

    BasicProfile basic = we.getBasicProfileByEmail(emailRequest);
    String p2gscore = basic.profileMatch.giving.p2g_score.text;


```
