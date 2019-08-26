package com.gpro.consulting.tiers.commun.coordination.value;


public class PhaseProduitValue implements Comparable<PhaseProduitValue> {

	/** The prix. */
	private Long id;
	
	/** The prix. */
	private Double prix;

	/** The devise. */
	private String devise;
	
	/** The variante. */
	private String variante;
	
	/** The ordre. */
	private String ordre;
	
	/** The eb_phase_id. */
	private long eb_phase_id;

	/** Theeb_produit_id */
	private long eb_produit_id;

	/************* Getters & Setters *****************/
	
	public long getEb_phase_id() {
		return eb_phase_id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public void setEb_phase_id(long eb_phase_id) {
		this.eb_phase_id = eb_phase_id;
	}

	public long getEb_produit_id() {
		return eb_produit_id;
	}

	public void setEb_produit_id(long eb_produit_id) {
		this.eb_produit_id = eb_produit_id;
	}

	/**
	 * @return the ordre
	 */
	public String getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(String ordre) {
		this.ordre = ordre;
	}

	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
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
	 * @param devise the devise to set
	 */
	public void setDevise(String devise) {
		this.devise = devise;
	}

	/**
	 * @return the variante
	 */
	public String getVariante() {
		return variante;
	}

	/**
	 * @param variante the variante to set
	 */
	public void setVariante(String variante) {
		this.variante = variante;
	}
	
	public int compareTo(PhaseProduitValue o) {
		PhaseProduitValue element= (PhaseProduitValue)o;
		return (ordre.compareTo(element.getOrdre()));
	}

}
