# wealthengine4j a Wealth Engine Java SDK

A Java based wrapper for the Wealth Engine API: https://dev.wealthengine.com/api

## DEPENDENCIES

 + Apache HTTPClient
 + Jackson Core, Databind and Annotations
  

```java
 
    try {
 
        WealthEngine we = new WealthEngine("<Your API Key>");
        EmailMatchRequest emailRequest = new EmailMatchRequest();
        emailRequest.email = "someone@somewhere.com";
        emailRequest.first_name = "Joe";
        emailRequest.last_name = "Smith";
        Future<MatchResponse> futurebm = we.getBasicProfileByEmail(emailRequest);
         
        //You can of course do anything you want with Future<MatchResponse>
        MatchResponse response = futurebm.get();
        BasicProfileMatch basic = (BasicProfileMatch) response.profileMatch;
        
        String p2gscore = basic.profileMatch.giving.p2g_score.text;
   }
   catch(ApiRequest.MalformedRequestException | ExecutionException | InterruptedException e) {e.printStackTrace();}

```
