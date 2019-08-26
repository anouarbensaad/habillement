package com.gpro.consulting.tiers.commun.coordination.value;

import java.util.Calendar;
import java.util.Set;


/**
 * The Class ArticleValue.
 *
 * @author $Ghazi
 */

public class ArticleValue implements Comparable<Object>{

	private Long id;

	/** The ref. */
	
	private String ref;

	/** The dedignation. */
	private String designation;
	
	/** uidImage */
	private String uidImage;

	/** The prix unitaire. */
	
	private Double pu;

	/** The prix moyen pondere. */
	
	private Double pmp;

	/** The producteur. */
	
	private String producteur;

	/** The date introduction. */
	
	private Calendar dateIntroduction;

	/** The laize. */
	
	private Double laize;

	/** The poids. */
	private Double poids;
	
	/** The tare. */
	
	private Double tare;

	/** The poids_brut. */
	
	private Double poidsBrut;


	/** The observation. */
	
	private String observation;

	/** The pi entite. */
	
	private Long  piEntite;

	/** The sous famille entite. */
	
	private Long sousFamilleArtEntite;
	
/** The sous famille entite Designation. */
	
	private String sousFamilleArtEntiteDesignation;
	
/** The famille entite Designation. */
	
	private String familleArticleDesignation;

/** The TypeArticle entite Designation. */
	
	private String typeArticleDesignation;
	
	/** The site entite. */
	
	private Long siteEntite;
	
/** The site entite Designation. */
	
	private String siteEntiteDesignation;

	/** The grosseur entite. */
	
	private Long grosseurEntite;

	/** The metrage entite. */
	
	private Long metrageEntite;

	/** The unite entite. */
	
	private Long uniteEntite;
	
	/** The matiere entite. */
	
	private Long matiereEntite;	
	
	/**couleur **-/
	 */
	
	private String couleur ;
	
	/**Methode de gestion *****/
	
	private String methodeGestion;
	
	private String codeFournisseur ;
	
	private String refBeforeUpdate;
	
	private String codeFournisseurBeforeUpdate;
	
	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	/*** Documents **//////
	private Set<DocumentArticleValue> documentEntites;
	
	/*** Seuils d'approvisionnement **//////
	private Set<SeuilArticleValue> seuilEntities;
	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */





	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ArticleValue [id=" + id + ", ref=" + ref + ", designation="
				+ designation + ", uidImage=" + uidImage + ", pu=" + pu
				+ ", pmp=" + pmp + ", producteur=" + producteur
				+ ", dateIntroduction=" + dateIntroduction + ", laize=" + laize
				+ ", poids=" + poids + ", tare=" + tare + ", poidsBrut="
				+ poidsBrut + ", observation=" + observation + ", piEntite="
				+ piEntite + ", sousFamilleArtEntite=" + sousFamilleArtEntite
				+ ", sousFamilleArtEntiteDesignation="
				+ sousFamilleArtEntiteDesignation
				+ ", familleArticleDesignation=" + familleArticleDesignation
				+ ", typeArticleDesignation=" + typeArticleDesignation
				+ ", siteEntite=" + siteEntite + ", siteEntiteDesignation="
				+ siteEntiteDesignation + ", grosseurEntite=" + grosseurEntite
				+ ", metrageEntite=" + metrageEntite + ", uniteEntite="
				+ uniteEntite + ", matiereEntite=" + matiereEntite
				+ ", couleur=" + couleur + ", methodeGestion="
				+ methodeGestion + ", documentEntites=" + documentEntites
				+ ", seuilEntities=" + seuilEntities + "]";
	}

	/**
	 * @return the uidImage
	 */
	public String getUidImage() {
		return uidImage;
	}

	/**
	 * @param uidImage the uidImage to set
	 */
	public void setUidImage(String uidImage) {
		this.uidImage = uidImage;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getRef() {
		return ref;
	}



	public void setRef(String ref) {
		this.ref = ref;
	}



	public String getDesignation() {
		return designation;
	}



	public void setDesignation(String designation) {
		this.designation = designation;
	}



	public Double getPu() {
		return pu;
	}



	public void setPu(Double pu) {
		this.pu = pu;
	}



	public Double getPmp() {
		return pmp;
	}



	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}



