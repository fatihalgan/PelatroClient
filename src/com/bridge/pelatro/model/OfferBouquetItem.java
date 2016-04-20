package com.bridge.pelatro.model;

import java.io.Serializable;

public class OfferBouquetItem implements Serializable {

	private static final long serialVersionUID = -2791523216429476195L;
	
	private String id;
	private String text;
	
	public OfferBouquetItem() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
