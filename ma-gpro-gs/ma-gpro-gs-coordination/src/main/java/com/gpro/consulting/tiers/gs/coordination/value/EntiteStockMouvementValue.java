package com.gpro.consulting.tiers.gs.coordination.value;

import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class EntiteStockMouvementValue.
 */
public class EntiteStockMouvementValue implements Comparable<Object> {

	/** The entite stock id. */
	private Long entiteStockId;
	/** The reference. */
	private String reference;

	private String designation;
	/** The famille. */
	private String famille;
	/** OA **/
	private String OA;
	/** PT **/
	private Double PT;

	/** The sous famille. */
	private String sousFamille;

	/** The fin cone. */
	private Long finCone;

	/** The poid. */
	private Double poid;

	/** The cone . */
	private Long cone;

	/** The qte of. */
	private Double qteOf;

	/** The qte r. */
	private Double qteR;

	/** The unite. */
	private String unite;

	/** The pu. */
	private Double pu;

	/** The pmp. */
	private Double pmp;

	/** The empl. */
	private String empl;

	/** The qte actuel. */
	private Double qteActuel;

	private Integer nbRouleau;

	/**
	 * Hajer AMRI 10/01/2017
	 * 
	 * @return
	 */

	/** The reference lot. */
	private String referenceLot;

	/** The date entree. */
	private Calendar dateEntree;

	private String codeFournisseur;

	private Long rouleauxActuels;
	
	public Double getPT() {
		return PT;
	}

	public void setPT(Double pT) {
		PT = pT;
	}

	public String getOA() {
		return OA;
	}

	public void setOA(String oA) {
		OA = oA;
	}

	public Integer getNbRouleau() {
		return nbRouleau;
	}

	public void setNbRouleau(Integer nbRouleau) {
		this.nbRouleau = nbRouleau;
	}

	private String etat;

	/**
	 * Gets the reference.
	 *
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Sets the reference.
	 *
	 * @param reference
	 *            the new reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Gets the famille.
	 *
	 * @return the famille
	 */
	public String getFamille() {
		return famille;
	}

	/**
	 * Sets the famille.
	 *
	 * @param famille
	 *            the new famille
	 */
	public void setFamille(String famille) {
		this.famille = famille;
	}

	/**
	 * Gets the sous famille.
	 *
	 * @return the sous famille
	 */
	public String getSousFamille() {
		return sousFamille;
	}

	/**
	 * Sets the sous famille.
	 *
	 * @param sousFamille
	 *            the new sous famille
	 */
	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}

	public Long getFinCone() {
		return finCone;
	}

	public void setFinCone(Long finCone) {
		this.finCone = finCone;
	}

	/**
	 * Gets the poid.
	 *
	 * @return the poid
	 */
	public Double getPoid() {
		return poid;
	}

	/**
	 * Sets the poid.
	 *
	 * @param poid
	 *            the new poid
	 */
	public void setPoid(Double poid) {
		this.poid = poid;
	}

	/**
	 * Gets the qte of.
	 *
	 * @return the qte of
	 */
	public Double getQteOf() {
		return qteOf;
	}

	/**
	 * Sets the qte of.
	 *
	 * @param qteOf
	 *            the new qte of
	 */
	public void setQteOf(Double qteOf) {
		this.qteOf = qteOf;
	}

	/**
	 * Gets the qte r.
	 *
	 * @return the qte r
	 */
	public Double getQteR() {
		return qteR;
	}

	/**
	 * Sets the qte r.
	 *
	 * @param qteR
	 *            the new qte r
	 */
	public void setQteR(Double qteR) {
		this.qteR = qteR;
	}

	/**
	 * Gets the unite.
	 *
	 * @return the unite
	 */
	public String getUnite() {
		return unite;
	}

	/**
	 * Sets the unite.
	 *
	 * @param unite
	 *            the new unite
	 */
	public void setUnite(String unite) {
		this.unite = unite;
	}

	/**
	 * Gets the pu.
	 *
	 * @return the pu
	 */
	public Double getPu() {
		return pu;
	}

	/**
	 * Sets the pu.
	 *
	 * @param pu
	 *            the new pu
	 */
	public void setPu(Double pu) {
		this.pu = pu;
	}

	/**
	 * Gets the pmp.
	 *
	 * @return the pmp
	 */
	public Double getPmp() {
		return pmp;
	}

	/**
	 * Sets the pmp.
	 *
	 * @param pmp
	 *            the new pmp
	 */
	public void setPmp(Double pmp) {
		this.pmp = pmp;
	}

	/**
	 * Gets the empl.
	 *
	 * @return the empl
	 */
	public String getEmpl() {
		return empl;
	}

	/**
	 * Sets the empl.
	 *
	 * @param empl
	 *            the new empl
	 */
	public void setEmpl(String empl) {
		this.empl = empl;
	}

	/**
	 * Gets the qte actuel.
	 *
	 * @return the qte actuel
	 */
	public Double getQteActuel() {
		return qteActuel;
	}

	/**
	 * Sets the qte actuel.
	 *
	 * @param qteActuel
	 *            the new qte actuel
	 */
	public void setQteActuel(Double qteActuel) {
		this.qteActuel = qteActuel;
	}

	/**
	 * Gets the entite stock id.
	 *
	 * @return the entite stock id
	 */
	public Long getEntiteStockId() {
		return entiteStockId;
	}

	/**
	 * Sets the entite stock id.
	 *
	 * @param entiteStockId
	 *            the new entite stock id
	 */
	public void setEntiteStockId(Long entiteStockId) {
		this.entiteStockId = entiteStockId;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the cone
	 */
	public Long getCone() {
		return cone;
	}

	/**
	 * @param cone
	 *            the cone to set
	 */
	public void setCone(Long cone) {
		this.cone = cone;
	}

	@Override
	public int compareTo(Object o) {
		EntiteStockMouvementValue element = (EntiteStockMouvementValue) o;
		return (element.getEntiteStockId().compareTo(entiteStockId));
	}

	public String getReferenceLot() {
		return referenceLot;
	}

	public void setReferenceLot(String referenceLot) {
		this.referenceLot = referenceLot;
	}

	public Calendar getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Calendar dateEntree) {
		this.dateEntree = dateEntree;
	}

	public String getCodeFournisseur() {
		return codeFournisseur;
	}

	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}

	public Long getRouleauxActuels() {
		return rouleauxActuels;
	}

	public void setRouleauxActuels(Long rouleauxActuels) {
		this.rouleauxActuels = rouleauxActuels;
	}

	@Override
	public String toString() {
		return "EntiteStockMouvementValue [entiteStockId=" + entiteStockId + ", reference=" + reference
				+ ", designation=" + designation + ", famille=" + famille + ", OA=" + OA + ", PT=" + PT
				+ ", sousFamille=" + sousFamille + ", finCone=" + finCone + ", poid=" + poid + ", cone=" + cone
				+ ", qteOf=" + qteOf + ", qteR=" + qteR + ", unite=" + unite + ", pu=" + pu + ", pmp=" + pmp + ", empl="
				+ empl + ", qteActuel=" + qteActuel + ", nbRouleau=" + nbRouleau + ", referenceLot=" + referenceLot
				+ ", dateEntree=" + dateEntree + ", codeFournisseur=" + codeFournisseur + ", rouleauxActuels="
				+ rouleauxActuels + ", etat=" + etat + "]";
	}

}
