# A WealthEngine Java SDK made with love by [Funraise](https://funraise.io)

<p align="center">
<img src="https://funraise.io/wp-content/uploads/2016/12/funny_green.png" width="200">
</p>

<p align="center">
<a href='https://bintray.com/jswenski/wealthengine4j/wealthengine4j/_latestVersion'><img src='https://api.bintray.com/packages/jswenski/wealthengine4j/wealthengine4j/images/download.svg'></a>
<img src="https://img.shields.io/badge/language-java-orange.svg" alt="Language: Java">
<img src="https://travis-ci.org/jmswenski/wealthengine4j.svg?branch=master" alt="Build">
<img src="https://img.shields.io/badge/license-Apache-000000.svg" alt="License">
</p>


## What is [WealthEngine](http://www.wealthengine.com/products-services/products/screen)?

WealthEngine profiles are proprietary wealth ratings and scores leveraging wealth intelligence
which provides deeper insight into a potential prospect. These profiles allow you to build marketing
and business development strategies. This insight is useful to not only identify
your best customers, but to uncover those individuals who may show
potential for additional business.

WealthEngine profiles contain information on an individual’s net worth, income, assets,
real estate, stock holding, charitable contributions and other financial
related data as well as business and personal contact information.

Funraise uses this library to derive key metrics for Donors in their SaaS Non-Profit Fundraising Platform.

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

 ## Installation

 Binary distributions for wealthengine4j are hosted on [Bintray](https://bintray.com/jswenski/wealthengine4j/wealthengine4j/).

```xml
<dependency>
  <groupId>io.funraise.wealthengine4j</groupId>
  <artifactId>wealthengine4j</artifactId>
  <version>2.0.4</version>
  <type>pom</type>
</dependency>
```


 ## Example Usage

```java

WealthEngine we = new WealthEngine("<Your API Key>");

EmailMatchRequest request = new EmailMatchRequest();
request.email = "someone@somewhere.com";
request.first_name = "Joe";
request.last_name = "Smith";

we.getBasicProfile(request, response -> {
    String p2gscore = basic.profileMatch.giving.p2g_score.text;
});

```

