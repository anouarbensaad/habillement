package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Calendar;
import java.util.Set;

import com.google.common.collect.ComparisonChain;

/**
 * @author $Ameni
 *
 */
public class OrdreFabricationReportValue implements Comparable<Object> {

	private Long id;
	private String numero;
	private Calendar dateIntroduction;
	private String observations;
	private Long quantite;
	private Long etat;
	private String etatDesignation;
	private Long produitId;
	private Long partieInterresId;
	private String partieInterresAbreviation;
	private String produitFamilleDesignation;
	private String produitSousFamilleDesignation;
	private String produitReference;
	private String produitDesignation;
	private Calendar dateLivraison;

	private Set<CompositionOfValue> compositionsOF;
	private Set<DetailOfValue> detailsOF;
	private Set<PhaseOfValue> phasesOF;

	private String numOFBeforeUpdate;

	// Added By Ghazi Atroussi on 04/09/2017

	private boolean a_planifier;
	private Long idSite;
	private Long quantitePlanifie;
	private String type;
	private String informationsStock;
	private String informationsAffectation;

	// Added by Zeineb

	private boolean special;
	private String traitement;
	private Integer cadence;
	private String atelier;

	private Calendar dateFin;

	private String typeOrdre;
	private String lieuLivraison;

	/* added on 17/04/2018 by hetteieb */
	private String numeroInterne;
	private Double prixUnitaire;

	
	

	private Long qtCoupe;
	
	private Long qtEngagement;
	
	private Long qtSortie;
	

	private Long qtFinition;
	
	
	private Long qtColisage;
	
	
	private Long qtExpedition;
	
	
	private Long qtSupp1;
	
	
	private Long qtSupp2;
	
	
	private Long qtSupp3;
	
	
	
	private String remarquesProduction;
	
	
	private Double temps;
	
	

	
	/**
	 * @return the numeroInterne
	 */
	public String getNumeroInterne() {
		return numeroInterne;
	}

	/**
	 * @param numeroInterne
	 *            the numeroInterne to set
	 */
	public void setNumeroInterne(String numeroInterne) {
		this.numeroInterne = numeroInterne;
	}

	/**
	 * @return the prixUnitair
	 */
	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	/**
	 * @param prixUnitair
	 *            the prixUnitair to set
	 */
	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public OrdreFabricationReportValue() {
	}

