
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;

import io.funraise.WealthEngine;
import io.funraise.WealthEngineException;
import io.funraise.requests.AddressMatchRequest;
import io.funraise.models.BasicProfile;
import io.funraise.requests.EmailMatchRequest;
import io.funraise.models.FullProfile;

public class WealthEngineTest {

    @Test
    public void testBasicProfileMatchEmail() throws WealthEngineException, IOException {

        WealthEngine we = new WealthEngine("abc123",WealthEngine.SANDBOX_URL, new FakeInterceptor("basic_profile_match.json"));
        
        EmailMatchRequest request = new EmailMatchRequest();
        request.first_name = "Jason";
        request.last_name = "Swenski";
        request.email = "jason@funraise.io";
        BasicProfile basic = we.getBasicProfileByEmail(request);

        assertTrue(basic != null);
        assertEquals(basic.identity.first_name,"Jason");
        assertEquals(basic.identity.last_name,"Swenski");
        assertEquals(basic.identity.middle_name,"Mock");
        assertEquals(basic.giving.p2g_score.value,"35");
    }
    
    @Test
    public void testFullProfileMatchAddress() throws WealthEngineException, IOException {

        WealthEngine we = new WealthEngine("abc123",WealthEngine.SANDBOX_URL, new FakeInterceptor("full_profile_match.json"));

        AddressMatchRequest request = new AddressMatchRequest();
        request.first_name = "Bill";
        request.last_name = "Gates";
        request.address_line1 = "1835 73rd Ave";
        request.city = "Medina";
        request.state = "WA";
        request.zip = "98039";

        FullProfile full = we.getFullProfileByAddress(request);
        assertTrue(full != null);
        assertEquals(full.identity.first_name,"BILL");
        assertEquals(full.identity.last_name,"GATES");
        assertEquals(full.identity.middle_name,"MOCK");
        assertEquals(full.demographics.has_children,false);
        
    }
}
