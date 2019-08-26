package com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class RechercheMulticritereFicheEclatementValue {

	private Long ordreFabricationId;
	private Long produitId;
	private String numOF;
	private boolean isOptimized;

	
	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}
	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	public String getNumOF() {
		return numOF;
	}
	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}	
	
	
	
	
}
