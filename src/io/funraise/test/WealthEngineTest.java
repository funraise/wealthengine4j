package io.funraise.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import io.funraise.api.WealthEngine;
import io.funraise.models.AddressMatchRequest;
import io.funraise.models.ApiRequest.MalformedRequestException;
import io.funraise.models.BasicProfileMatch;
import io.funraise.models.EmailMatchRequest;
import io.funraise.models.FullProfileMatch;
import io.funraise.models.MatchResponse;


@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
public class WealthEngineTest {
    
    private static WealthEngine we = new WealthEngine("abc123",WealthEngine.SANDBOX_URL);
    private static  CloseableHttpClient defaultHttpClientMocked = null;
    
    @Before
    public void setup() throws ClientProtocolException, IOException, URISyntaxException {
        MockitoAnnotations.initMocks(WealthEngine.class);
        
        PowerMockito.mockStatic(CloseableHttpClient.class);
        defaultHttpClientMocked =  PowerMockito.mock(CloseableHttpClient.class);        
        we.setClient(defaultHttpClientMocked);
    }
    
    @Test
    public void testBasicProfileMatchEmail() throws ClientProtocolException, IOException, URISyntaxException {
        
        PowerMockito.when(defaultHttpClientMocked.execute(Mockito.any(HttpPost.class)))
        .thenReturn(new MockHTTPResponse("basic_profile_match.json","application/json"));
        
        EmailMatchRequest request = new EmailMatchRequest();
        request.first_name = "Jason";
        request.last_name = "Swenski";
        request.email = "jason@funraise.io";
        MatchResponse response = null;
        try {
            response = we.getBasicProfileByEmail(request).get();
        } catch (MalformedRequestException | InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertTrue(response != null);
        assertTrue(response.profileMatch != null);
        
        BasicProfileMatch basic = (BasicProfileMatch) response.profileMatch;
        
        assertEquals(basic.identity.first_name,"Jason");
        assertEquals(basic.identity.last_name,"Swenski");
        assertEquals(basic.identity.middle_name,"Mock");
        assertEquals(basic.giving.p2g_score.value,"35");
        
      
    }
    
    @Test
    public void testFullProfileMatchAddress() throws ClientProtocolException, IOException, URISyntaxException {
        
        PowerMockito.when(defaultHttpClientMocked.execute(Mockito.any(HttpPost.class)))
        .thenReturn(new MockHTTPResponse("full_profile_match.json","application/json"));
        
        
        AddressMatchRequest request = new AddressMatchRequest();
        request.first_name = "Bill";
        request.last_name = "Gates";
        request.address_line1 = "1835 73rd Ave";
        request.city = "Medina";
        request.state = "WA";
        request.zip = "98039";
        
        
        MatchResponse response = null;
        try {
            response = we.getFullProfileByAddress(request).get();
        } catch (MalformedRequestException | InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        assertTrue(response != null);
        assertTrue(response.profileMatch != null);
        
        FullProfileMatch full = (FullProfileMatch) response.profileMatch;
        
        assertEquals(full.identity.first_name,"BILL");
        assertEquals(full.identity.last_name,"GATES");
        assertEquals(full.identity.middle_name,"MOCK");
        assertEquals(full.demographics.has_children,false);
        
    }
}