	public OrdreFabricationReportValue(Long id, String numero, Long quantite, Long produitId, Long partieInterresId) {
		super();
		this.id = id;
		this.numero = numero;
		this.quantite = quantite;
		this.produitId = produitId;
		this.partieInterresId = partieInterresId;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Calendar getDateIntroduction() {
		return dateIntroduction;
	}

	public void setDateIntroduction(Calendar dateIntroduction) {
		this.dateIntroduction = dateIntroduction;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public Set<CompositionOfValue> getCompositionsOF() {
		return compositionsOF;
	}

	public void setCompositionsOF(Set<CompositionOfValue> compositionsOF) {
		this.compositionsOF = compositionsOF;
	}

	public Set<DetailOfValue> getDetailsOF() {
		return detailsOF;
	}

	public void setDetailsOF(Set<DetailOfValue> detailsOF) {
		this.detailsOF = detailsOF;
	}

	public Set<PhaseOfValue> getPhasesOF() {
		return phasesOF;
	}

	public void setPhasesOF(Set<PhaseOfValue> phasesOF) {
		this.phasesOF = phasesOF;
	}

	public Long getEtat() {
		return etat;
	}

	public void setEtat(Long etat) {
		this.etat = etat;
	}

	public String getEtatDesignation() {
		return etatDesignation;
	}

	public void setEtatDesignation(String etatDesignation) {
		this.etatDesignation = etatDesignation;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Long getPartieInterresId() {
		return partieInterresId;
	}

	public void setPartieInterresId(Long partieInterresId) {
		this.partieInterresId = partieInterresId;
	}

	public String getProduitFamilleDesignation() {
		return produitFamilleDesignation;
	}

	public void setProduitFamilleDesignation(String produitFamilleDesignation) {
		this.produitFamilleDesignation = produitFamilleDesignation;
	}

	public String getProduitSousFamilleDesignation() {
		return produitSousFamilleDesignation;
	}

	public void setProduitSousFamilleDesignation(String produitSousFamilleDesignation) {
		this.produitSousFamilleDesignation = produitSousFamilleDesignation;
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

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getPartieInterresAbreviation() {
		return partieInterresAbreviation;
	}

	public void setPartieInterresAbreviation(String partieInterresAbreviation) {
		this.partieInterresAbreviation = partieInterresAbreviation;
	}

	public String getNumOFBeforeUpdate() {
		return numOFBeforeUpdate;
	}

	public void setNumOFBeforeUpdate(String numOFBeforeUpdate) {
		this.numOFBeforeUpdate = numOFBeforeUpdate;
	}

	@Override
	public String toString() {
		return "OrdreFabricationValue [id=" + id + ", numero=" + numero + ", dateIntroduction=" + dateIntroduction
				+ ", observations=" + observations + ", quantite=" + quantite + ", produitId=" + produitId
				+ ", partieInterresId=" + partieInterresId + ", partieInterresAbreviation=" + partieInterresAbreviation
				+ ", produitFamilleDesignation=" + produitFamilleDesignation + ", produitSousFamilleDesignation="
				+ produitSousFamilleDesignation + ", produitReference=" + produitReference + ", produitDesignation="
				+ produitDesignation + ", dateLivraison=" + dateLivraison + "]";
	}

	public boolean isA_planifier() {
		return a_planifier;
	}

	public void setA_planifier(boolean a_planifier) {
		this.a_planifier = a_planifier;
	}

	public Long getIdSite() {
		return idSite;
	}

	public void setIdSite(Long idSite) {
		this.idSite = idSite;
	}

	public Long getQuantitePlanifie() {
		return quantitePlanifie;
	}

	public void setQuantitePlanifie(Long quantitePlanifie) {
		this.quantitePlanifie = quantitePlanifie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInformationsStock() {
		return informationsStock;
	}

	public void setInformationsStock(String informationsStock) {
		this.informationsStock = informationsStock;
	}

	public String getInformationsAffectation() {
		return informationsAffectation;
	}

	public void setInformationsAffectation(String informationsAffectation) {
		this.informationsAffectation = informationsAffectation;
	}

	public boolean isSpecial() {
		return special;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public Integer getCadence() {
		return cadence;
	}

	public void setCadence(Integer cadence) {
		this.cadence = cadence;
	}

	public String getAtelier() {
		return atelier;
	}

	public void setAtelier(String designationSite) {
		this.atelier = designationSite;
	}

	public Calendar getDateFin() {
		return dateFin;
	}

	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}

	public String getTypeOrdre() {
		return typeOrdre;
	}

	public void setTypeOrdre(String typeOrdre) {
		this.typeOrdre = typeOrdre;
	}

	public String getLieuLivraison() {
		return lieuLivraison;
	}

	public void setLieuLivraison(String lieuLivraison) {
		this.lieuLivraison = lieuLivraison;
	}

	public Long getQtCoupe() {
		return qtCoupe;
	}

	public void setQtCoupe(Long qtCoupe) {
		this.qtCoupe = qtCoupe;
	}

	public Long getQtEngagement() {
		return qtEngagement;
	}

	public void setQtEngagement(Long qtEngagement) {
		this.qtEngagement = qtEngagement;
	}

	public Long getQtSortie() {
		return qtSortie;
	}

	public void setQtSortie(Long qtSortie) {
		this.qtSortie = qtSortie;
	}

	public Long getQtFinition() {
		return qtFinition;
	}

	public void setQtFinition(Long qtFinition) {
		this.qtFinition = qtFinition;
	}

	public Long getQtColisage() {
		return qtColisage;
	}

	public void setQtColisage(Long qtColisage) {
		this.qtColisage = qtColisage;
	}

	public Long getQtExpedition() {
		return qtExpedition;
	}

	public void setQtExpedition(Long qtExpedition) {
		this.qtExpedition = qtExpedition;
	}

	public Long getQtSupp1() {
		return qtSupp1;
	}

	public void setQtSupp1(Long qtSupp1) {
		this.qtSupp1 = qtSupp1;
	}

	public Long getQtSupp2() {
		return qtSupp2;
	}

	public void setQtSupp2(Long qtSupp2) {
		this.qtSupp2 = qtSupp2;
	}

	public Long getQtSupp3() {
		return qtSupp3;
	}

	public void setQtSupp3(Long qtSupp3) {
		this.qtSupp3 = qtSupp3;
	}

	public String getRemarquesProduction() {
		return remarquesProduction;
	}

	public void setRemarquesProduction(String remarquesProduction) {
		this.remarquesProduction = remarquesProduction;
	}

	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}



	@Override
	public int compareTo(Object o) {
		  
		OrdreFabricationReportValue m=(OrdreFabricationReportValue)o;
		
		return ComparisonChain.start()
		    		
		 	        .compare(this.getDateLivraison(),m.getDateLivraison())
		 	        .compare(this.getId(),m.getId())
		        .result();
	}
	
	

}
