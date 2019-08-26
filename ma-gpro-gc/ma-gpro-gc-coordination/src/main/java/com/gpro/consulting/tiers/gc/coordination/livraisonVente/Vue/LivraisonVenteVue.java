package com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class LivraisonVenteVue {
	
	private String refLivraison;
	private Long agentBLId;
	private List<ProduitLivraisonVue> produitLivraison = new ArrayList<ProduitLivraisonVue>();
	public String getRefLivraison() {
		return refLivraison;
	}
	public void setRefLivraison(String refLivraison) {
		this.refLivraison = refLivraison;
	}
	public Long getAgentBLId() {
		return agentBLId;
	}
	public void setAgentBLId(Long agentBLId) {
		this.agentBLId = agentBLId;
	}
	public List<ProduitLivraisonVue> getProduitLivraison() {
		return produitLivraison;
	}
	public void setProduitLivraison(List<ProduitLivraisonVue> produitLivraison) {
		this.produitLivraison = produitLivraison;
	}
	
}
