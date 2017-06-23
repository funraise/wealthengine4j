package io.funraise;

import io.funraise.requests.AddressMatchRequest;
import io.funraise.models.BasicProfile;
import io.funraise.requests.EmailMatchRequest;
import io.funraise.models.FullProfile;
import io.funraise.requests.PhoneMatchRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WealthEngineService {

    @POST("profile/find_one/by_email/basic")
    Call<BasicProfile> getBasicProfile(@Body EmailMatchRequest request);

    @POST("profile/find_one/by_phone/basic")
    Call<BasicProfile> getBasicProfile(@Body PhoneMatchRequest request);

    @POST("profile/find_one/by_address/basic")
    Call<BasicProfile> getBasicProfile(@Body AddressMatchRequest request);


    @POST("profile/find_one/by_email/full")
    Call<FullProfile> getFullProfile(@Body EmailMatchRequest request);

    @POST("profile/find_one/by_phone/full")
    Call<FullProfile> getFullProfile(@Body PhoneMatchRequest request);

    @POST("profile/find_one/by_address/full")
    Call<FullProfile> getFullProfile(@Body AddressMatchRequest request);
}
