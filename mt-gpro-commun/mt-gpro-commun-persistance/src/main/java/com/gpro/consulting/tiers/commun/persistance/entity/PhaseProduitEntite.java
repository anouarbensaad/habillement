package com.gpro.consulting.tiers.commun.persistance.entity;

import java.io.Serializable;

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

import com.gpro.consulting.tiers.commun.coordination.IConstante;

/**
* Classe: PhaseProduit
* @author $AMENI
* 
*/
/**
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name = IConstante.TABLE_EB_PHASE_PRODUIT)
public class PhaseProduitEntite implements Serializable {
	
	private static final long serialVersionUID = 4937515058050891151L;

	@Id
	@SequenceGenerator(name="EB_PHASE_PRODUIT_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_PHASE_PRODUIT,allocationSize=1)
	// allocationSize = 1
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EB_PHASE_PRODUIT_ID_GENERATOR")
    private Long id;
	
	

	/** The prix. */
	@Column(name = "prix")
	private Double prix;

	/** The devise. */
	@Column(name = "devise")
	private String devise;
	
	/** The variante. */
	@Column(name = "variante")
	private String variante;
	
	/** The ordre. */
	@Column(name = "ordre")
	private String ordre;
	
	/** The eb_phase_id. */
	@Column(name = "eb_phase_id")
	private Long eb_phase_id;
	
	/** many-to-one association to Produit**. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_produit_id")
	private ProduitEntity produit;

	/************* Getters & Setters *****************/
	
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
	 * @param prix
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

	/**
	 * @return the produit
	 */
	public ProduitEntity getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(ProduitEntity produit) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return super.toString();
	}	
	
	public Long getEb_phase_id() {
		return eb_phase_id;
	}

	public void setEb_phase_id(Long eb_phase_id) {
		this.eb_phase_id = eb_phase_id;
	}

	/************* Equals & ToString *****************/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
