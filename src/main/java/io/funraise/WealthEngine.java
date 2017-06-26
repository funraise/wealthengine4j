package io.funraise;

import java.util.concurrent.TimeUnit;
import io.funraise.requests.AddressMatchRequest;
import io.funraise.models.BasicProfile;
import io.funraise.requests.EmailMatchRequest;
import io.funraise.models.FullProfile;
import io.funraise.requests.PhoneMatchRequest;
import io.funraise.requests.callbacks.BasicProfileCallback;
import io.funraise.requests.callbacks.FullProfileCallback;
import io.funraise.requests.callbacks.ProfileCallbackDelegate;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
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

    private final WealthEngineService service;

   /**
    * <P>This constructor creates a WealthEngine object with which
    * you may interact with the API.
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
    * SANDBOX_URL or PROD_URL.
    *
    * @param apiKey your WealthEngine API key
    * @param environmentUrl the PROD/SANDBOX URL for WealthEngine
    * @param interceptor an Interceptor which is attached to the underlying
    *                    OkHttp client which can be used to modify/mock responses
    */
    public WealthEngine(String apiKey, String environmentUrl, Interceptor interceptor) {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.connectTimeout(30, TimeUnit.SECONDS);

        if(interceptor != null) {
            builder.interceptors().add(interceptor);
        }

        builder.addInterceptor(chain -> {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "APIKey " + apiKey)
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
     * @throws WealthEngineException if the request is malformed
     * @param request a EmailMatchRequest object
     * @param delegate a ProfileCallbackDelegate which accepts the response
     */
    public void getBasicProfile(EmailMatchRequest request, ProfileCallbackDelegate delegate) throws WealthEngineException {

        if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
        Call<BasicProfile> call = service.getBasicProfile(request);
        call.enqueue(new BasicProfileCallback(delegate));
    }

    /**
     * <P>Calling this method with an PhoneMatchRequest object tries to return a BasicProfile by first name, last name + phone
     *
     * @throws WealthEngineException if the request is malformed
     * @param request a PhoneMatchRequest object
     * @param delegate a ProfileCallbackDelegate which accepts the response
     */
    public void getBasicProfile(PhoneMatchRequest request, ProfileCallbackDelegate delegate) throws WealthEngineException {

        if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
        Call<BasicProfile> call = service.getBasicProfile(request);
        call.enqueue(new BasicProfileCallback(delegate));
    }

    /**
     * <P>Calling this method with an AddressMatchRequest object tries to return a BasicProfile by first name, last name + address
     *    Only US addresses/zipcodes are valid here. In general WealthEngines API only returns info on
     *    USA persons
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a AddressMatchRequest object
     * @param delegate a ProfileCallbackDelegate which accepts the response
     */
    public void getBasicProfile(AddressMatchRequest request, ProfileCallbackDelegate delegate) throws WealthEngineException {

        if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
        Call<BasicProfile> call = service.getBasicProfile(request);
        call.enqueue(new BasicProfileCallback(delegate));
    }

    /**
     * <P>Calling this method with an EmailMatchRequest object tries to return a FullProfile by first name, last name + email
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a EmailMatchRequest object
     * @param delegate a ProfileCallbackDelegate which accepts the response
     */
    public void getFullProfile(EmailMatchRequest request, ProfileCallbackDelegate delegate) throws WealthEngineException {

        if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
        Call<FullProfile> call = service.getFullProfile(request);
        call.enqueue(new FullProfileCallback(delegate));
    }

    /**
     * <P>Calling this method with an PhoneMatchRequest object tries to return a FullProfile by first name, last name + phone
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a PhoneMatchRequest object
     * @param delegate a ProfileCallbackDelegate which accepts the response
     */
    public void getFullProfile(PhoneMatchRequest request, ProfileCallbackDelegate delegate) throws WealthEngineException {

        if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
        Call<FullProfile> call = service.getFullProfile(request);
        call.enqueue(new FullProfileCallback(delegate));
    }

    /**
     * <P>Calling this method with an AddressMatchRequest object tries to return a FullProfile by first name, last name + address
     *    Only US addresses/zipcodes are valid here. In general WealthEngines API only returns info on
     *    USA persons
     *
     * @throws WealthEngineException if the request fails or if the request is malformed
     * @param request a AddressMatchRequest object
     * @param delegate a ProfileCallbackDelegate which accepts the response
     */
    public void getFullProfile(AddressMatchRequest request, ProfileCallbackDelegate delegate) throws WealthEngineException {

        if(!request.isValid()) throw new WealthEngineException("Request is not properly formatted");
        Call<FullProfile> call = service.getFullProfile(request);
        call.enqueue(new FullProfileCallback(delegate));
    }
}
