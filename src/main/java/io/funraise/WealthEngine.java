package io.funraise;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.funraise.requests.AddressMatchRequest;
import io.funraise.models.BasicProfile;
import io.funraise.requests.EmailMatchRequest;
import io.funraise.models.FullProfile;
import io.funraise.requests.PhoneMatchRequest;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
* Wealth Engine API Wrapper
* 
* <P>Wrapper which allows interaction with the various Wealth Engine API methods
* for deriving the wealth and giving metrics of an individual.
*  
* @author Jason Swenski, CTO - Funraise Inc.
* @version 2.0
*/
public class WealthEngine {

    public static final String PROD_URL = "https://api.wealthengine.com/v1/";
    public static final String SANDBOX_URL = "https://api-sandbox.wealthengine.com/v1/";

    private String _apiKey;
    private WealthEngineService service;
    
    /**
    * <P>This constructor creates a WealthEngine object with which
    * you may interact with the API. It assumes you want the Wealth Engine
    * production environment 
    *
    * @param apiKey
    */
    public WealthEngine(String apiKey) {
        this(apiKey,PROD_URL,null);
    }
    
    /**
    * <P>This constructor creates a WealthEngine object with which
    * you may interact with the API. You can use the second argument
    * to tell it which environment to connect to using the constants
    * SANDBOX_URL or PROD_URL. The third parameter defines the number of threads
    * available to the thread executor 
    *
    * @param apiKey your WealthEngine API key
    * @param environmentUrl the PROD/SANDBOX URL for WealthEngine
    */
    public WealthEngine(String apiKey, String environmentUrl, Interceptor interceptor) {
        _apiKey = apiKey;

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        if(interceptor != null) {
            builder.interceptors().add(interceptor);
        }
        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("APIKey", _apiKey)
                    .addHeader("Content-Type","application/json")
                    .build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.build();

        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(environmentUrl)
                                      .client(client)
                                      .addConverterFactory(JacksonConverterFactory.create())
                                      .build();

        service = retrofit.create(WealthEngineService.class);
    }


    /**
     * <P>Calling this method with an EmailMatchRequest object tries to return a BasicProfile by first name, last name + email
     *
     * <P>BasicProfile is returned immediately to the caller
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a EmailMatchRequest object
     * @return BasicProfile
     */
    public BasicProfile getBasicProfileByEmail(EmailMatchRequest request) throws WealthEngineException {
        try {
            if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
            return service.getBasicProfileByEmail(request).execute().body();
        }
        catch(IOException e) {
            throw new WealthEngineException("Failed to connect to API",e);
        }
    }

    /**
     * <P>Calling this method with an PhoneMatchRequest object tries to return a BasicProfile by first name, last name + phone
     *
     * <P>BasicProfile is returned immediately to the caller
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a PhoneMatchRequest object
     * @return BasicProfile
     *
     */
    public BasicProfile getBasicProfileByPhone(PhoneMatchRequest request) throws WealthEngineException {
        try {
            if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
            return service.getBasicProfileByPhone(request).execute().body();
        }
        catch (IOException e) {
            throw new WealthEngineException("Failed to connect to API",e);
        }
    }

    /**
     * <P>Calling this method with an AddressMatchRequest object tries to return a BasicProfile by first name, last name + address
     *    Only US addresses/zipcodes are valid here. In general WealthEngines API only returns info on
     *    USA persons
     *
     * <P>BasicProfile is returned immediately to the caller
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a AddressMatchRequest object
     * @return BasicProfile
     *
     */
    public BasicProfile getBasicProfileByAddress(AddressMatchRequest request) throws WealthEngineException {
        try {
            if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
            return service.getBasicProfileByAddress(request).execute().body();
        }
        catch (IOException e) {
            throw new WealthEngineException("Failed to connect to API",e);
        }
    }



    /**
     * <P>Calling this method with an EmailMatchRequest object tries to return a FullProfile by first name, last name + email
     *
     * <P>FullProfile is returned immediately to the caller
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a EmailMatchRequest object
     * @return FullProfile
     */
    public FullProfile getFullProfileByEmail(EmailMatchRequest request) throws WealthEngineException {
        try {
            if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
            return service.getFullProfileByEmail(request).execute().body();
        }
        catch(IOException e) {
            throw new WealthEngineException("Failed to connect to API",e);
        }
    }


    /**
     * <P>Calling this method with an PhoneMatchRequest object tries to return a FullProfile by first name, last name + phone
     *
     * <P>FullProfile is returned immediately to the caller
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a PhoneMatchRequest object
     * @return FullProfile
     *
     */
    public FullProfile getFullProfileByPhone(PhoneMatchRequest request) throws WealthEngineException {
        try {
            if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
            return service.getFullProfileByPhone(request).execute().body();
        }
        catch(IOException e) {
            throw new WealthEngineException("Failed to connect to API",e);
        }
    }


    /**
     * <P>Calling this method with an AddressMatchRequest object tries to return a FullProfile by first name, last name + address
     *    Only US addresses/zipcodes are valid here. In general WealthEngines API only returns info on
     *    USA persons
     *
     * <P>FullProfile is returned immediately to the caller
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a AddressMatchRequest object
     * @return FullProfile
     *
     */
    public FullProfile getFullProfileByAddress(AddressMatchRequest request) throws WealthEngineException {
        try {
            if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
            return service.getFullProfileByAddress(request).execute().body();
        }
        catch(IOException e) {
            throw new WealthEngineException("Failed to connect to API",e);
        }
    }
    
    

}
