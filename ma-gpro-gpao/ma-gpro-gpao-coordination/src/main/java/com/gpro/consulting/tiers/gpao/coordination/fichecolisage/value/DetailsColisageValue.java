package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;

public class DetailsColisageValue implements Comparable<DetailsColisageValue> {
	
	private Long id;
	private Long couleurId;
	private Long tailleId;
	private Long quantite;
	private Long quantiteRestante;
	private Long pcb ;
	private Long ficheColisageId;
	private String tailleDesignation;
	private String couleurDesignation;
	private Double poidsNet ;
	private Double poidsBrut ; 
	private String produitReference;
	private String produitDesignation;
	private String ordreNumero;
	
	
	public int compareTo(DetailsColisageValue element) {
		return (id.compareTo(element.getId()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public Long getQuantiteRestante() {
		return quantiteRestante;
	}

	public void setQuantiteRestante(Long quantiteRestante) {
		this.quantiteRestante = quantiteRestante;
	}

	public Long getFicheColisageId() {
		return ficheColisageId;
	}

	public void setFicheColisageId(Long ficheColisageId) {
		this.ficheColisageId = ficheColisageId;
	}

	public Long getPcb() {
		return pcb;
	}

	public void setPcb(Long pcb) {
		this.pcb = pcb;
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

	public String getOrdreNumero() {
		return ordreNumero;
	}

	public void setOrdreNumero(String ordreNumero) {
		this.ordreNumero = ordreNumero;
	}
	
}
