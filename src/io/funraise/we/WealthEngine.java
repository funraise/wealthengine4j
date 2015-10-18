package io.funraise.we;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.funraise.we.Request.MatchRequest;
import io.funraise.we.Response.BasicProfileMatch;
import io.funraise.we.Response.MatchResponse;

public class WealthEngine {

    public static final String PROD_URL = "https://api.wealthengine.com/v1/";
    public static final String SANDBOX_URL = "https://api-sandbox.wealthengine.com/v1/";

    private final String _apiKey;
    private final String _apiRoot;
	
	public WealthEngine(String apiKey) {
        _apiKey = apiKey;
        _apiRoot = PROD_URL;  
    }
	
	public WealthEngine(String apiKey, String environmentUrl) {
        _apiKey = apiKey;
        _apiRoot = environmentUrl;  
    }
	
	public MatchResponse getProfile(MatchRequest request) {
		
		CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
	    httpclient.start();
	    
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    final HttpPost httprequest = new HttpPost(_apiRoot + request.getEndpoint());
	    httprequest.addHeader("Authorization", "APIKey " + _apiKey);
	    httprequest.addHeader("Content-type","application/json");
	    
	    MatchResponse matchResponse = new MatchResponse();

	    try {
			httprequest.setEntity(new StringEntity(mapper.writeValueAsString(request)));
	        Future<HttpResponse> future = httpclient.execute(httprequest, null);
	        HttpResponse response = future.get();
	        matchResponse.statusCode = response.getStatusLine().getStatusCode();
	        matchResponse.rawContent = EntityUtils.toString(response.getEntity());
	        
	        matchResponse.profileMatch = mapper.readValue(matchResponse.rawContent, BasicProfileMatch.class);
		} catch (IOException | ParseException | InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}  
		return matchResponse;
	}
}
