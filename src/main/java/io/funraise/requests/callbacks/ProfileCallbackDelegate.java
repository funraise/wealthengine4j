package io.funraise.requests.callbacks;

import io.funraise.responses.ProfileResponse;

/**
 * Created by jason on 6/25/17.
 */
public interface ProfileCallbackDelegate {
    void onComplete(ProfileResponse response);
}
