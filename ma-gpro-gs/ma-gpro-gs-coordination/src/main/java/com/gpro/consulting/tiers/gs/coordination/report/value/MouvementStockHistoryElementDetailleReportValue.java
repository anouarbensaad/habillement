package com.gpro.consulting.tiers.gs.coordination.report.value;

public class MouvementStockHistoryElementDetailleReportValue extends MouvementStockHistoryElementReportValue implements Comparable<Object>{
	

	private String sousFamille;
	private String couleurArticle;
	private String refLot;
	private String producteur;
	private String codeFournisseur;
	
	//Hajer Amri 22/03/2017
	private Long finconesReel;
	private Long cones;

	public String getSousFamille() {
		return sousFamille;
	}
	public void setSousFamille(String sousFamille) {
		this.sousFamille = sousFamille;
	}
	public String getCouleurArticle() {
		return couleurArticle;
	}
	public void setCouleurArticle(String couleurArticle) {
		this.couleurArticle = couleurArticle;
	}
	public String getRefLot() {
		return refLot;
	}
	public void setRefLot(String refLot) {
		this.refLot = refLot;
	}
	public String getProducteur() {
		return producteur;
	}
	public void setProducteur(String producteur) {
		this.producteur = producteur;
	}
	public String getCodeFournisseur() {
		return codeFournisseur;
	}
	public void setCodeFournisseur(String codeFournisseur) {
		this.codeFournisseur = codeFournisseur;
	}
	public Long getFinconesReel() {
		return finconesReel;
	}
	public void setFinconesReel(Long finconesReel) {
		this.finconesReel = finconesReel;
	}
	public Long getCones() {
		return cones;
	}
	public void setCones(Long cones) {
		this.cones = cones;
	}
	
}

