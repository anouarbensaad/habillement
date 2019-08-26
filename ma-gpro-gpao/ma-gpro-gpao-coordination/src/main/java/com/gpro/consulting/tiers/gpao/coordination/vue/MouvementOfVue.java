package com.gpro.consulting.tiers.gpao.coordination.vue;

import java.util.Calendar;

/**
 * @author Wahid Gazzah
 * @since 13/05/2016
 */
public class MouvementOfVue {
	
	private String referenceArticle;
	private String designationArticle;
	private String familleArticle;
	private Double quantiteAct;
	private Double quantiteLibre;
	private Double besoinOF;
	private Double aReserver;
	private String emplacement;
	private String type;
	private Boolean manque;
	private Double reservationOF;
	private Long qteOF;
	private Double reservationAct;
	private Long entiteStock;
	private Long typeArticle;
	private String lot;
	private Calendar dateEntree;
	private Double bu;
	private Double reservationOrigine;
	
	private Long rouleauxActuel;
	
	
	
	public Long getRouleauxActuel() {
		return rouleauxActuel;
	}


	public void setRouleauxActuel(Long rouleauxActuel) {
		this.rouleauxActuel = rouleauxActuel;
	}


	public MouvementOfVue() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MouvementOfVue(String referenceArticle, String designationArticle, String familleArticle, Double quantiteAct,
			Double quantiteLibre, Double besoinOF, Double aReserver, String emplacement, String type, Boolean manque,
			Double reservationOF, Long qteOF, Double reservationAct, Long entiteStock, Long typeArticle, String lot,
			Calendar dateEntree, Double bu) {
		super();
		this.referenceArticle = referenceArticle;
		this.designationArticle = designationArticle;
		this.familleArticle = familleArticle;
		this.quantiteAct = quantiteAct;
		this.quantiteLibre = quantiteLibre;
		this.besoinOF = besoinOF;
		this.aReserver = aReserver;
		this.emplacement = emplacement;
		this.type = type;
		this.manque = manque;
		this.reservationOF = reservationOF;
		this.qteOF = qteOF;
		this.reservationAct = reservationAct;
		this.entiteStock = entiteStock;
		this.typeArticle = typeArticle;
		this.lot = lot;
		this.dateEntree = dateEntree;
		this.bu = bu;
	}




	public Double getBu() {
		return bu;
	}


	public void setBu(Double bu) {
		this.bu = bu;
	}

	public String getReferenceArticle() {
		return referenceArticle;
	}
	public void setReferenceArticle(String referenceArticle) {
		this.referenceArticle = referenceArticle;
	}
	public String getDesignationArticle() {
		return designationArticle;
	}
	public void setDesignationArticle(String designationArticle) {
		this.designationArticle = designationArticle;
	}
	public String getFamilleArticle() {
		return familleArticle;
	}
	public void setFamilleArticle(String familleArticle) {
		this.familleArticle = familleArticle;
	}
	public Double getQuantiteAct() {
		return quantiteAct;
	}
	public void setQuantiteAct(Double quantiteAct) {
		this.quantiteAct = quantiteAct;
	}
	public Double getQuantiteLibre() {
		return quantiteLibre;
	}
	public void setQuantiteLibre(Double quantiteLibre) {
		this.quantiteLibre = quantiteLibre;
	}
	public Double getBesoinOF() {
		return besoinOF;
	}
	public void setBesoinOF(Double besoinOF) {
		this.besoinOF = besoinOF;
	}
	public Double getaReserver() {
		return aReserver;
	}
	public void setaReserver(Double aReserver) {
		this.aReserver = aReserver;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getManque() {
		return manque;
	}
	public void setManque(Boolean manque) {
		this.manque = manque;
	}
	public Double getReservationOF() {
		return reservationOF;
	}
	public void setReservationOF(Double reservationOF) {
		this.reservationOF = reservationOF;
	}
	public Long getQteOF() {
		return qteOF;
	}
	public void setQteOF(Long qteOF) {
		this.qteOF = qteOF;
	}
	public Double getReservationAct() {
		return reservationAct;
	}
	public void setReservationAct(Double reservationAct) {
		this.reservationAct = reservationAct;
	}
	public Long getEntiteStock() {
		return entiteStock;
	}
	public void setEntiteStock(Long entiteStock) {
		this.entiteStock = entiteStock;
	}
	public Long getTypeArticle() {
		return typeArticle;
	}
	public void setTypeArticle(Long typeArticle) {
		this.typeArticle = typeArticle;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public Calendar getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}


	public Double getReservationOrigine() {
		return reservationOrigine;
	}


	public void setReservationOrigine(Double reservationOrigine) {
		this.reservationOrigine = reservationOrigine;
	}
	
}
