package com.bridge.pelatro.command;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import com.bridge.pelatro.exception.ConnectionException;
import com.bridge.pelatro.exception.InvalidOfferException;
import com.bridge.pelatro.exception.MSISDNNotFoundException;
import com.bridge.pelatro.exception.PelatroCommandException;
import com.bridge.pelatro.serializer.PelatroRequest;
import com.bridge.pelatro.serializer.PelatroRequestParam;

public abstract class AbstractPelatroProvisionCommand implements Command {

	protected PelatroRequest request = new PelatroRequest();
	
	private String targetUrl;
	protected int httpStatusCode = 0;
	private static Log logger = LogFactory.getLog(AbstractPelatroProvisionCommand.class);
	private String pelatroHost;
	
	public AbstractPelatroProvisionCommand(String targetUrl, String pelatroHost) {
		this.targetUrl = targetUrl;
		this.pelatroHost = pelatroHost;
	}
	
	public abstract void prepareRequest();
	
	public void execute() throws ConnectionException, PelatroCommandException {
		prepareRequest();
	    List<NameValuePair> httpRequestParams = new ArrayList<NameValuePair>();
	   	//Set the parameters
	    for(PelatroRequestParam param : getRequest().getParams()) {
    		httpRequestParams.add(new BasicNameValuePair(param.getParamName(), param.getParamValue()));
    	}
    	URI uri = null;
    	try {
    		uri = new URI(targetUrl + "/" + request.getMethod()); /**URLEncodedUtils.format(httpRequestParams, "UTF-8")**/
    	} catch(URISyntaxException e) {
    		logger.error("Invalid URI syntax prepared: " + targetUrl + "/" + request.getMethod() + "?" + URLEncodedUtils.format(httpRequestParams, "UTF-8"));
    		throw new RuntimeException("Invalid URI syntax prepared: " + targetUrl + "/" + request.getMethod() + "?" + URLEncodedUtils.format(httpRequestParams, "UTF-8"));
    	}
    	DefaultHttpClient httpClient = null;	
    	try {
    		HttpPost postMethod = new HttpPost(uri);
    		postMethod.setHeader("Host", pelatroHost);
    		postMethod.setHeader("Content-type", "application/x-www-form-urlencoded");
    		postMethod.setEntity(new UrlEncodedFormEntity(httpRequestParams));
    		HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, 2000);
            HttpConnectionParams.setStaleCheckingEnabled(params, true);
            HttpConnectionParams.setSoTimeout(params, 2000);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            httpClient = new DefaultHttpClient(params);
            httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(10, true));
            HttpResponse response = null;
    		logger.debug("Sending Request Message: " + postMethod.getRequestLine());
    		response = httpClient.execute(postMethod);
    		httpStatusCode = response.getStatusLine().getStatusCode();
    		logger.debug("Successfully sent message");
    		if(httpStatusCode == HttpStatus.SC_NOT_FOUND) {
    			throw new MSISDNNotFoundException();
    		}
    		if(httpStatusCode == HttpStatus.SC_PRECONDITION_FAILED) {
    			throw new InvalidOfferException();
    		}
    		if (httpStatusCode != HttpStatus.SC_OK) {
        		throw new ConnectionException("Http Status error " + httpStatusCode + " returned from XML-RPC call to host: " + targetUrl, httpStatusCode);
            }
    	} catch(IOException e) {
    		throw new ConnectionException("Cannot execute call to " + targetUrl + "/" + request.getMethod() + ".", HttpStatus.SC_SERVICE_UNAVAILABLE);
    	} finally {
    		httpClient.getConnectionManager().shutdown();
    	}
	}
	
	public PelatroRequest getRequest() {
		return request;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	} 
	
	public String getTargetUrl() {
		return targetUrl;
	}
	
}
