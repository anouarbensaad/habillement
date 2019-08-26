package com.gpro.consulting.tiers.gpao.coordination.value;

/**
 * @author $Ameni
 *
 */
public class CompositionOfValue {

	private Long id;

	/** The quantite. */
	private Long commandeId;

	/** The quantite. */
	private Long quantite;
	
	//bi-directional many-to-one association to GpOrdreFabrication
	/** The OrdreFabrication. */
	private OrdreFabricationValue ordre;
	
	/**** Attributs ajoutés pour recuperation des données des vues ***/
	
	 private String designationProduit;
	 private String referenceProduit;
	 private String sousfamilleProduitDesignation;
	 private Long sousFamilleProduit;

	 private Long idProduit;
	 
	 private Long QuantiteBC;
	 private String numeroBC;
	 private Long typeBC;
	 
	/******** Getter & Setter *********/
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
	 * @return the commandeId
	 */
	public Long getCommandeId() {
		return commandeId;
	}

	/**
	 * @param commandeId the commandeId to set
	 */
	public void setCommandeId(Long commandeId) {
		this.commandeId = commandeId;
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
	 * @return the ordre
	 */
	public OrdreFabricationValue getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(OrdreFabricationValue ordre) {
		this.ordre = ordre;
	}

	/**
	 * @return the designationProduit
	 */
	public String getDesignationProduit() {
		return designationProduit;
	}

	/**
	 * @param designationProduit the designationProduit to set
	 */
	public void setDesignationProduit(String designationProduit) {
		this.designationProduit = designationProduit;
	}

	/**
	 * @return the referenceProduit
	 */
	public String getReferenceProduit() {
		return referenceProduit;
	}

	/**
	 * @param referenceProduit the referenceProduit to set
	 */
	public void setReferenceProduit(String referenceProduit) {
		this.referenceProduit = referenceProduit;
	}

	/**
	 * @return the sousfamilleProduitDesignation
	 */
	public String getSousfamilleProduitDesignation() {
		return sousfamilleProduitDesignation;
	}

	/**
	 * @param sousfamilleProduitDesignation the sousfamilleProduitDesignation to set
	 */
	public void setSousfamilleProduitDesignation(
			String sousfamilleProduitDesignation) {
		this.sousfamilleProduitDesignation = sousfamilleProduitDesignation;
	}

	/**
	 * @return the sousFamilleProduit
	 */
	public Long getSousFamilleProduit() {
		return sousFamilleProduit;
	}

	/**
	 * @param sousFamilleProduit the sousFamilleProduit to set
	 */
	public void setSousFamilleProduit(Long sousFamilleProduit) {
		this.sousFamilleProduit = sousFamilleProduit;
	}

	/**
	 * @return the idProduit
	 */
	public Long getIdProduit() {
		return idProduit;
	}

	/**
	 * @param idProduit the idProduit to set
	 */
	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	/**
	 * @return the quantiteBC
	 */
	public Long getQuantiteBC() {
		return QuantiteBC;
	}

	/**
	 * @param quantiteBC the quantiteBC to set
	 */
	public void setQuantiteBC(Long quantiteBC) {
		QuantiteBC = quantiteBC;
	}

	/**
	 * @return the numeroBC
	 */
	public String getNumeroBC() {
		return numeroBC;
	}

	/**
	 * @param numeroBC the numeroBC to set
	 */
	public void setNumeroBC(String numeroBC) {
		this.numeroBC = numeroBC;
	}

	/**
	 * @return the typeBC
	 */
	public Long getTypeBC() {
		return typeBC;
	}

	/**
	 * @param typeBC the typeBC to set
	 */
	public void setTypeBC(Long typeBC) {
		this.typeBC = typeBC;
	}
	
}
