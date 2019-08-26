package com.gpro.consulting.tiers.commun.coordination.value;

import java.util.Set;


// TODO: Auto-generated Javadoc
/**
 * The Class FamilleProduitValue.
 *@author med
 */
public class FamilleProduitValue   {
	
	/** The id. */
	private Long id;
	
	/** The designation. */
	private String designation;
	
	/** List Sous Famille **/
	private Set<SousFamilleProduitValue> sousFamille;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the designation.
	 *
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * Sets the designation.
	 *
	 * @param designation the new designation
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Set<SousFamilleProduitValue> getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(Set<SousFamilleProduitValue> sousFamille) {
		this.sousFamille = sousFamille;
	}

	

}