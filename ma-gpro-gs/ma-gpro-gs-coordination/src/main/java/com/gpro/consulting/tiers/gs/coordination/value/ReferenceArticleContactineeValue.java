package com.gpro.consulting.tiers.gs.coordination.value;

/**
 * The Class EntiteStockValue.
 */
public class ReferenceArticleContactineeValue{
	
	private Long id;
	private String reference ;
	
	public ReferenceArticleContactineeValue(Long id, String reference) {
		super();
		this.id = id;
		this.reference = reference;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
