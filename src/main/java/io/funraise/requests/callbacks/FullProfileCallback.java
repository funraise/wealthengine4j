package io.funraise.requests.callbacks;

import io.funraise.models.FullProfile;

/**
 * Created by jason on 6/25/17.
 */
public class FullProfileCallback extends ProfileCallback<FullProfile>  {

    public FullProfileCallback(ProfileCallbackDelegate delegate) {
        super(delegate);
    }
}
