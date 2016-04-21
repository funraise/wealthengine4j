package io.funraise.api;

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

import io.funraise.models.AddressMatchRequest;
import io.funraise.models.ApiRequest;
import io.funraise.models.EmailMatchRequest;
import io.funraise.models.MatchRequest;
import io.funraise.models.MatchResponse;
import io.funraise.models.PhoneMatchRequest;
import io.funraise.models.ProfileMatch;
import io.funraise.models.ApiRequest.MalformedRequestException;


/**
* Wealth Engine API Wrapper
* 
* <P>Wrapper which allows interaction with the various Wealth Engine API methods
* for deriving the wealth and giving metrics of an individual.
*  
* @author Jason Swenski, CTO - FUNRaise Inc.
* @version 1.1
*/
public class WealthEngine {

    public static final String PROD_URL = "https://api.wealthengine.com/v1/";
    public static final String SANDBOX_URL = "https://api-sandbox.wealthengine.com/v1/";
    public static final int DEFAULT_THREADS = 16;
    
    private static ExecutorService executor = null;
    private static CloseableHttpClient httpclient;
    
    private final String _apiKey;
    private final String _apiRoot;
    private int _numThreads;
    
    /**
    * <P>This constructor creates a WealthEngine object with which
    * you may interact with the API. It assumes you want the Wealth Engine
    * production environment 
    *
    * @param apiKey
    */
    public WealthEngine(String apiKey) {
        this(apiKey,PROD_URL);
    }
    
    /**
     * <P>This constructor creates a WealthEngine object with which
     * you may interact with the API. You can use the second argument
     * to tell it which environment to connect to using the constants
     * SANDBOX_URL or PROD_URL. 
     * 
     * @param apiKey
     * @param environmentUrl
     */
    public WealthEngine(String apiKey, String environmentUrl) {
        this(apiKey,environmentUrl,DEFAULT_THREADS);
    }
    
    /**
    * <P>This constructor creates a WealthEngine object with which
    * you may interact with the API. You can use the second argument
    * to tell it which environment to connect to using the constants
    * SANDBOX_URL or PROD_URL. The third parameter defines the number of threads
    * available to the thread executor 
    *
    * @param apiKey
    * @param environmentUrl
    * @param numThreads
    */
    public WealthEngine(String apiKey, String environmentUrl, int numThreads) {
        _apiKey = apiKey;
        _apiRoot = environmentUrl; 
        httpclient = HttpClients.createDefault();
        _numThreads = numThreads;
        initExecutor();
    }
    
    public void setClient(CloseableHttpClient client) {
        httpclient = client;
    }
    
    /**
     * <P>Sets the number of threads the underlying ExecutorService can use to 
     *    make requests against the Wealth Engine API. For large batches of 
     *    requests you might want to up this. The default is 1
     *
     * @param size
     */
    public void setThreadPoolSize(int size) {
        _numThreads = size;
    }
    
    /**
    * <P>Gets the number of threads the ExecutorService is using to process API requests
    *    
    * @returns the number of threads the ExecutorService is using to process API requests
    */
    public int getThreadPoolSize() {
        return _numThreads;
    }
    
    /**
     * <P> Sets up the underlying executor thread pool
     *
     */
    public void initExecutor() {
        executor = Executors.newFixedThreadPool(_numThreads);
    }
    
    /**
     * <P>Calling this method with an EmailMatchRequest object tries to return a BasicProfileMatch by first name, last name + email
     * 
     * <P>A Future<MatchResponse> is returned immediately to the caller which can be redeemed later. Contained 
     * within the MatchResponse is a ProfileMatch object which can be cast to a BasicProfileMatch or FullProfileMatch
     * depending on if you call getBasic* or getFull* methods
     * 
     * @throws ApiRequest.MalformedRequestException if the request is missing required fields or the fields are invalid
     * @param request
     * @return Future<MatchResponse>
     */
    public Future<MatchResponse> getBasicProfileByEmail(EmailMatchRequest request) throws MalformedRequestException {
        return getProfile(request,"profile/find_one/by_email/basic","io.funraise.models.BasicProfileMatch");
    }
    
    /**
     * <P>Calling this method with an PhoneMatchRequest object tries to return a BasicProfileMatch by first name, last name + phone
     * 
     * <P>A Future<MatchResponse> is returned immediately to the caller which can be redeemed later. Contained 
     * within the MatchResponse is a ProfileMatch object which can be cast to a BasicProfileMatch or FullProfileMatch
     * depending on if you call getBasic* or getFull* methods
     * 
     * @throws ApiRequest.MalformedRequestException if the request is missing required fields or the fields are invalid
     * @param request
     * @return Future<MatchResponse>
     */
    public Future<MatchResponse> getBasicProfileByPhone(PhoneMatchRequest request) throws MalformedRequestException {
        return getProfile(request,"profile/find_one/by_phone/basic","io.funraise.models.BasicProfileMatch");
    }
    
