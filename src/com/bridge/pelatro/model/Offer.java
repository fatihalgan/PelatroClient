package com.bridge.pelatro.model;

import java.io.Serializable;

public class Offer implements Serializable {

	private static final long serialVersionUID = -7299533533214856978L;
	
	private String campaign;
	private String timestamp;
	private String offer;
	private int status;
	private int validity;
	
	public Offer() {
		super();
	}

	public String getCampaign() {
		return campaign;
	}

	public void setCampaign(String campaign) {
		this.campaign = campaign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

}
