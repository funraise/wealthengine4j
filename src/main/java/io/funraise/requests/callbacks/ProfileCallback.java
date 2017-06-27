package io.funraise.requests.callbacks;

import io.funraise.models.BasicProfile;
import io.funraise.responses.ProfileResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jason on 6/25/17.
 */
public class ProfileCallback<T extends BasicProfile> implements Callback<T>  {

    protected ProfileCallbackDelegate delegate;
    protected ProfileResponse profileResponse = new ProfileResponse();

    public ProfileCallback(ProfileCallbackDelegate delegate) {
        this.delegate = delegate;
    }

    public void onComplete() {
        delegate.onComplete(profileResponse);
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            profileResponse.profile = response.body();
        } else {
            profileResponse.error = response.message();
        }
        onComplete();
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {
        profileResponse.error = throwable.getMessage();
        onComplete();
    }
}
