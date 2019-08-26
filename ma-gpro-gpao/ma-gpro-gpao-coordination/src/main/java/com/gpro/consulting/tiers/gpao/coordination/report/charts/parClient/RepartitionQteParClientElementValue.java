package com.gpro.consulting.tiers.gpao.coordination.report.charts.parClient;

public class RepartitionQteParClientElementValue {

	private Long quantite;
	private String abreviationClient;
	private String designationProduit;
	private String produitSousFamilleDesignation;
	private String designationSFamille;
	private String referenceProduit;

	public String getReferenceProduit() {
		return referenceProduit;
	}

	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	public String getProduitSousFamilleDesignation() {
		return produitSousFamilleDesignation;
	}

	public void setProduitSousFamilleDesignation(String produitSousFamilleDesignation) {
		this.produitSousFamilleDesignation = produitSousFamilleDesignation;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getAbreviationClient() {
		return abreviationClient;
	}

	public void setAbreviationClient(String abreviationClient) {
		this.abreviationClient = abreviationClient;
	}

	public String getDesignationProduit() {
		return designationProduit;
	}

	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}

	public String getDesignationSFamille() {
		return designationSFamille;
	}

	public void setDesignationSFamille(String designationSFamille) {
		this.designationSFamille = designationSFamille;
	}

	@Override
	public String toString() {
		return "RepartitionQteParClientElementValue [quantite=" + quantite + ", abreviationClient=" + abreviationClient
				+ ", designationProduit=" + designationProduit + ", designationSFamille=" + designationSFamille + "]";
	}

}
