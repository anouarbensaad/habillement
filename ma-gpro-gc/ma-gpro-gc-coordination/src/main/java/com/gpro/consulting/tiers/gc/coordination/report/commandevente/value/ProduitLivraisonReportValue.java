package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Ameni Berrich
 *
 */
public class ProduitLivraisonReportValue {

	private Double prixUnitaire;
	private Double prixTotal;
	private Long quantiteTotal;
	private Calendar dateLivraison;
	private String produitDesignation;

	private String tailleD1;
	private String tailleD2;
	private String tailleD3;
	private String tailleD4;
	private String tailleD5;
	private String tailleD6;

	// Produits Commande (pr Leo Minor)
	private String designationP;
	private String sousFamilleP;
	private Long quantiteP;

	private List<DetailsProduitLivraisonReportValue> listDetailsProduitLivraison = new ArrayList<DetailsProduitLivraisonReportValue>();

	private String sousFamilleDesignation;

	public Double getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(Double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Long getQuantiteTotal() {
		return quantiteTotal;
	}

	public void setQuantiteTotal(Long quantiteTotal) {
		this.quantiteTotal = quantiteTotal;
	}

	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	public String getProduitDesignation() {
		return produitDesignation;
	}

	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}

	public String getTailleD1() {
		return tailleD1;
	}

	public void setTailleD1(String tailleD1) {
		this.tailleD1 = tailleD1;
	}

	public String getTailleD2() {
		return tailleD2;
	}

	public void setTailleD2(String tailleD2) {
		this.tailleD2 = tailleD2;
	}

	public String getTailleD3() {
		return tailleD3;
	}

	public void setTailleD3(String tailleD3) {
		this.tailleD3 = tailleD3;
	}

	public String getTailleD4() {
		return tailleD4;
	}

	public void setTailleD4(String tailleD4) {
		this.tailleD4 = tailleD4;
	}

	public String getTailleD5() {
		return tailleD5;
	}

	public void setTailleD5(String tailleD5) {
		this.tailleD5 = tailleD5;
	}

	public String getTailleD6() {
		return tailleD6;
	}

	public void setTailleD6(String tailleD6) {
		this.tailleD6 = tailleD6;
	}

	public List<DetailsProduitLivraisonReportValue> getListDetailsProduitLivraison() {
		return listDetailsProduitLivraison;
	}

	public void setListDetailsProduitLivraison(List<DetailsProduitLivraisonReportValue> listDetailsProduitLivraison) {
		this.listDetailsProduitLivraison = listDetailsProduitLivraison;
	}

	public String getSousFamilleDesignation() {
		return sousFamilleDesignation;
	}

	public void setSousFamilleDesignation(String sousFamilleDesignation) {
		this.sousFamilleDesignation = sousFamilleDesignation;
	}

	public String getDesignationP() {
		return designationP;
	}

	public void setDesignationP(String designationP) {
		this.designationP = designationP;
	}

	public String getSousFamilleP() {
		return sousFamilleP;
	}

	public void setSousFamilleP(String sousFamilleP) {
		this.sousFamilleP = sousFamilleP;
	}

	public Long getQuantiteP() {
		return quantiteP;
	}

	public void setQuantiteP(Long quantiteP) {
		this.quantiteP = quantiteP;
	}

}
