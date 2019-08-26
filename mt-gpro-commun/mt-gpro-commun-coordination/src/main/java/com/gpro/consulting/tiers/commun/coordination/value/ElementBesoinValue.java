package com.gpro.consulting.tiers.commun.coordination.value;

import java.util.Calendar;

public class ElementBesoinValue {
	
	private Long    idElementBesoinValue;
	private Double  quantite;
	private boolean bloQuatite;
	private int    ordre;
	private String phase;
	private Long ficheBesoinId;
//	private boolean  blSuppression;
//	private Calendar dateSuppression;
//	private Calendar dateModification;
	private Calendar dateCreation;

    private Long eb_taille_id;	
	private Long eb_couleur_id;	
	//private Long eb_article_id;
	
	private String designationTaille;
	private String designationCouleur;
	
	private ArticleValue article;
	
	
	/**
	 * type:
	 * 1 -> Fourniture
	 * 2 -> Tissu
	 * 3 -> Fil à Coudre
	 */
	//Added on 07/04/2016, By Ameni Berrich
	private String   type;
	
	
	/*
	private CouleurValue couleur;
	private TailleValue taille;*/
	
	public Long getIdElementBesoinValue() {
		return idElementBesoinValue;
	}
	public void setIdElementBesoinValue(Long idElementBesoinValue) {
		this.idElementBesoinValue = idElementBesoinValue;
	}
	public Double getQuantite() {
		return quantite;
	}
	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}
	public boolean isBloQuatite() {
		return bloQuatite;
	}
	public void setBloQuatite(boolean bloQuatite) {
		this.bloQuatite = bloQuatite;
	}
	public int getOrdre() {
		return ordre;
	}
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
//	public Long getArticleId() {
//		return articleId;
//	}
//	public void setArticleId(Long articleId) {
//		this.articleId = articleId;
//	}
//	public Long getCouleurId() {
//		return couleurId;
//	}
//	public void setCouleurId(Long couleurId) {
//		this.couleurId = couleurId;
//	}
//	public Long getTailleId() {
//		return tailleId;
//	}
//	public void setTailleId(Long tailleId) {
//		this.tailleId = tailleId;
//	}
	public Long getFicheBesoinId() {
		return ficheBesoinId;
	}
	public void setFicheBesoinId(Long ficheBesoinId) {
		this.ficheBesoinId = ficheBesoinId;
	}
//	public boolean isBlSuppression() {
//		return blSuppression;
//	}
//	public void setBlSuppression(boolean blSuppression) {
//		this.blSuppression = blSuppression;
//	}
//	public Calendar getDateSuppression() {
//		return dateSuppression;
//	}
//	public void setDateSuppression(Calendar dateSuppression) {
//		this.dateSuppression = dateSuppression;
//	}
//	public Calendar getDateModification() {
//		return dateModification;
//	}
//	public void setDateModification(Calendar dateModification) {
//		this.dateModification = dateModification;
//	}	
	public Calendar getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	public ArticleValue getArticle() {
		return article;
	}
	public void setArticle(ArticleValue article) {
		this.article = article;
	}
//	public CouleurValue getCouleur() {
//		return couleur;
//	}
//	public void setCouleur(CouleurValue couleur) {
//		this.couleur = couleur;
//	}
//	public TailleValue getTaille() {
//		return taille;
//	}
//	public void setTaille(TailleValue taille) {
//		this.taille = taille;
//	}
	public Long getEb_taille_id() {
		return eb_taille_id;
	}
	public void setEb_taille_id(Long eb_taille_id) {
		this.eb_taille_id = eb_taille_id;
	}
	public Long getEb_couleur_id() {
		return eb_couleur_id;
	}
	public void setEb_couleur_id(Long eb_couleur_id) {
		this.eb_couleur_id = eb_couleur_id;
	}
//	public Long getEb_article_id() {
//		return eb_article_id;
//	}
//	public void setEb_article_id(Long eb_article_id) {
//		this.eb_article_id = eb_article_id;
//	}
	public String getDesignationTaille() {
		return designationTaille;
	}
	public void setDesignationTaille(String designationTaille) {
		this.designationTaille = designationTaille;
	}
	public String getDesignationCouleur() {
		return designationCouleur;
	}
	public void setDesignationCouleur(String designationCouleur) {
		this.designationCouleur = designationCouleur;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ElementBesoinValue [idElementBesoinValue="
				+ idElementBesoinValue + ", quantite=" + quantite
				+ ", bloQuatite=" + bloQuatite + ", ordre=" + ordre
				+ ", phase=" + phase + ", ficheBesoinId=" + ficheBesoinId
				+ ", dateCreation=" + dateCreation + ", eb_taille_id="
				+ eb_taille_id + ", eb_couleur_id=" + eb_couleur_id
				+ ", designationTaille=" + designationTaille
				+ ", designationCouleur=" + designationCouleur 
				+ ", article=" + article.toString() + "]";
	}



	
	
}
