package com.bridge.pelatro.model;

import java.io.Serializable;

public class RecentSubscriberActivityResponse implements Serializable {

	private static final long serialVersionUID = 4098822341494695578L;

	private RecentSubscriberActivity recentSubscriberActivity;
	
	public RecentSubscriberActivityResponse() {
		super();
	}

	public RecentSubscriberActivity getRecentSubscriberActivity() {
		return recentSubscriberActivity;
	}

	public void setRecentSubscriberActivity(
			RecentSubscriberActivity recentSubscriberActivity) {
		this.recentSubscriberActivity = recentSubscriberActivity;
	}
	
}
