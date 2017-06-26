package io.funraise.requests.callbacks;

import io.funraise.models.ProfileResponse;

/**
 * Created by jason on 6/25/17.
 */
public interface ProfileCallbackDelegate {
    void onComplete(ProfileResponse response);
}
