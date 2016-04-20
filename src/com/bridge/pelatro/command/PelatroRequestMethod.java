package com.bridge.pelatro.command;

public enum PelatroRequestMethod {

	pelatroRsaMethod("pelatro_rsa"),
	pelatroBouquetMethod("pelatro_bouquet"),
	pelatroProvisionMethod("pelatro_provision");
	
	private String method;
	
	private PelatroRequestMethod(String method) {
		this.method = method;
	}
	
	public String toString() {
		if(this.equals(pelatroRsaMethod)) return "pelatro_rsa";
		else if(this.equals(pelatroBouquetMethod)) return "pelatro_bouquet";
		else if(this.equals(pelatroProvisionMethod)) return "pelatro_provision";
		else return null;
	}
}
