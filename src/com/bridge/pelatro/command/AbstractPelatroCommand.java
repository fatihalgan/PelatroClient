package com.bridge.pelatro.command;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
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

public abstract class AbstractPelatroCommand implements Command {

	protected PelatroRequest request = new PelatroRequest();
	
	private String targetUrl;
	protected int httpStatusCode = 0;
	private static Log logger = LogFactory.getLog(AbstractPelatroCommand.class);
	private String pelatroHost;
	protected String jsonResponse;
	
	public AbstractPelatroCommand(String targetUrl, String pelatroHost) {
		this.targetUrl = targetUrl;
		this.pelatroHost = pelatroHost;
	}
	
	public abstract void prepareRequest();
	public abstract void prepareResponse() throws PelatroCommandException;
	
	public void execute() throws ConnectionException, PelatroCommandException {
		prepareRequest();
	    List<NameValuePair> httpRequestParams = new ArrayList<NameValuePair>();
	   	//Set the parameters
	    for(PelatroRequestParam param : getRequest().getParams()) {
    		httpRequestParams.add(new BasicNameValuePair(param.getParamName(), param.getParamValue()));
    	}
    	URI uri = null;
    	try {
    		uri = new URI(targetUrl + "/" + request.getMethod() + "?" + serializeRequestParams(httpRequestParams) /**URLEncodedUtils.format(httpRequestParams, "UTF-8")**/);
    	} catch(URISyntaxException e) {
    		logger.error("Invalid URI syntax prepared: " + targetUrl + "/" + request.getMethod() + "?" + URLEncodedUtils.format(httpRequestParams, "UTF-8"));
    		throw new RuntimeException("Invalid URI syntax prepared: " + targetUrl + "/" + request.getMethod() + "?" + URLEncodedUtils.format(httpRequestParams, "UTF-8"));
    	}
    	DefaultHttpClient httpClient = null;
    	try {
    		HttpGet getMethod = new HttpGet(uri);
    		getMethod.setHeader("Host", pelatroHost);
    		HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, 2000);
            HttpConnectionParams.setStaleCheckingEnabled(params, true);
            HttpConnectionParams.setSoTimeout(params, 2000);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            httpClient = new DefaultHttpClient(params);
            httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler(10, true));
            HttpResponse response = null;
    		logger.debug("Sending Request Message: " + getMethod.getRequestLine());
    		response = httpClient.execute(getMethod);
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
    		HttpEntity entity = response.getEntity();
    		StringBuffer strResponse = new StringBuffer();
    		if(entity != null) {
    			strResponse.append(EntityUtils.toString(entity));
    		}
    		logger.debug("Response Message: ");
    		logger.debug(strResponse.toString());
    		this.jsonResponse = strResponse.toString();
    		prepareResponse();
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
	
	private String serializeRequestParams(List<NameValuePair> params) {
		String returnVal = "";
		for(NameValuePair pair : params) {
			returnVal = returnVal + pair.getName() + "=" + pair.getValue();
			if(params.indexOf(pair) != params.size() - 1) returnVal = returnVal + "&";
		}
		return returnVal;
	}
}
