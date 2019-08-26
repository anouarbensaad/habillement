package com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value;


/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class ResultatRechecheFicheEclatementElementValue implements Comparable<ResultatRechecheFicheEclatementElementValue>{

	private Long id;
	
	private Long nombrePaquet;
	private Long quantiteEclate;
	
	private Long ordreFabricationId;
	private String produitReference;
	private String produitDesignation;
	private String ordreFabricationNumero;
	
	private String clientAbreviation;
	private String clientReference;
	
	public int compareTo(ResultatRechecheFicheEclatementElementValue element) {

		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getNombrePaquet() {
		return nombrePaquet;
	}

	public void setNombrePaquet(Long nombrePaquet) {
		this.nombrePaquet = nombrePaquet;
	}

	public Long getQuantiteEclate() {
		return quantiteEclate;
	}

	public void setQuantiteEclate(Long quantiteEclate) {
		this.quantiteEclate = quantiteEclate;
	}

	public Long getOrdreFabricationId() {
		return ordreFabricationId;
	}

	public void setOrdreFabricationId(Long ordreFabricationId) {
		this.ordreFabricationId = ordreFabricationId;
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

	public String getOrdreFabricationNumero() {
		return ordreFabricationNumero;
	}

	public void setOrdreFabricationNumero(String ordreFabricationNumero) {
		this.ordreFabricationNumero = ordreFabricationNumero;
	}

	public String getClientAbreviation() {
		return clientAbreviation;
	}
	public void setClientAbreviation(String clientAbreviation) {
		this.clientAbreviation = clientAbreviation;
	}
	
	public String getClientReference() {
		return clientReference;
	}
	
	public void setClientReference(String clientReference) {
		this.clientReference = clientReference;
	}
	
	
}
