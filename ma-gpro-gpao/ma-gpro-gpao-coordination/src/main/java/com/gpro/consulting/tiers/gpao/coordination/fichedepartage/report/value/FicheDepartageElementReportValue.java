package com.gpro.consulting.tiers.gpao.coordination.fichedepartage.report.value;


/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */
public class FicheDepartageElementReportValue implements Comparable<FicheDepartageElementReportValue>{

	private Long paquetId;
	private Long numPaquet;
	private String couleurDesignation;
	private String tailleDesignation;
	private Long quantite;
	
	public int compareTo(FicheDepartageElementReportValue element) {
		
		return (numPaquet.compareTo(element.getNumPaquet()));
	}

	public Long getPaquetId() {
		return paquetId;
	}

	public void setPaquetId(Long paquetId) {
		this.paquetId = paquetId;
	}

	public Long getNumPaquet() {
		return numPaquet;
	}

	public void setNumPaquet(Long numPaquet) {
		this.numPaquet = numPaquet;
	}

	public String getCouleurDesignation() {
		return couleurDesignation;
	}

	public void setCouleurDesignation(String couleurDesignation) {
		this.couleurDesignation = couleurDesignation;
	}

	public String getTailleDesignation() {
		return tailleDesignation;
	}

	public void setTailleDesignation(String tailleDesignation) {
		this.tailleDesignation = tailleDesignation;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}
	
	
}
