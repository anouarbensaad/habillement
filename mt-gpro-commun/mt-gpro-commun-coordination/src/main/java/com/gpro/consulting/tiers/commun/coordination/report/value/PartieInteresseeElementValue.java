/**
 * 
 */
package com.gpro.consulting.tiers.commun.coordination.report.value;

/**
 * @author Ameni Berrich
 * @since 23/05/2016
 */
public class PartieInteresseeElementValue {
	
	/** The ref. */
	private String reference;
	/** The raison sociale. */
	private String raisonSociale;
	/** The famille . */
	private Long famillePartieInteressee;
	/** The categorie . */
	private Long categoriePartieInteressee;
	/** The type . */
	private Long typePartieInteressee;
	/** The telephone. */
	private String telephone;
	
	//conversion Id / Designation
	private String famillePIDesignation;
	private String categoriePIDesignation;
	private String typePIDesignation;
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getRaisonSociale() {
		return raisonSociale;
	}
	public void setRaisonSociale(String raisonSociale) {
		this.raisonSociale = raisonSociale;
	}
	public Long getFamillePartieInteressee() {
		return famillePartieInteressee;
	}
	public void setFamillePartieInteressee(Long famillePartieInteressee) {
		this.famillePartieInteressee = famillePartieInteressee;
	}
	public Long getCategoriePartieInteressee() {
		return categoriePartieInteressee;
	}
	public void setCategoriePartieInteressee(Long categoriePartieInteressee) {
		this.categoriePartieInteressee = categoriePartieInteressee;
	}
	public Long getTypePartieInteressee() {
		return typePartieInteressee;
	}
	public void setTypePartieInteressee(Long typePartieInteressee) {
		this.typePartieInteressee = typePartieInteressee;
	}
	public String getFamillePIDesignation() {
		return famillePIDesignation;
	}
	public void setFamillePIDesignation(String famillePIDesignation) {
		this.famillePIDesignation = famillePIDesignation;
	}
	public String getCategoriePIDesignation() {
		return categoriePIDesignation;
	}
	public void setCategoriePIDesignation(String categoriePIDesignation) {
		this.categoriePIDesignation = categoriePIDesignation;
	}
	public String getTypePIDesignation() {
		return typePIDesignation;
	}
	public void setTypePIDesignation(String typePIDesignation) {
		this.typePIDesignation = typePIDesignation;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
}
