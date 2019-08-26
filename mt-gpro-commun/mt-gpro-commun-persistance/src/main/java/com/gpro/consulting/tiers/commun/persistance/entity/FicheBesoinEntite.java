package com.gpro.consulting.tiers.commun.persistance.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.coordination.IConstante;

@Entity
@Table(name=IConstante.TABLE_Fiche_BESOIN)
public class FicheBesoinEntite implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5354350853219129255L;
	
	/** The id. */
	@Id
	@SequenceGenerator(name="EB_FICHE_BESOIN_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_FICHE_BESOIN,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EB_FICHE_BESOIN_ID_GENERATOR")
    private Long id;	
	
	@Column(name="date_introduction")
	private Calendar dateIntroduction;
	
	@Column(name="responsable")
	private String responsable;	
	
	@Column(name="observations")
	private String observation;	
	
	@Column(name="bl_suppression")
	private boolean blSuppression;
	
	@Column(name="date_suppression")
	private Calendar dateSuppression;
	
	@Column(name="date_modification")
	private Calendar dateModification;
	
	@Column(name="date_creation")
	private Calendar dateCreation;
	
	@OneToOne
    @JoinColumn(name="eb_produit_id")
	private ProduitEntity produitEntity;
	
	/** *** many-to-one association to ElementFicheBesoinEntite. */
	@OneToMany(fetch=FetchType.LAZY,mappedBy="ficheBesoin", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ElementFicheBesoinEntite> elementFicheBesoin;	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public boolean isBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public ProduitEntity getProduitEntity() {
		return produitEntity;
	}

	public void setProduitEntity(ProduitEntity produitEntity) {
		this.produitEntity = produitEntity;
	}

	public Set<ElementFicheBesoinEntite> getElementFicheBesoin() {
		return elementFicheBesoin;
	}

	public void setElementFicheBesoin(
			Set<ElementFicheBesoinEntite> elementFicheBesoin) {
		
		if(this.elementFicheBesoin != null){
			
			// Mode update
			this.elementFicheBesoin.clear();
			this.elementFicheBesoin.addAll(elementFicheBesoin);
			
		}else{
			//Mode create
			this.elementFicheBesoin = elementFicheBesoin;
		}
			
	}
	
	
	
	
	

}
