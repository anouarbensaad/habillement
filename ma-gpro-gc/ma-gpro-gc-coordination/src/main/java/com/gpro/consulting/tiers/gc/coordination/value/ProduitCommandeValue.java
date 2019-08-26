package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.Calendar;
import java.util.List;

public class ProduitCommandeValue implements Comparable<ProduitCommandeValue> {

	/** the Id */
	private Long id;

	/** The prix. */
	private Double prix;

	/** The devise. */
	private String devise;

	/** The quantite. */
	private Long quantite;

	/** The date livraison. */
	private Calendar dateLivraison;

	/** The produitId. */
	private Long produitId;

	/** The gc_commandevente_id. */
	private Long commandeVenteId;

	private List<DetailsProduitCommandeValue> listDetailsProduitCommande;

	// added on 17/03/2016, by Ameni Berrich
	private String produitDesignation;
	private String referenceProduit;
	private String refCommandeVente;
	private String clientAbreviation;

	private Long partieIntersseId;

	public int compareTo(ProduitCommandeValue o) {
		ProduitCommandeValue element = (ProduitCommandeValue) o;
		return (element.getId().compareTo(id));
	}

	/************* Getters & Setters *****************/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param prix
	 *            the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	/**
	 * @return the devise
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * @param devise
	 *            the devise to set
	 */
	public void setDevise(String devise) {
		this.devise = devise;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the dateLivraison
	 */
	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * @param dateLivraison
	 *            the dateLivraison to set
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	/**
	 * @return the produitId
	 */
	public Long getProduitId() {
		return produitId;
	}

	/**
	 * @param produitId
	 *            the produitId to set
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	/**
	 * @return the commandeVenteId
	 */
	public Long getCommandeVenteId() {
		return commandeVenteId;
	}

	/**
	 * @param commandeVenteId
	 *            the commandeVenteId to set
	 */
	public void setCommandeVenteId(Long commandeVenteId) {
		this.commandeVenteId = commandeVenteId;
	}

	public List<DetailsProduitCommandeValue> getListDetailsProduitCommande() {
		return listDetailsProduitCommande;
	}

	public void setListDetailsProduitCommande(List<DetailsProduitCommandeValue> listDetailsProduitCommande) {
		this.listDetailsProduitCommande = listDetailsProduitCommande;
	}

	/**
	 * @return the produitDesignation
	 */
	public String getProduitDesignation() {

		return produitDesignation;
	}

	/**
	 * @param produitDesignation
	 *            the produitDesignation to set
	 */
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	/**
	 * @return the referenceProduit
	 */
	public String getReferenceProduit() {

		return referenceProduit;
	}

	/**
	 * @param referenceProduit
	 *            the referenceProduit to set
	 */
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	/**
	 * @return the refCommandeVente
	 */
	public String getRefCommandeVente() {

		return refCommandeVente;
	}

	/**
	 * @param refCommandeVente
	 *            the refCommandeVente to set
	 */
	public void setRefCommandeVente(String refCommandeVente) {
		this.refCommandeVente = refCommandeVente;
	}

	/**
	 * @return the clientAbreviation
	 */
	public String getClientAbreviation() {

		return clientAbreviation;
	}

	/**
	 * @param clientAbreviation
	 *            the clientAbreviation to set
	 */
	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public Long getPartieIntersseId() {
		return partieIntersseId;
	}

	public void setPartieIntersseId(Long partieIntersseId) {
		this.partieIntersseId = partieIntersseId;
	}
	

	@Override
	public String toString() {
		return "ProduitCommandeValue [id=" + id + ", prix=" + prix + ", devise=" + devise + ", quantite=" + quantite
				+ ", dateLivraison=" + dateLivraison + ", produitId=" + produitId + ", commandeVenteId="
				+ commandeVenteId + ", produitDesignation=" + produitDesignation + ", referenceProduit="
				+ referenceProduit + ", refCommandeVente=" + refCommandeVente + ", clientAbreviation="
				+ clientAbreviation + ", partieIntersseId=" + partieIntersseId + "]";
	}

}
