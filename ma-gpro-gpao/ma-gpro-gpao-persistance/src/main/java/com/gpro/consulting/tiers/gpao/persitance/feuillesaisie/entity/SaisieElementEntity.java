package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */
@Entity
@Table(name=IConstante.TABLE_GP_ELEMENT_SAISIE)
public class SaisieElementEntity implements Serializable {
	
	private static final long serialVersionUID = -4195611806305175159L;

	@Id
	@SequenceGenerator(name="ELEMENT_SAISIE_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_ELEMENT_SAISIE, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ELEMENT_SAISIE_ID_GENERATOR")
    private Long id;
	
	@Column(name = "CODE_BARRE")
	private String codeBarre;
	
	@Column(name = "PAQUET_ID")
	private Long paquetId;
	
	@Column(name = "ELEMENT_GAMME_ID")
	private Long elementGammeId;
	
	@ManyToOne
	@JoinColumn(name = "FEUILLE_ID")
	private FeuilleSaisieEntity fiheSaisie;
	
	@Column(name = "QUANTITE")
	private Long quantite;

	@Column(name = "num_of")
	private String ordreFabricationNumero;
	
	
	@Column(name = "ordre")
	private Long ordre;
	
	
	// Colonnes Optimisation 
	// Ghazi 27/06/2019
	
	
	@Column(name = "client")
	private Long idClient;
	
	@Column(name = "produit_id")
	private Long idProduit;
	
	
	@Column(name = "operation_id")
	private Long idOperation;
	
	@Column(name = "comptage")
	private Boolean comptage;
	
	
	@Column(name = "of_id")
	private Long idOF;
	
	
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodeBarre() {
		return codeBarre;
	}

	public void setCodeBarre(String codeBarre) {
		this.codeBarre = codeBarre;
	}

	public Long getPaquetId() {
		return paquetId;
	}

	public void setPaquetId(Long paquetId) {
		this.paquetId = paquetId;
	}

	public Long getElementGammeId() {
		return elementGammeId;
	}

	public void setElementGammeId(Long elementGammeId) {
		this.elementGammeId = elementGammeId;
	}

	public FeuilleSaisieEntity getFiheSaisie() {
		return fiheSaisie;
	}

	public void setFiheSaisie(FeuilleSaisieEntity fiheSaisie) {
		this.fiheSaisie = fiheSaisie;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "SaisieElementEntity [id=" + id + ", fiheSaisie=" + fiheSaisie + ", quantite=" + quantite + "]";
	}

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public Long getIdOperation() {
		return idOperation;
	}

	public void setIdOperation(Long idOperation) {
		this.idOperation = idOperation;
	}

	

	public Long getIdOF() {
		return idOF;
	}

	public void setIdOF(Long idOF) {
		this.idOF = idOF;
	}

	public Boolean getComptage() {
		return comptage;
	}

	public void setComptage(Boolean comptage) {
		this.comptage = comptage;
	}
	
	
	
	
}
