package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value;

import java.util.Calendar;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ProduitLivraisonValue implements Comparable<ProduitLivraisonValue>{
	
	private Long id;
	private Long produitId;
	private Double prix;
	private String devise;
	private Long quantite;
	private Calendar dateLivraison;
	private Long livraisonVenteId;
	
	//added on 19/07/2016, by Hajer AMRI
	private String referenceCommande;
			
	private List<DetailProduitLivraisonValue> listDetailsProduitLivraison;
	
	
	@Override
	public int compareTo(ProduitLivraisonValue o) {
		ProduitLivraisonValue element= (ProduitLivraisonValue)o;
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
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

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Long getLivraisonVenteId() {
		return livraisonVenteId;
	}

	public void setLivraisonVenteId(Long livraisonVenteId) {
		this.livraisonVenteId = livraisonVenteId;
	}
	
	public String getReferenceCommande() {
		return referenceCommande;
	}

	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}

	public List<DetailProduitLivraisonValue> getListDetailsProduitLivraison() {
		return listDetailsProduitLivraison;
	}

	public void setListDetailsProduitLivraison(
			List<DetailProduitLivraisonValue> listDetailsProduitLivraison) {
		this.listDetailsProduitLivraison = listDetailsProduitLivraison;
	}

	@Override
	public String toString() {
		return "ProduitLivraisonValue [id=" + id + ", produitId=" + produitId + ", referenceCommande="
				+ referenceCommande + "]";
	}

}
