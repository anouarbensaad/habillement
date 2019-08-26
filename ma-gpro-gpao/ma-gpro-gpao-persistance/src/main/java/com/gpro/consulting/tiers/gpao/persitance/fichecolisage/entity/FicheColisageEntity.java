package com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;


@Entity
@Table(name=IConstante.TABLE_GP_FICHECOLISAGE)
public class FicheColisageEntity implements Serializable{
	
	private static final long serialVersionUID = -3004815224021179635L;
	
	@Id
	@SequenceGenerator(name="FICHECOLISAGE_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_FICHECOLISAGE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FICHECOLISAGE_ID_GENERATOR")
    private Long id;
	
	@Column(name = "DATE_LANCEMENT")
	private Calendar dateLancement;
	
	@Column(name = "OBSERVATIONS")
	private String observations;
	
	@Column(name = "GP_OF_ID")
	private Long ordreFabricationId;
	
	@Column(name = "NOMBRE_COLIS")
	private Long nombreColis;
	
	@Column(name = "QUANTITE_COLIS")
	private Long quantiteColis;
	
	@OneToMany(mappedBy = "ficheColisage", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<ColisEntity> listColis;
	
	@OneToMany(mappedBy = "ficheColisage", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DetailsColisageEntity> listDetails;
	
	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;
	
	@Column(name = "VERSION")
	private String version;
	
	@Column(name = "NUMERO_OF")
	private String numeroOf ;
	
	
	@Column(name = "SEMAINE")
	private String semaine;
	
	
//	@Column(name = "REFERENCE")
//	private String reference ;
//	
//	@Column(name = "DESIGNATON")
//	private String designation ;
//	
	@Column(name = "QUANTITE_TOTALE")
	private Long quantiteTotale ;
		
	@Column(name = "PRODUIT_ID")
	private Long produitId;
	
	@Column(name = "PRODUIT_REFERENCE")
	private String produitReference;
	
	@Column(name = "PRODUIT_DESIGNATION")
	private String produitDesignation;
	
	@Column(name = "CLIENT_ID")
	private Long clientId;
	
	@Column(name = "CLIENT_ABREVIATION")
	private String clientAbreviation;
	
	@Column(name = "CLIENT_REFERENCE")
	private String clientReference;
	
	@Column(name = "COULEUR")
	private String  couleur ;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Calendar getDateLancement() {
		return dateLancement;
	}

	public void setDateLancement(Calendar dateLancement) {
		this.dateLancement = dateLancement;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}

	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
	}

	public Boolean getBlSuppression() {
		return blSuppression;
	}

	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}

	public Calendar getDateSuppression() {
		return dateSuppression;
	}

	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}

	public Calendar getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}

	public Calendar getDateModification() {
		return dateModification;
	}

	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getNombreColis() {
		return nombreColis;
	}

	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	public Long getQuantiteColis() {
		return quantiteColis;
	}

	public void setQuantiteColis(Long quantiteColis) {
		this.quantiteColis = quantiteColis;
	}

	public Set<ColisEntity> getListColis() {
		return listColis;
	}

	public void setListColis(Set<ColisEntity> listColis) {
		this.listColis = listColis;
	}

	public Set<DetailsColisageEntity> getListDetails() {
		return listDetails;
	}

	public void setListDetails(Set<DetailsColisageEntity> listDetails) {
		this.listDetails = listDetails;
	}

	public String getNumeroOf() {
		return numeroOf;
	}

	public void setNumeroOf(String numeroOf) {
		this.numeroOf = numeroOf;
	}

//	public String getReference() {
//		return reference;
//	}
//
//	public void setReference(String reference) {
//		this.reference = reference;
//	}
//
//	public String getDesignation() {
//		return designation;
//	}
//
//	public void setDesignation(String designation) {
//		this.designation = designation;
//	}

	public Long getQuantiteTotale() {
		return quantiteTotale;
	}

	public void setQuantiteTotale(Long quantiteTotale) {
		this.quantiteTotale = quantiteTotale;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public String getProduitReference() {
		return produitReference;
	}

	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}

	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}

	public String getClientReference() {
		return clientReference;
	}

	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getSemaine() {
		return semaine;
	}

	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}

	

}
