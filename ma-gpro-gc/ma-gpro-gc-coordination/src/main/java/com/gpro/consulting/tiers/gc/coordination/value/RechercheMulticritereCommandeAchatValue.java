/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.Calendar;

/**
 * @author $Ameni
 *
 */
public class RechercheMulticritereCommandeAchatValue {

	/** The ref. */
	private String vReference;

	/** Type Partie Interessee **/
	private String vPartieInteressee;

	/** The etat commande. */
	private String vEtatCommande;

	/** The site . */
	private String vSite;
	
	/** The Cout Signe.*/
	private String vCoutSigne;
	
	/** The Cout Signe.*/
	private Long vCout;
	
	/** dateCommande debut */
	private Calendar vDateCommandeDu ;
	
	/** dateCommande fin */
	private Calendar vDateCommandeA;
	
	/** dateLivraison debut */
	private Calendar vDateLivraisonPrevueDu ;
	
	/** dateLivraison fin */
	private Calendar vDdateLivraisonPrevueA;

	/**
	 * @return the vReference
	 */
	public String getvReference() {
		return vReference;
	}

	/**
	 * @param vReference the vReference to set
	 */
	public void setvReference(String vReference) {
		this.vReference = vReference;
	}

	/**
	 * @return the vPartieInteressee
	 */
	public String getvPartieInteressee() {
		return vPartieInteressee;
	}

	/**
	 * @param vPartieInteressee the vPartieInteressee to set
	 */
	public void setvPartieInteressee(String vPartieInteressee) {
		this.vPartieInteressee = vPartieInteressee;
	}

	/**
	 * @return the vEtatCommande
	 */
	public String getvEtatCommande() {
		return vEtatCommande;
	}

	/**
	 * @param vEtatCommande the vEtatCommande to set
	 */
	public void setvEtatCommande(String vEtatCommande) {
		this.vEtatCommande = vEtatCommande;
	}

	/**
	 * @return the vSite
	 */
	public String getvSite() {
		return vSite;
	}

	/**
	 * @param vSite the vSite to set
	 */
	public void setvSite(String vSite) {
		this.vSite = vSite;
	}

	/**
	 * @return the vCoutSigne
	 */
	public String getvCoutSigne() {
		return vCoutSigne;
	}

	/**
	 * @param vCoutSigne the vCoutSigne to set
	 */
	public void setvCoutSigne(String vCoutSigne) {
		this.vCoutSigne = vCoutSigne;
	}

	/**
	 * @return the vCout
	 */
	public Long getvCout() {
		return vCout;
	}

	/**
	 * @param vCout the vCout to set
	 */
	public void setvCout(Long vCout) {
		this.vCout = vCout;
	}

	/**
	 * @return the vDateCommandeDu
	 */
	public Calendar getvDateCommandeDu() {
		return vDateCommandeDu;
	}

	/**
	 * @param vDateCommandeDu the vDateCommandeDu to set
	 */
	public void setvDateCommandeDu(Calendar vDateCommandeDu) {
		this.vDateCommandeDu = vDateCommandeDu;
	}

	/**
	 * @return the vDateCommandeA
	 */
	public Calendar getvDateCommandeA() {
		return vDateCommandeA;
	}

	/**
	 * @param vDateCommandeA the vDateCommandeA to set
	 */
	public void setvDateCommandeA(Calendar vDateCommandeA) {
		this.vDateCommandeA = vDateCommandeA;
	}

	/**
	 * @return the vDateLivraisonPrevueDu
	 */
	public Calendar getvDateLivraisonPrevueDu() {
		return vDateLivraisonPrevueDu;
	}

	/**
	 * @param vDateLivraisonPrevueDu the vDateLivraisonPrevueDu to set
	 */
	public void setvDateLivraisonPrevueDu(Calendar vDateLivraisonPrevueDu) {
		this.vDateLivraisonPrevueDu = vDateLivraisonPrevueDu;
	}

	/**
	 * @return the vDdateLivraisonPrevueA
	 */
	public Calendar getvDdateLivraisonPrevueA() {
		return vDdateLivraisonPrevueA;
	}

	/**
	 * @param vDdateLivraisonPrevueA the vDdateLivraisonPrevueA to set
	 */
	public void setvDdateLivraisonPrevueA(Calendar vDdateLivraisonPrevueA) {
		this.vDdateLivraisonPrevueA = vDdateLivraisonPrevueA;
	}
	
	
	
	
}
