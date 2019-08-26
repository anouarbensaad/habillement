package com.gpro.consulting.tiers.commun.coordination.value;


public class ProduitCouleurValue{
	
	private Long id;
	private long ebCouleurId;
	private long ebProduitId;
	private String designation;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getEbCouleurId() {
		return ebCouleurId;
	}

	public void setEbCouleurId(long ebCouleurId) {
		this.ebCouleurId = ebCouleurId;
	}

	public long getEbProduitId() {
		return ebProduitId;
	}

	public void setEbProduitId(long ebProduitId) {
		this.ebProduitId = ebProduitId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
	
}