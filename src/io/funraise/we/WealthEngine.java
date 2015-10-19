package io.funraise.we;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.funraise.we.Request.ApiRequest;
import io.funraise.we.Request.ApiRequest.MalformedRequestException;
import io.funraise.we.Request.MatchRequest;
import io.funraise.we.Response.BasicProfileMatch;
import io.funraise.we.Response.MatchResponse;


/**
* Wealth Engine API Wrapper
* 
* <P>Wrapper which allows interaction with the various Wealth Engine API methods
* for deriving the wealth and giving metrics of an individual. There is one method of note
* getProfile(...) which accepts a MatchRequest object. 
*  
* @author Jason Swenski, CTO - FUNRaise Inc.
* @version 1.0
*/
public class WealthEngine {

    public static final String PROD_URL = "https://api.wealthengine.com/v1/";
    public static final String SANDBOX_URL = "https://api-sandbox.wealthengine.com/v1/";
    
    private final String _apiKey;
    private final String _apiRoot;
    
    /**
     * <P>This constructor creates a WealthEngine object with which
     * you may interact with the API. It assumes you want the Wealth Engine
     * production environment 
     *
     * @param apiKey
     */
    public WealthEngine(String apiKey) {
        _apiKey = apiKey;
        _apiRoot = PROD_URL;  
    }
    
    /**
     * <P>This constructor creates a WealthEngine object with which
     * you may interact with the API. You can use the second argument
     * to tell it which environment to connect to using the constants
     * SANDBOX_URL or PROD_URL
     *
     * @param apiKey
     * @param environmentUrl
     */
    public WealthEngine(String apiKey, String environmentUrl) {
        _apiKey = apiKey;
        _apiRoot = environmentUrl;  
    }
    
    /**
     * <P>Calling this method with a MatchRequest object invokes one of the
     * Wealth Engine API endpoints based on what type of request you pass it.
     * 
     * <P>Calling with one of {AddressMatchRequest, EmailMatchRequest, PhoneMatchRequest}
     * will call the appropriate Wealth Engine endpoint and attempt to match the individual. 
     * 
     * <P>A Future<MatchResponse> is returned immediately to the caller which can be redeemed later. Contained 
     * within the MatchResponse is a BasicProfileMatch object which has all of the relevant data about
     * the individual if they are found by the API. 
     * @throws ApiRequest.MalformedRequestException if the request is missing required fields or the fields are invalid
     * @param request
     * @return Future<MatchResponse>
     */
    public Future<MatchResponse> getProfile(MatchRequest request) throws MalformedRequestException {
        
        if(!request.validate()) throw new ApiRequest.MalformedRequestException("The request has missing or invalid properties");
       
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Callable<MatchResponse> task = () -> {
            
            CloseableHttpClient httpclient = HttpClients.createDefault();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            final HttpPost httprequest = new HttpPost(_apiRoot + request.getEndpoint());
            httprequest.addHeader("Authorization", "APIKey " + _apiKey);
            httprequest.addHeader("Content-type","application/json");
            MatchResponse matchResponse = new MatchResponse();

            try {
                httprequest.setEntity(new StringEntity(mapper.writeValueAsString(request)));
                HttpResponse response = httpclient.execute(httprequest);
                matchResponse.statusCode = response.getStatusLine().getStatusCode();
                matchResponse.rawContent = EntityUtils.toString(response.getEntity());
                matchResponse.profileMatch = mapper.readValue(matchResponse.rawContent, BasicProfileMatch.class);
            
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            } finally {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } 
            return matchResponse;
        
        };
        return executor.submit(task);
    }
}
