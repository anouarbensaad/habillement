/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.vue;

/**
 * @author $Ameni
 *
 */
public class PhaseProduitVue {

	/** The idProduit. */
	private Long idProduit;
	
	/** The idPhase. */
	private Long idPhase;
	
	/** The designation. */
	private String designation;
	
	/** Prix. */
	private Double prix;
	
	/** Devise. */
	private String devise;

	/************ Getter & setter *********/
	/**
	 * @param idProduit the idProduit to set
	 */
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @return the idProduit
	 */
	public Long getIdProduit() {
		return idProduit;
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
	 * @return the idPhase
	 */
	public Long getIdPhase() {
		return idPhase;
	}

	/**
	 * @param idPhase the idPhase to set
	 */
	public void setIdPhase(Long idPhase) {
		this.idPhase = idPhase;
	}

}
