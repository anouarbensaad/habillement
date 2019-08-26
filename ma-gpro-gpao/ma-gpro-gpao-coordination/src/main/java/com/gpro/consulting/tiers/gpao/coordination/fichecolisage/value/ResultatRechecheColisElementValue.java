package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;


/**
 * @author Ghazi Atroussi
 * @since 24/11/2018
 *
 */
public class ResultatRechecheColisElementValue implements Comparable<ResultatRechecheColisElementValue>{	
	
	private Long id;
	private Long num;
	private Long couleurId;
	private Long tailleId;
	private Long quantite;
	private Long ordre;	
	private String quantiteDesignation;
	private Long ficheColisageId;
	private String tailleDesignation;
	private String couleurDesignation;
	private Double poidsNet ;
	private Double poidsBrut ; 
	private String ordreNumero;
	private Long nombreCartons;
	private String size ;
	private String carton;
	private String palette ;
	private Long ordreFabricationId;
	private String produitReference;
	private String produitDesignation;
	private String clientAbreviation;
	private String clientReference;
	private String semaine;

	private Long bonSortie;
	
	private Long ordreFiche;
	
	
	private Boolean fictif;
	
	private String ordrePalette ;
	
	
	
	public String getOrdrePalette() {
		return ordrePalette;
	}



	public void setOrdrePalette(String ordrePalette) {
		this.ordrePalette = ordrePalette;
	}



	public Boolean getFictif() {
		return fictif;
	}



	public void setFictif(Boolean fictif) {
		this.fictif = fictif;
	}



	public int compareTo(ResultatRechecheColisElementValue element) {

		return (element.getId().compareTo(id));
	}
	

	
	public Long getOrdreFiche() {
		return ordreFiche;
	}



	public void setOrdreFiche(Long ordreFiche) {
		this.ordreFiche = ordreFiche;
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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


	public String getSemaine() {
		return semaine;
	}

	public void setSemaine(String semaine) {
		this.semaine = semaine;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
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

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
	}

	public String getQuantiteDesignation() {
		return quantiteDesignation;
	}

	public void setQuantiteDesignation(String quantiteDesignation) {
		this.quantiteDesignation = quantiteDesignation;
	}

	public Long getFicheColisageId() {
		return ficheColisageId;
	}

	public void setFicheColisageId(Long ficheColisageId) {
		this.ficheColisageId = ficheColisageId;
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

	public Double getPoidsNet() {
		return poidsNet;
	}

	public void setPoidsNet(Double poidsNet) {
		this.poidsNet = poidsNet;
	}

	public Double getPoidsBrut() {
		return poidsBrut;
	}

	public void setPoidsBrut(Double poidsBrut) {
		this.poidsBrut = poidsBrut;
	}

	public String getOrdreNumero() {
		return ordreNumero;
	}

	public void setOrdreNumero(String ordreNumero) {
		this.ordreNumero = ordreNumero;
	}

	public Long getNombreCartons() {
		return nombreCartons;
	}

	public void setNombreCartons(Long nombreCartons) {
		this.nombreCartons = nombreCartons;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCarton() {
		return carton;
	}

	public void setCarton(String carton) {
		this.carton = carton;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public Long getBonSortie() {
		return bonSortie;
	}

	public void setBonSortie(Long bonSortie) {
		this.bonSortie = bonSortie;
	}
	
	
}
