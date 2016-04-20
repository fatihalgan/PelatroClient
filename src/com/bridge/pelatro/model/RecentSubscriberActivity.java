package com.bridge.pelatro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecentSubscriberActivity implements Serializable {

	private static final long serialVersionUID = -5010966713694206604L;
	
	private int lookbackPeriodInDays;
	private RecentOffers offers;
	private RecentRewards rewards;
	
	public RecentSubscriberActivity() {
		super();
	}

	public RecentOffers getOffers() {
		return offers;
	}

	public void setOffers(RecentOffers offers) {
		this.offers = offers;
	}

	public RecentRewards getRewards() {
		return rewards;
	}

	public void setRewards(RecentRewards rewards) {
		this.rewards = rewards;
	}

	public int getLookbackPeriodInDays() {
		return lookbackPeriodInDays;
	}

	public void setLookbackPeriodInDays(int lookbackPeriodInDays) {
		this.lookbackPeriodInDays = lookbackPeriodInDays;
	}
	
	
}
