package com.gpro.consulting.tiers.gpao.coordination.recapproduction.value;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 21/06/2016
 *
 */
public class RecapProductionValue implements Comparable<RecapProductionValue>{

	private Long id;
	
	private String numero;
	private Long quantite;
	private Long partieInterresId;
	private Long produitId;
	private Calendar dateLivraison;
	private Calendar dateIntroduction;
	
	private String partieInterresAbreviation;
	private String produitReference;
	private String produitDesignation;
	
	private Long ofId;
	
	@Override
	public int compareTo(RecapProductionValue element) {
		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getPartieInterresId() {
		return partieInterresId;
	}

	public void setPartieInterresId(Long partieInterresId) {
		this.partieInterresId = partieInterresId;
	}

	public String getPartieInterresAbreviation() {
		return partieInterresAbreviation;
	}

	public void setPartieInterresAbreviation(String partieInterresAbreviation) {
		this.partieInterresAbreviation = partieInterresAbreviation;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public Long getOfId() {
		return ofId;
	}

	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}
	
	
}
