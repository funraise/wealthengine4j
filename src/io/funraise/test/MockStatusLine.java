package io.funraise.test;

import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;

public class MockStatusLine implements StatusLine {

    @Override
    public ProtocolVersion getProtocolVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getReasonPhrase() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getStatusCode() {
        // TODO Auto-generated method stub
        return 200;
    }

}
