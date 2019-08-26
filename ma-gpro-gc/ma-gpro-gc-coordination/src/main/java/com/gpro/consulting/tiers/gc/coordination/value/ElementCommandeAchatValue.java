/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.value;

/**
 * @author $Ameni
 *
 */
public class ElementCommandeAchatValue {

	private Long id;

	/** The quantite. */
	private Long quantite;

	/** The prixUnitaire. */
	private Long prixUnitiaire;

	/** The livre. */
	private boolean livre;

	/** The prixTotal. */
	private Long prixTotal;
	
	/** The articleId. */
	private Long articleId;
	
	/** The gc_commandeachat_id. */
	private Long commandeAchatId;

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

	/**
	 * @return the quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the prixUnitiaire
	 */
	public Long getPrixUnitiaire() {
		return prixUnitiaire;
	}

	/**
	 * @param prixUnitiaire the prixUnitiaire to set
	 */
	public void setPrixUnitiaire(Long prixUnitiaire) {
		this.prixUnitiaire = prixUnitiaire;
	}

	/**
	 * @return the livre
	 */
	public boolean isLivre() {
		return livre;
	}

	/**
	 * @param livre the livre to set
	 */
	public void setLivre(boolean livre) {
		this.livre = livre;
	}

	/**
	 * @return the prixTotal
	 */
	public Long getPrixTotal() {
		return prixTotal;
	}

	/**
	 * @param prixTotal the prixTotal to set
	 */
	public void setPrixTotal(Long prixTotal) {
		this.prixTotal = prixTotal;
	}

	/**
	 * @return the articleId
	 */
	public Long getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the commandeAchatId
	 */
	public Long getCommandeAchatId() {
		return commandeAchatId;
	}

	/**
	 * @param commandeAchatId the commandeAchatId to set
	 */
	public void setCommandeAchatId(Long commandeAchatId) {
		this.commandeAchatId = commandeAchatId;
	}

}
