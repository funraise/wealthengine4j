
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

        WealthEngine we = new WealthEngine("abc123",WealthEngine.SANDBOX_URL, new FakeSuccessInterceptor("basic_profile_match.json",200));
        EmailMatchRequest request = new EmailMatchRequest();
        request.first_name = "Jason";
        request.last_name = "Swenski";
        request.email = "jason@funraise.io";

        we.getBasicProfile(request, response -> {

            BasicProfile basic = response.profile;
            assertTrue(basic != null);
            assertEquals(basic.identity.first_name,"JASON");
            assertEquals(basic.identity.last_name,"SWENSKI");
            assertEquals(basic.identity.middle_name,"E");
            assertEquals(basic.giving.p2g_score.value,"10");
        });

    }
    
    @Test
    public void testFullProfileMatchAddress() throws WealthEngineException, IOException {

        WealthEngine we = new WealthEngine("abc123",WealthEngine.SANDBOX_URL, new FakeSuccessInterceptor("full_profile_match.json",200));
        AddressMatchRequest request = new AddressMatchRequest();
        request.first_name = "Bill";
        request.last_name = "Gates";
        request.address_line1 = "1835 73rd Ave";
        request.city = "Medina";
        request.state = "WA";
        request.zip = "98039";

        we.getFullProfile(request, response -> {

            FullProfile full = (FullProfile)response.profile;
            assertTrue(full != null);
            assertEquals(full.identity.first_name,"JASON");
            assertEquals(full.identity.last_name,"SWENSKI");
            assertEquals(full.identity.middle_name,"E");
            assertEquals(full.demographics.has_children,true);
        });

    }


    @Test
    public void testErrors() throws WealthEngineException, IOException {

        WealthEngine we = new WealthEngine("abc123",WealthEngine.SANDBOX_URL, new FakeSuccessInterceptor("error.json",500));
        AddressMatchRequest request = new AddressMatchRequest();
        request.first_name = "Bill";
        request.last_name = "Gates";
        request.address_line1 = "1835 73rd Ave";
        request.city = "Medina";
        request.state = "WA";
        request.zip = "98039";

        we.getFullProfile(request, response -> {
            assertNotNull(response.error);
        });

    }
}