    /**
     * <P>Calling this method with an AddressMatchRequest object tries to return a BasicProfileMatch by first name, last name + address
     * 
     * <P>A Future<MatchResponse> is returned immediately to the caller which can be redeemed later. Contained 
     * within the MatchResponse is a ProfileMatch object which can be cast to a BasicProfileMatch or FullProfileMatch
     * depending on if you call getBasic* or getFull* methods
     * 
     * @throws ApiRequest.MalformedRequestException if the request is missing required fields or the fields are invalid
     * @param request
     * @return Future<MatchResponse>
     */
    public Future<MatchResponse> getBasicProfileByAddress(AddressMatchRequest request) throws MalformedRequestException {
        return getProfile(request,"profile/find_one/by_address/basic","io.funraise.models.BasicProfileMatch");
    }
    
    /**
     * <P>Calling this method with an AddressMatchRequest object tries to return a FullProfileMatch by first name, last name + address
     * 
     * <P>A Future<MatchResponse> is returned immediately to the caller which can be redeemed later. Contained 
     * within the MatchResponse is a ProfileMatch object which can be cast to a BasicProfileMatch or FullProfileMatch
     * depending on if you call getBasic* or getFull* methods
     * 
     * @throws ApiRequest.MalformedRequestException if the request is missing required fields or the fields are invalid
     * @param request
     * @return Future<MatchResponse>
     */
    public Future<MatchResponse> getFullProfileByAddress(AddressMatchRequest request) throws MalformedRequestException {
        return getProfile(request,"profile/find_one/by_address/full","io.funraise.models.FullProfileMatch");
    }
    
    /**
     * <P>Calling this method with an PhoneMatchRequest object tries to return a FullProfileMatch by first name, last name + phone
     * 
     * <P>A Future<MatchResponse> is returned immediately to the caller which can be redeemed later. Contained 
     * within the MatchResponse is a ProfileMatch object which can be cast to a BasicProfileMatch or FullProfileMatch
     * depending on if you call getBasic* or getFull* methods
     * 
     * @throws ApiRequest.MalformedRequestException if the request is missing required fields or the fields are invalid
     * @param request
     * @return Future<MatchResponse>
     */
    public Future<MatchResponse> getFullProfileByPhone(PhoneMatchRequest request) throws MalformedRequestException {
        return getProfile(request,"profile/find_one/by_phone/full","io.funraise.models.FullProfileMatch");
    }
    
    /**
     * <P>Calling this method with an EmailMatchRequest object tries to return a FullProfileMatch by first name, last name + email
     * 
     * <P>A Future<MatchResponse> is returned immediately to the caller which can be redeemed later. Contained 
     * within the MatchResponse is a ProfileMatch object which can be cast to a BasicProfileMatch or FullProfileMatch
     * depending on if you call getBasic* or getFull* methods
     * 
     * @throws ApiRequest.MalformedRequestException if the request is missing required fields or the fields are invalid
     * @param request
     * @return Future<MatchResponse>
     */
    public Future<MatchResponse> getFullProfileByEmail(EmailMatchRequest request) throws MalformedRequestException {
        return getProfile(request,"profile/find_one/by_email/full","io.funraise.models.FullProfileMatch");
    }
    
    
    //Does most of the work
    private Future<MatchResponse> getProfile(MatchRequest request, final String endpoint, String matchTypeImpl) throws MalformedRequestException {
        
        if(!request.validate()) throw new ApiRequest.MalformedRequestException("The request has missing or invalid properties");
       
        
        Callable<MatchResponse> task = () -> {
            
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            final HttpPost httprequest = new HttpPost(_apiRoot + endpoint);
            httprequest.addHeader("Authorization", "APIKey " + _apiKey);
            httprequest.addHeader("Content-type","application/json");
            MatchResponse matchResponse = new MatchResponse();

            try {
                httprequest.setEntity(new StringEntity(mapper.writeValueAsString(request)));
                                
                HttpResponse response = httpclient.execute(httprequest);
                matchResponse.statusCode = response.getStatusLine().getStatusCode();
                matchResponse.rawContent = EntityUtils.toString(response.getEntity());          
                matchResponse.profileMatch = (ProfileMatch) mapper.readValue(matchResponse.rawContent, Class.forName(matchTypeImpl));
            
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
