package com.bridge.pelatro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecentOffers implements Serializable {

	private static final long serialVersionUID = 6592738775862859780L;

	private List<Offer> recentOffers = new ArrayList<Offer>();
	
	public RecentOffers() {
		super();
	}

	public List<Offer> getRecentOffers() {
		return recentOffers;
	}

	public void setRecentOffers(List<Offer> recentOffers) {
		this.recentOffers = recentOffers;
	}
	
}
