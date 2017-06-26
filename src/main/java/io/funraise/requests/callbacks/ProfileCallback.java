package io.funraise.requests.callbacks;

import io.funraise.models.ProfileResponse;

/**
 * Created by jason on 6/25/17.
 */
public class ProfileCallback {

    protected ProfileCallbackDelegate delegate;
    protected ProfileResponse profileResponse = new ProfileResponse();

    public ProfileCallback(ProfileCallbackDelegate delegate) {
        this.delegate = delegate;
    }

    public void onComplete() {
        delegate.onComplete(profileResponse);
    }
}
