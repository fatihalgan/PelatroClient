package com.bridge.pelatro.serializer;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class PelatroRequest implements Serializable {

	private static final long serialVersionUID = 7127534410501126932L;
	
	private String method;
	private Set<PelatroRequestParam> params = new HashSet<PelatroRequestParam>();
	
	public PelatroRequest() {
		super();
	}

	public PelatroRequest(String method) {
		this.method = method;
	}
	
	public void addParam(String paramName, String paramValue) {
		PelatroRequestParam param = new PelatroRequestParam(paramName, paramValue);
		params.add(param);
	}
	
	public void addParam(PelatroRequestParam param) {
		params.add(param);
	}
	
	public String getValue(String paramName) {
		for(PelatroRequestParam param : params) {
			if(param.getParamName().equals(paramName)) return param.getParamValue();
		}
		return null;
	}
	
	public boolean setValue(String paramName, String paramValue) {
		for(PelatroRequestParam param : params) {
			if(param.getParamName().equals(paramName)) {
				param.setParamValue(paramValue);
				return true;
			}
		}
		return false;
	}
	
	public Set<PelatroRequestParam> getParams() {
		return params;
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
}
