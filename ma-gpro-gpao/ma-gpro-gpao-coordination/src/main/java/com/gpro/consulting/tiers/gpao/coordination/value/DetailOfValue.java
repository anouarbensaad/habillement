/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.value;



/**
 * @author $Ameni
 *
 */
public class DetailOfValue  implements Comparable<DetailOfValue>{

	private Long id;

	/** The quantite. */
	private Long quantite;

	/** The eb_taille_id. */
	private Long tailleId;

	/** The eb_couleur_id. */
	private Long couleurId;

	/** The stock qte. */
	private Long qteStock;
	
	private Long ordre;
	
	private String tailleDesignation;
	
	private String couleurDesignation;
	
	private String numOF;
	
	private String designationOF;
	
	private String referenceProduit;
	
	
	/********* Getter & Setter *********/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the tailleId
	 */
	public Long getTailleId() {
		return tailleId;
	}

	/**
	 * @param tailleId the tailleId to set
	 */
	public void setTailleId(Long tailleId) {
		this.tailleId = tailleId;
	}

	/**
	 * @return the couleurId
	 */
	public Long getCouleurId() {
		return couleurId;
	}

	/**
	 * @param couleurId the couleurId to set
	 */
	public void setCouleurId(Long couleurId) {
		this.couleurId = couleurId;
	}
	public int compareTo(DetailOfValue element) {
		return (ordre.compareTo(element.getOrdre()));
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public Long getQteStock() {
		return qteStock;
	}

	public void setQteStock(Long qteStock) {
		this.qteStock = qteStock;
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

	public String getNumOF() {
		return numOF;
	}

	public void setNumOF(String numOF) {
		this.numOF = numOF;
	}

	public String getDesignationOF() {
		return designationOF;
	}

	public void setDesignationOF(String designationOF) {
		this.designationOF = designationOF;
	}

	public String getReferenceProduit() {
		return referenceProduit;
	}

	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}
	
	
	
}