	public String getProducteur() {
		return producteur;
	}



	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}



	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}



	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}



	public Double getLaize() {
		return laize;
	}



	public void setLaize(Double laize) {
		this.laize = laize;
	}



	public Double getPoids() {
		return poids;
	}



	public void setPoids(Double poids) {
		this.poids = poids;
	}



	public Double getTare() {
		return tare;
	}



	public void setTare(Double tare) {
		this.tare = tare;
	}



	public Double getPoidsBrut() {
		return poidsBrut;
	}



	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}



	public String getObservation() {
		return observation;
	}



	public void setObservation(String observation) {
		this.observation = observation;
	}



	public Long getPiEntite() {
		return piEntite;
	}



	public void setPiEntite(Long piEntite) {
		this.piEntite = piEntite;
	}



	public Long getSousFamilleArtEntite() {
		return sousFamilleArtEntite;
	}



	public void setSousFamilleArtEntite(Long sousFamilleArtEntite) {
		this.sousFamilleArtEntite = sousFamilleArtEntite;
	}


	/**
	 * @return the sousFamilleArtEntiteDesignation
	 */
	public String getSousFamilleArtEntiteDesignation() {
		return sousFamilleArtEntiteDesignation;
	}



	/**
	 * @param sousFamilleArtEntiteDesignation the sousFamilleArtEntiteDesignation to set
	 */
	public void setSousFamilleArtEntiteDesignation(
			String sousFamilleArtEntiteDesignation) {
		this.sousFamilleArtEntiteDesignation = sousFamilleArtEntiteDesignation;
	}


	/**
	 * @return the familleArticleDesignation
	 */
	public String getFamilleArticleDesignation() {
		return familleArticleDesignation;
	}



	/**
	 * @param familleArticleDesignation the familleArticleDesignation to set
	 */
	public void setFamilleArticleDesignation(String familleArticleDesignation) {
		this.familleArticleDesignation = familleArticleDesignation;
	}



	/**
	 * @return the typeArticleDesignation
	 */
	public String getTypeArticleDesignation() {
		return typeArticleDesignation;
	}



	/**
	 * @param typeArticleDesignation the typeArticleDesignation to set
	 */
	public void setTypeArticleDesignation(String typeArticleDesignation) {
		this.typeArticleDesignation = typeArticleDesignation;
	}



	/**
	 * @return the siteEntite
	 */
	public Long getSiteEntite() {
		return siteEntite;
	}



	/**
	 * @param siteEntite the siteEntite to set
	 */
	public void setSiteEntite(Long siteEntite) {
		this.siteEntite = siteEntite;
	}



	/**
	 * @return the siteEntiteDesignation
	 */
	public String getSiteEntiteDesignation() {
		return siteEntiteDesignation;
	}



	/**
	 * @param siteEntiteDesignation the siteEntiteDesignation to set
	 */
	public void setSiteEntiteDesignation(String siteEntiteDesignation) {
		this.siteEntiteDesignation = siteEntiteDesignation;
	}



	public Long getGrosseurEntite() {
		return grosseurEntite;
	}



	public void setGrosseurEntite(Long grosseurEntite) {
		this.grosseurEntite = grosseurEntite;
	}



	public Long getMetrageEntite() {
		return metrageEntite;
	}



	public void setMetrageEntite(Long metrageEntite) {
		this.metrageEntite = metrageEntite;
	}



	public Long getUniteEntite() {
		return uniteEntite;
	}



	public void setUniteEntite(Long uniteEntite) {
		this.uniteEntite = uniteEntite;
	}



	public Long getMatiereEntite() {
		return matiereEntite;
	}



	public void setMatiereEntite(Long matiereEntite) {
		this.matiereEntite = matiereEntite;
	}



	public String getCouleur() {
		return couleur;
	}



	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}



	public String getMethodeGestion() {
		return methodeGestion;
	}



	public void setMethodeGestion(String methodeGestion) {
		this.methodeGestion = methodeGestion;
	}



	public Set<DocumentArticleValue> getDocumentEntites() {
		return documentEntites;
	}



	public void setDocumentEntites(Set<DocumentArticleValue> documentEntites) {
		this.documentEntites = documentEntites;
	}



	public Set<SeuilArticleValue> getSeuilEntities() {
		return seuilEntities;
	}



	public void setSeuilEntities(Set<SeuilArticleValue> seuilEntities) {
		this.seuilEntities = seuilEntities;
	}

	@Override
	public int compareTo(Object o) {
		ArticleValue element= (ArticleValue)o;
		return (element.getId().compareTo(id));
	}

	public String getRefBeforeUpdate() {
		return refBeforeUpdate;
	}

	public void setRefBeforeUpdate(String refBeforeUpdate) {
		this.refBeforeUpdate = refBeforeUpdate;
	}

	public String getCodeFournisseurBeforeUpdate() {
		return codeFournisseurBeforeUpdate;
	}

	public void setCodeFournisseurBeforeUpdate(String codeFournisseurBeforeUpdate) {
		this.codeFournisseurBeforeUpdate = codeFournisseurBeforeUpdate;
	}
	
	
}
