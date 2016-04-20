package com.bridge.pelatro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecentRewards implements Serializable {

	private static final long serialVersionUID = 8185054480674292756L;

	private List<Reward> recentRewards = new ArrayList<Reward>();
	
	public RecentRewards() {
		super();
	}

	public List<Reward> getRecentRewards() {
		return recentRewards;
	}

	public void setRecentRewards(List<Reward> recentRewards) {
		this.recentRewards = recentRewards;
	}
	
}
