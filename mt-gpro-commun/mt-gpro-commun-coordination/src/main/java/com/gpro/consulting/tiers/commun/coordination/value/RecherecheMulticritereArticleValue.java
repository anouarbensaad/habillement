package com.gpro.consulting.tiers.commun.coordination.value;

public class RecherecheMulticritereArticleValue {
	/** The ref. */
	private String ref;
	/** The designation. */
	private String designation ;
	/** The type. */
	private Long typeEntite;
	/** The sous famille. */
	private Long sousFamilleEntite;
	/** The famille. */
	private Long familleEntite;
	/** The site. */
	private Long site;
	/** The prix inf. */
	private Double prix_inf;
	/** The prix sup. */
	private Double prix_sup;
	/** ID Magasin. */
	private Long idMAgasin;
	
	private boolean isOptimized;

	/**
	 * @return the ref
	 */
	public String getRef() {
		return ref;
	}
	/**
	 * @param ref the ref to set
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}
	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public Long getTypeEntite() {
		return typeEntite;
	}
	public void setTypeEntite(Long typeEntite) {
		this.typeEntite = typeEntite;
	}
	public Long getSousFamilleEntite() {
		return sousFamilleEntite;
	}
	public void setSousFamilleEntite(Long sousFamilleEntite) {
		this.sousFamilleEntite = sousFamilleEntite;
	}
	public Long getFamilleEntite() {
		return familleEntite;
	}
	public void setFamilleEntite(Long familleEntite) {
		this.familleEntite = familleEntite;
	}
	public Long getSite() {
		return site;
	}
	public void setSite(Long site) {
		this.site = site;
	}
	/**
	 * @return the prix_inf
	 */
	public Double getPrix_inf() {
		return prix_inf;
	}
	/**
	 * @param prix_inf the prix_inf to set
	 */
	public void setPrix_inf(Double prix_inf) {
		this.prix_inf = prix_inf;
	}
	/**
	 * @return the prix_sup
	 */
	public Double getPrix_sup() {
		return prix_sup;
	}
	/**
	 * @param prix_sup the prix_sup to set
	 */
	public void setPrix_sup(Double prix_sup) {
		this.prix_sup = prix_sup;
	}
	public Long getIdMAgasin() {
		return idMAgasin;
	}
	public void setIdMAgasin(Long idMAgasin) {
		this.idMAgasin = idMAgasin;
	}
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	
}
