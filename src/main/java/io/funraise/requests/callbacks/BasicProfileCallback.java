package io.funraise.requests.callbacks;

import io.funraise.models.BasicProfile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 6/25/17.
 */
public class BasicProfileCallback extends ProfileCallback implements Callback<BasicProfile> {

    public BasicProfileCallback(ProfileCallbackDelegate delegate) {
        super(delegate);
    }

    @Override
    public void onResponse(Call<BasicProfile> call, Response<BasicProfile> response) {
        if (response.isSuccessful()) {
            profileResponse.profile = response.body();
        } else {
            profileResponse.error = response.message().toString();
        }
        onComplete();
    }

    @Override
    public void onFailure(Call<BasicProfile> call, Throwable throwable) {
        profileResponse.error = throwable.getMessage();
        onComplete();
    }
}
