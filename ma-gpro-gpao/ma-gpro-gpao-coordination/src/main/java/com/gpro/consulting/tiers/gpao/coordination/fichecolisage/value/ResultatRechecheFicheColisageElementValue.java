package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;


/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public class ResultatRechecheFicheColisageElementValue implements Comparable<ResultatRechecheFicheColisageElementValue>{

	private Long id;
	
	private Long nombreColis;
	private Long quantiteColis;
	private Long quaniteTotale ;
	private Long ordreFabricationId;
	private String produitReference;
	private String produitDesignation;
	private String ordreFabricationNumero;
	private String couleur;
	private String clientAbreviation;
	private String clientReference;
	private String semaine;
	public int compareTo(ResultatRechecheFicheColisageElementValue element) {

		return (element.getId().compareTo(id));
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}


	public Long getNombreColis() {
		return nombreColis;
	}

	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	public Long getQuantiteColis() {
		return quantiteColis;
	}

	public void setQuantiteColis(Long quantiteColis) {
		this.quantiteColis = quantiteColis;
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

	public Long getQuaniteTotale() {
		return quaniteTotale;
	}

	public void setQuaniteTotale(Long quaniteTotale) {
		this.quaniteTotale = quaniteTotale;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getSemaine() {
		return semaine;
	}

	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}
	
	
}
