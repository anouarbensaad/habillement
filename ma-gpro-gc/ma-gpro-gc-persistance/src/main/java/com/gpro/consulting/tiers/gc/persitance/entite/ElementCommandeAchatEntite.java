/**
 * 
 */
package com.gpro.consulting.tiers.gc.persitance.entite;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;

/**
* Classe: ElementCommandeAchatEntite
* @author $AMENI
* 
*/
@Entity
@Table(name = IConstanteGC.TABLE_GC_ELEMENTCOMMANDE)
public class ElementCommandeAchatEntite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2726372503163662101L;
	
	/** the Id. */
	@Id
	@SequenceGenerator(name="GC_ELEMENTCOMMANDE_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_ELEMENTCOMMANDE,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GC_ELEMENTCOMMANDE_ID_GENERATOR")
    private Long id;
	
	/** The quantite. */
	@Column(name = "quantite")
	private Long quantite;
	
	/** The prixUnitaire. */
	@Column(name = "prix_unitaire")
	private Long prixUnitiaire;
	
	/** The livre. */
	@Column(name="livre")
	private boolean livre;
	
	/** The prixTotal. */
	@Column(name = "prix_total")
	private Long prixTotal;
	
	/** The date creation. */
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	/** The date modification. */
	@Column(name="date_modification")
	private Calendar dateModification;

	/** The date suppression. */
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	/** The bl suppression. */
	@Column(name="bl_suppression")
	private boolean blSuppression;
	
	/** many-to-one association to Article**. *//*
	@ManyToOne(fetch=FetchType.LAZY , cascade=CascadeType.ALL)
	@JoinColumn(name = "eb_article_id")
	private ArticleEntite article;*/
	
	/** The articleId. */
	@Column(name = "eb_article_id")
	private Long articleId;
	
	/** many-to-one association to CommandeAchat**. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "gc_commandeachat_id")
	private CommandeAchatEntite commandeAchat;

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
	 * @return the dateCreation
	 */
	public Calendar getDateCreation() {
		return dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the dateModification
	 */
	public Calendar getDateModification() {
		return dateModification;
	}

	/**
	 * @param dateModification the dateModification to set
	 */
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	/**
	 * @return the dateSuppression
	 */
	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	/**
	 * @param dateSuppression the dateSuppression to set
	 */
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	/**
	 * @return the blSuppression
	 */
	public boolean isBlSuppression() {
		return blSuppression;
	}

	/**
	 * @param blSuppression the blSuppression to set
	 */
	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
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
	 * @return the commandeAchat
	 */
	public CommandeAchatEntite getCommandeAchat() {
		return commandeAchat;
	}

	/**
	 * @param commandeAchat the commandeAchat to set
	 */
	public void setCommandeAchat(CommandeAchatEntite commandeAchat) {
		this.commandeAchat = commandeAchat;
	}
	
}