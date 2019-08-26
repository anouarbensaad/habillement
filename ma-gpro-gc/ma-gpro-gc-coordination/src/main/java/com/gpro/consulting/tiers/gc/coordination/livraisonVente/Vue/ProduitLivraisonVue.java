package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue;

/**
 * @author Ameni Berrich
 *
 */
public class ProduitLivraisonVue {

	private Long id;
	private Double prix;
	private String devise;
	private Long quantite;
	private Long produitId;
	private String referenceCommande;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getPrix() {
		return prix;
	}
	public void setPrix(Double prix) {
		this.prix = prix;
	}
	public String getDevise() {
		return devise;
	}
	public void setDevise(String devise) {
		this.devise = devise;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public String getReferenceCommande() {
		return referenceCommande;
	}
	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}
	
	

}
