package io.funraise.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.HttpParams;

public class MockHTTPResponse implements CloseableHttpResponse {

    
    private StringEntity entity;
    
    public MockHTTPResponse(String resourceName, String contentType) throws IOException, URISyntaxException {
        
        TestUtils utils = new TestUtils();
        String json = utils.loadMockJson(resourceName);
        this.entity = new StringEntity(json);
    }

    @Override
    public HttpEntity getEntity() {
        return entity;
    }

    @Override
    public Locale getLocale() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StatusLine getStatusLine() {
        // TODO Auto-generated method stub

        return new MockStatusLine();
    }

    @Override
    public void setEntity(HttpEntity arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLocale(Locale arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setReasonPhrase(String arg0) throws IllegalStateException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setStatusCode(int arg0) throws IllegalStateException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setStatusLine(StatusLine arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setStatusLine(ProtocolVersion arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setStatusLine(ProtocolVersion arg0, int arg1, String arg2) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addHeader(Header arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addHeader(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean containsHeader(String arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Header[] getAllHeaders() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Header getFirstHeader(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Header[] getHeaders(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Header getLastHeader(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HttpParams getParams() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProtocolVersion getProtocolVersion() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HeaderIterator headerIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HeaderIterator headerIterator(String arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removeHeader(Header arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeHeaders(String arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setHeader(Header arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setHeader(String arg0, String arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setHeaders(Header[] arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setParams(HttpParams arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void close() throws IOException {
        // TODO Auto-generated method stub
        
    }
}
