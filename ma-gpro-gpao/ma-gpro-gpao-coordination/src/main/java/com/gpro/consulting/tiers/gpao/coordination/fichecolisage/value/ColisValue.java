package com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value;

import java.util.Calendar;

import com.google.common.collect.ComparisonChain;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationReportValue;

public class ColisValue implements Comparable<Object>{
	
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
	private String produitReference;
	private String produitDesignation;
	private String ordreNumero;
	private Long nombreCartons;
	private String size ;
	private String carton;
	
	private String palette ;
	
	
	private String choix;
	
	
	private Long bonSortie;
	private Calendar dateSortie;
		private String ordrePalette ;
	
	private Boolean fictif;
	
	private Long ancienOrdre;
	
	private Long ordreFiche;
	
	private String refPalette;
	
	
	
	public String getRefPalette() {
		return refPalette;
	}



	public void setRefPalette(String refPalette) {
		this.refPalette = refPalette;
	}



	public int compareTo(ColisValue element) {
		return (ordre.compareTo(element.getOrdre()));
	}
	
	
	
	@Override
	public int compareTo(Object o) {
		  
		ColisValue m=(ColisValue)o;
			
		return ComparisonChain.start()
		    		
		 	        .compare(this.getOrdre(),m.getOrdre())
		 	        .compare(this.getId(),m.getId())
		        .result();
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public String getQuantiteDesignation() {
		return quantiteDesignation;
	}

	public void setQuantiteDesignation(String quantiteDesignation) {
		this.quantiteDesignation = quantiteDesignation;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public Long getBonSortie() {
		return bonSortie;
	}

	public void setBonSortie(Long bonSortie) {
		this.bonSortie = bonSortie;
	}

	public Calendar getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}

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



	public Long getAncienOrdre() {
		return ancienOrdre;
	}



	public void setAncienOrdre(Long ancienOrdre) {
		this.ancienOrdre = ancienOrdre;
	}



	public Long getOrdreFiche() {
		return ordreFiche;
	}



	public void setOrdreFiche(Long ordreFiche) {
		this.ordreFiche = ordreFiche;
	}

	
	
	
	
	
}
