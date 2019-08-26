package com.gpro.consulting.tiers.gpao.coordination.gammeof.value;

/**
 * @author Ameni Berrich
 *
 */
public class DetailTailleCouleurAvancementOFValue {

	private Long quantite;
	private Long couleurId;
	private Long tailleId;
	
	//Conversion Id/Designation
	private String tailleDesignation;
	private String couleurDesignation;
	
	public Long getQuantite() {
		return quantite;
	}
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	public Long getCouleurId() {
		return couleurId;
	}
	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
	}
	public Long getTailleId() {
		return tailleId;
	}
	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
	}
	public String getTailleDesignation() {
		return tailleDesignation;
	}
	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}
	public String getCouleurDesignation() {
		return couleurDesignation;
	}
	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}
	
}
