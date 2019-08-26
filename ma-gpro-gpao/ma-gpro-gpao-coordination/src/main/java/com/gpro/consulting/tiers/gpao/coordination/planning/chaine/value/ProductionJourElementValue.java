package com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value;

import java.util.Calendar;

public class ProductionJourElementValue implements Comparable<ProductionJourElementValue>{

	private Long id;
	private Long idElementPlanning;
	private Long quantite;
	private Calendar date;
	private String observation;
	
	private Boolean blSuppression;
	private Calendar dateSuppression ;    
	private Calendar dateModification;   
	private Calendar dateCreation ; 
	
	private String oFDesignation ;
	private String chaineDesignation;
	
	private String designationPlanning ;

	private String phaseDesignation;
	
	
	
	private Long of;
	

	private Long chaine;
	
	
	private Long phase;
	
	

	private Long semaine;
	
	

	private String periode;
	


	

	
	private Double effectif;
	
	private Double rendement;
	
	
	private Long qteBefore;
	
	
	

	
	
	
	private Long partieInterresId;	

	
	
	private String produitDesignation;
	
	
	private String produitReference;
	
	
	private Long qteNonConf;
	
	
	
	
	

	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public Long getIdElementPlanning() {
		return idElementPlanning;
	}






	public void setIdElementPlanning(Long idElementPlanning) {
		this.idElementPlanning = idElementPlanning;
	}






	public Long getQuantite() {
		return quantite;
	}






	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}






	public Calendar getDate() {
		return date;
	}






	public void setDate(Calendar date) {
		this.date = date;
	}






	public String getObservation() {
		return observation;
	}






	public void setObservation(String observation) {
		this.observation = observation;
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






	public String getoFDesignation() {
		return oFDesignation;
	}






	public void setoFDesignation(String oFDesignation) {
		this.oFDesignation = oFDesignation;
	}






	public String getChaineDesignation() {
		return chaineDesignation;
	}






	public void setChaineDesignation(String chaineDesignation) {
		this.chaineDesignation = chaineDesignation;
	}






	public String getDesignationPlanning() {
		return designationPlanning;
	}






	public void setDesignationPlanning(String designationPlanning) {
		this.designationPlanning = designationPlanning;
	}






	public String getPhaseDesignation() {
		return phaseDesignation;
	}






	public void setPhaseDesignation(String phaseDesignation) {
		this.phaseDesignation = phaseDesignation;
	}






	public Long getOf() {
		return of;
	}






	public void setOf(Long of) {
		this.of = of;
	}






	public Long getChaine() {
		return chaine;
	}






	public void setChaine(Long chaine) {
		this.chaine = chaine;
	}






	public Long getPhase() {
		return phase;
	}






	public void setPhase(Long phase) {
		this.phase = phase;
	}






	public Long getSemaine() {
		return semaine;
	}






	public void setSemaine(Long semaine) {
		this.semaine = semaine;
	}






	public String getPeriode() {
		return periode;
	}






	public void setPeriode(String periode) {
		this.periode = periode;
	}






	public Double getEffectif() {
		return effectif;
	}






	public void setEffectif(Double effectif) {
		this.effectif = effectif;
	}






	public Double getRendement() {
		return rendement;
	}






	public void setRendement(Double rendement) {
		this.rendement = rendement;
	}






	public Long getQteBefore() {
		return qteBefore;
	}






	public void setQteBefore(Long qteBefore) {
		this.qteBefore = qteBefore;
	}






	public Long getPartieInterresId() {
		return partieInterresId;
	}






	public void setPartieInterresId(Long partieInterresId) {
		this.partieInterresId = partieInterresId;
	}






	public String getProduitDesignation() {
		return produitDesignation;
	}






	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}






	public String getProduitReference() {
		return produitReference;
	}






	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
	}






	public Long getQteNonConf() {
		return qteNonConf;
	}






	public void setQteNonConf(Long qteNonConf) {
		this.qteNonConf = qteNonConf;
	}






	@Override
	public int compareTo(ProductionJourElementValue element) {
		
		return (element.getId().compareTo(id));
	}
 
	
	

	
	
}
