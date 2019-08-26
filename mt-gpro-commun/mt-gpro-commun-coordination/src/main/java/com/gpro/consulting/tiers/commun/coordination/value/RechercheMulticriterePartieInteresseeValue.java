package com.gpro.consulting.tiers.commun.coordination.value;

public class RechercheMulticriterePartieInteresseeValue {

	
	/** The ref. */
	private String vReference;

	/** The raison sociale. */
	private String vRaisonSociale;

	/** The famille entite. */
	private Long vFamillePartieInteressee;

	/** The categorie entite. */
	private Long vCategoriePartieInteressee;

	/** Type Partie Interessee **/
	private Long vTypePartieInteressee;

	/** Activité Partie Interessee **/
	private String vActivite;

	private String actif;

	private boolean isOptimized;

	/**
	 * @return the reference
	 */

	/**
	 * Accesseur en lecture de l'attribut <code>vReference</code>.
	 * 
	 * @return String L'attribut vReference à lire.
	 */
	public String getvReference() {
		return vReference;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>vReference</code>.
	 *
	 * @param vReference
	 *            L'attribut vReference à modifier.
	 */
	public void setvReference(String vReference) {
		this.vReference = vReference;
	}

	/**
	 * Accesseur en lecture de l'attribut <code>vRaisonSociale</code>.
	 * 
	 * @return String L'attribut vRaisonSociale à lire.
	 */
	public String getvRaisonSociale() {
		return vRaisonSociale;
	}

	/**
	 * Accesseur en écriture de l'attribut <code>vRaisonSociale</code>.
	 *
	 * @param vRaisonSociale
	 *            L'attribut vRaisonSociale à modifier.
	 */
	public void setvRaisonSociale(String vRaisonSociale) {
		this.vRaisonSociale = vRaisonSociale;
	}

	public Long getvFamillePartieInteressee() {
		return vFamillePartieInteressee;
	}

	public void setvFamillePartieInteressee(Long vFamillePartieInteressee) {
		this.vFamillePartieInteressee = vFamillePartieInteressee;
	}

	public Long getvCategoriePartieInteressee() {
		return vCategoriePartieInteressee;
	}

	public void setvCategoriePartieInteressee(Long vCategoriePartieInteressee) {
		this.vCategoriePartieInteressee = vCategoriePartieInteressee;
	}

	public Long getvTypePartieInteressee() {
		return vTypePartieInteressee;
	}

	public void setvTypePartieInteressee(Long vTypePartieInteressee) {
		this.vTypePartieInteressee = vTypePartieInteressee;
	}

	public String getvActivite() {
		return vActivite;
	}

	public void setvActivite(String vActivite) {
		this.vActivite = vActivite;
	}

	public String getActif() {
		return actif;
	}

	public void setActif(String actif) {
		this.actif = actif;
	}

	public boolean isOptimized() {
		return isOptimized;
	}

	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}

}
