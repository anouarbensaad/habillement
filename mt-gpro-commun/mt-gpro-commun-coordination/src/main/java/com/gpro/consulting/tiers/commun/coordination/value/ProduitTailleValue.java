package com.gpro.consulting.tiers.commun.coordination.value;

public class ProduitTailleValue implements Comparable<ProduitTailleValue> {

	private Long id;
	private Long ebTailleId;
	private Long ebProduitId;
	private String designation;
	
	//used only to SORT
	private Integer tailleOrdre;

	@Override
	public int compareTo(ProduitTailleValue o) {
		ProduitTailleValue element= (ProduitTailleValue)o;
		return tailleOrdre.compareTo(element.getTailleOrdre());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEbTailleId() {
		return ebTailleId;
	}

	public void setEbTailleId(Long ebTailleId) {
		this.ebTailleId = ebTailleId;
	}

	public Long getEbProduitId() {
		return ebProduitId;
	}

	public void setEbProduitId(Long ebProduitId) {
		this.ebProduitId = ebProduitId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Integer getTailleOrdre() {
		return tailleOrdre;
	}

	public void setTailleOrdre(Integer tailleOrdre) {
		this.tailleOrdre = tailleOrdre;
	}

	
}