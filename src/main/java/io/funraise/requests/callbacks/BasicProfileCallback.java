package io.funraise.requests.callbacks;

import io.funraise.models.BasicProfile;

/**
 * Created by jason on 6/25/17.
 */
public class BasicProfileCallback extends ProfileCallback<BasicProfile> {

    public BasicProfileCallback(ProfileCallbackDelegate delegate) {
        super(delegate);
    }

}
