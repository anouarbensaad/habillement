/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.Calendar;

/**
 * @author $Ameni
 *
 */
public class RechercheMulticritereCommandeVenteValue {

	/** The ref. */
	private String vReference;

	/** Type Partie Interessee **/
	private String vTypePartieInteressee;

	/** designation produit **/
	private String vProduit;

	/** The etat commande. */
	private String vEtatCommande;

	/** The Type commande . */
	private String vTypeCommande;
	
	/** dateIntroduction debut */
	private Calendar dateIntroductionDu ;
	
	/** dateIntroduction fin */
	private Calendar dateIntroductionA;
	
	/** dateLivraison debut */
	private Calendar dateLivraisonDu ;
	
	/** dateLivraison fin */
	private Calendar dateLivraisonA;
	
	//added on 16/03/2016, by Wahid Gazzah
	private Long quantiteDu;
	private Long quantiteA;
	private Double coutDu;
	private Double coutA;
	

	/************ Getter & Setter **************/
	/**
	 * @return the vReference
	 */
	public String getvReference() {
		return vReference;
	}

	/**
	 * @param vReference
	 *            the vReference to set
	 */
	public void setvReference(String vReference) {
		this.vReference = vReference;
	}

	/**
	 * @return the vTypePartieInteressee
	 */
	public String getvTypePartieInteressee() {
		return vTypePartieInteressee;
	}

	/**
	 * @param vTypePartieInteressee
	 *            the vTypePartieInteressee to set
	 */
	public void setvTypePartieInteressee(String vTypePartieInteressee) {
		this.vTypePartieInteressee = vTypePartieInteressee;
	}

	/**
	 * @return the vProduit
	 */
	public String getvProduit() {
		return vProduit;
	}

	/**
	 * @param vProduit
	 *            the vProduit to set
	 */
	public void setvProduit(String vProduit) {
		this.vProduit = vProduit;
	}

	/**
	 * @return the vEtatCommande
	 */
	public String getvEtatCommande() {
		return vEtatCommande;
	}

	/**
	 * @param vEtatCommande
	 *            the vEtatCommande to set
	 */
	public void setvEtatCommande(String vEtatCommande) {
		this.vEtatCommande = vEtatCommande;
	}

	/**
	 * @return the vTypeCommande
	 */
	public String getvTypeCommande() {
		return vTypeCommande;
	}

	/**
	 * @param vTypeCommande
	 *            the vTypeCommande to set
	 */
	public void setvTypeCommande(String vTypeCommande) {
		this.vTypeCommande = vTypeCommande;
	}

	/**
	 * @return the dateIntroductionDu
	 */
	public Calendar getDateIntroductionDu() {
		return dateIntroductionDu;
	}

	/**
	 * @param dateIntroductionDu the dateIntroductionDu to set
	 */
	public void setDateIntroductionDu(Calendar dateIntroductionDu) {
		this.dateIntroductionDu = dateIntroductionDu;
	}

	/**
	 * @return the dateIntroductionA
	 */
	public Calendar getDateIntroductionA() {
		return dateIntroductionA;
	}

	/**
	 * @param dateIntroductionA the dateIntroductionA to set
	 */
	public void setDateIntroductionA(Calendar dateIntroductionA) {
		this.dateIntroductionA = dateIntroductionA;
	}

	/**
	 * @return the dateLivraisonDu
	 */
	public Calendar getDateLivraisonDu() {
		return dateLivraisonDu;
	}

	/**
	 * @param dateLivraisonDu the dateLivraisonDu to set
	 */
	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}

	/**
	 * @return the dateLivraisonA
	 */
	public Calendar getDateLivraisonA() {
		return dateLivraisonA;
	}

	/**
	 * @param dateLivraisonA the dateLivraisonA to set
	 */
	public void setDateLivraisonA(Calendar dateLivraisonA) {
		this.dateLivraisonA = dateLivraisonA;
	}

	public Long getQuantiteDu() {
		return quantiteDu;
	}

	public void setQuantiteDu(Long quantiteDu) {
		this.quantiteDu = quantiteDu;
	}

	public Long getQuantiteA() {
		return quantiteA;
	}

	public void setQuantiteA(Long quantiteA) {
		this.quantiteA = quantiteA;
	}

	public Double getCoutDu() {
		return coutDu;
	}

	public void setCoutDu(Double coutDu) {
		this.coutDu = coutDu;
	}

	public Double getCoutA() {
		return coutA;
	}

	public void setCoutA(Double coutA) {
		this.coutA = coutA;
	}

}
