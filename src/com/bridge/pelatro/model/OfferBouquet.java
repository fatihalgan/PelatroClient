package com.bridge.pelatro.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OfferBouquet implements Serializable {

	private static final long serialVersionUID = 8761208558961264961L;

	private List<OfferBouquetItem> offers = new ArrayList<OfferBouquetItem>();
	
	public OfferBouquet() {
		super();
	}

	public List<OfferBouquetItem> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferBouquetItem> offers) {
		this.offers = offers;
	}
}
