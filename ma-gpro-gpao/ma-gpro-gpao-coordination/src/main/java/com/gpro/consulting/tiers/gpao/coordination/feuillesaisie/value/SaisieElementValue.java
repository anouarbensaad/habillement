package com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public class SaisieElementValue implements Comparable<SaisieElementValue>{
	
	private Long id;
	private String codeBarre;
	private Long paquetId;
	private Long elementGammeId;
	private Long feuilleId;
	private Long quantite;
	
	private Long paquetNum;
	private Long paquetOrdre;
	private String operationCode;
	private String operationDesignation;
	private Double temps;
	private String ordreFabricationNumero;
	private Double minutage;
	
	private Long ordre;
		
	
	private Long idClient;
	
	
	private Long idProduit;
	
	
	
	private Long idOperation;
	
	
	private Boolean comptage;
	
	
	
	private Long idOF;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@JsonIgnore
	private Boolean blSuppression;
	@JsonIgnore
	private Calendar dateSuppression;
	@JsonIgnore
	private Calendar dateCreation;
	@JsonIgnore
	private Calendar dateModification;
	@JsonIgnore
	private String version;
	
	public int compareTo(SaisieElementValue element) {
		return (element.getCodeBarre().compareTo(codeBarre));
	}
	
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
	public Long getFeuilleId() {
		return feuilleId;
	}
	public void setFeuilleId(Long feuilleId) {
		this.feuilleId = feuilleId;
	}
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
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

	public Long getPaquetNum() {
		return paquetNum;
	}

	public void setPaquetNum(Long paquetNum) {
		this.paquetNum = paquetNum;
	}

	public Long getPaquetOrdre() {
		return paquetOrdre;
	}

	public void setPaquetOrdre(Long paquetOrdre) {
		this.paquetOrdre = paquetOrdre;
	}
	

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationDesignation() {
		return operationDesignation;
	}

	public void setOperationDesignation(String operationDesignation) {
		this.operationDesignation = operationDesignation;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
	}

	public Double getMinutage() {
		return minutage;
	}

	public void setMinutage(Double minutage) {
		this.minutage = minutage;
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


	public Boolean getComptage() {
		return comptage;
	}

	public void setComptage(Boolean comptage) {
		this.comptage = comptage;
	}

	public Long getIdOF() {
		return idOF;
	}

	public void setIdOF(Long idOF) {
		this.idOF = idOF;
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	@Override
	public String toString() {
		return "SaisieElementValue [id=" + id + ", feuilleId=" + feuilleId + ", blSuppression=" + blSuppression + "]";
	}

}