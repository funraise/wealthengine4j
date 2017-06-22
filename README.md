# wealthengine4j a Wealth Engine Java SDK

A Java based wrapper for the Wealth Engine API: https://dev.wealthengine.com/api

## DEPENDENCIES

 + Retrofit2
 + Retrofit2 Jackson Converters
  

```java
 
    try {
 
        WealthEngine we = new WealthEngine("<Your API Key>");

        EmailMatchRequest emailRequest = new EmailMatchRequest();
        emailRequest.email = "someone@somewhere.com";
        emailRequest.first_name = "Joe";
        emailRequest.last_name = "Smith";

        BasicProfile basic = we.getBasicProfileByEmail(emailRequest);
        String p2gscore = basic.profileMatch.giving.p2g_score.text;
   }
   catch(WealthEngineException e) {e.printStackTrace();}

```
