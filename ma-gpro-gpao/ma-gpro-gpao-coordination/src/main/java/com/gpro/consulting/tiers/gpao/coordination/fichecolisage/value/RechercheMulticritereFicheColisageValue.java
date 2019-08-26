package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class RechercheMulticritereFicheColisageValue {

	private Long ordreFabricationId;
	private Long produitId;
	private String designationProduit;
	private String numeroOF;
	private boolean isOptimized;
	private String semaine;
	private Long partieIntersseId ;
	

	
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
	public String getDesignationProduit() {
		return designationProduit;
	}
	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}
	public String getNumeroOF() {
		return numeroOF;
	}
	public void setNumeroOF(String numeroOF) {
		this.numeroOF = numeroOF;
	}
	public String getSemaine() {
		return semaine;
	}
	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}
	public Long getPartieIntersseId() {
		return partieIntersseId;
	}
	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}
	@Override
	public String toString() {
		return "RechercheMulticritereFicheColisageValue [ordreFabricationId="
				+ ordreFabricationId + ", produitId=" + produitId
				+ ", designationProduit=" + designationProduit + ", numeroOF="
				+ numeroOF + ", isOptimized=" + isOptimized + ", semaine="
				+ semaine + ", partieIntersseId=" + partieIntersseId + "]";
	}	
	
	
}
