package com.gpro.consulting.tiers.commun.coordination.baseinfo.value;


/**
 * @author Wahid Gazzah
 * @since 01/06/2016
 *
 */
public class BaseInfoValue implements Comparable<BaseInfoValue>{

    private Long id;
	private String designation;
	private String valeur;
	private String unite;
	private String logo;
	private boolean actif;
	
	
	
	
	private Long sortieChaine;
	
	
	
	private Long engagement;
	
	
	
	private Long sortieCoupe;
	
	
	
	private Long conditionnement;	
	
	
	private Long eclatement;		
	
	
	
	private Long supp1;	
	
	
	private Long supp2;	
	
	
	
	
	
	
	public int compareTo(BaseInfoValue element) {
		return (element.getId().compareTo(id));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getValeur() {
		return valeur;
	}
	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	public String getUnite() {
		return unite;
	}
	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public boolean isActif() {
		return actif;
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public Long getSortieChaine() {
		return sortieChaine;
	}

	public void setSortieChaine(Long sortieChaine) {
		this.sortieChaine = sortieChaine;
	}

	public Long getEngagement() {
		return engagement;
	}

	public void setEngagement(Long engagement) {
		this.engagement = engagement;
	}

	public Long getSortieCoupe() {
		return sortieCoupe;
	}

	public void setSortieCoupe(Long sortieCoupe) {
		this.sortieCoupe = sortieCoupe;
	}

	public Long getConditionnement() {
		return conditionnement;
	}

	public void setConditionnement(Long conditionnement) {
		this.conditionnement = conditionnement;
	}

	public Long getEclatement() {
		return eclatement;
	}

	public void setEclatement(Long eclatement) {
		this.eclatement = eclatement;
	}

	public Long getSupp1() {
		return supp1;
	}

	public void setSupp1(Long supp1) {
		this.supp1 = supp1;
	}

	public Long getSupp2() {
		return supp2;
	}

	public void setSupp2(Long supp2) {
		this.supp2 = supp2;
	}
	
	
	
}
