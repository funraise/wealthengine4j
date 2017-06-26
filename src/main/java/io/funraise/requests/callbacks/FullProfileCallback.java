package io.funraise.requests.callbacks;

import io.funraise.models.FullProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 6/25/17.
 */
public class FullProfileCallback extends ProfileCallback implements Callback<FullProfile> {

    public FullProfileCallback(ProfileCallbackDelegate delegate) {
        super(delegate);
    }

    @Override
    public void onResponse(Call<FullProfile> call, Response<FullProfile> response) {
        if (response.isSuccessful()) {
            profileResponse.profile = response.body();
        } else {
            profileResponse.error = response.message().toString();
        }
        onComplete();
    }

    @Override
    public void onFailure(Call<FullProfile> call, Throwable throwable) {

    }
}
