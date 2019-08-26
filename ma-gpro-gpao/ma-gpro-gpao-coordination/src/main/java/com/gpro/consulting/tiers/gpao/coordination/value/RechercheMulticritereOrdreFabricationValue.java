package com.gpro.consulting.tiers.gpao.coordination.value;

import java.util.Calendar;

/**
 * @author $Ameni
 *
 */
public class RechercheMulticritereOrdreFabricationValue {

	private String vNumero;
	private String  vCompositionBC;
	private String vCompositionClient;
	private String vEtat;
	private Calendar vDateIntroductionDu;
	private Calendar vDateIntroductionAu;
	private Long clientId;
	private Long produitId;
	private Calendar dateLivraisonDu;
	private Calendar dateLivraisonTo;
	private boolean isOptimized;
	
	private Long statut;
	
	private String etatCoupe;
	private String etatFabrication;
	private String etatConditionnement;
	private String etatCollisage;
	private String etatExpedition;
	private String etatEngagement;
	
	
	
	public String getvNumero() {
		return vNumero;
	}
	public void setvNumero(String vNumero) {
		this.vNumero = vNumero;
	}
	public String getvCompositionBC() {
		return vCompositionBC;
	}
	public void setvCompositionBC(String vCompositionBC) {
		this.vCompositionBC = vCompositionBC;
	}
	public String getvCompositionClient() {
		return vCompositionClient;
	}
	public void setvCompositionClient(String vCompositionClient) {
		this.vCompositionClient = vCompositionClient;
	}
	public String getvEtat() {
		return vEtat;
	}
	public void setvEtat(String vEtat) {
		this.vEtat = vEtat;
	}
	public Calendar getvDateIntroductionDu() {
		return vDateIntroductionDu;
	}
	public void setvDateIntroductionDu(Calendar vDateIntroductionDu) {
		this.vDateIntroductionDu = vDateIntroductionDu;
	}
	public Calendar getvDateIntroductionAu() {
		return vDateIntroductionAu;
	}
	public void setvDateIntroductionAu(Calendar vDateIntroductionAu) {
		this.vDateIntroductionAu = vDateIntroductionAu;
	}
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Calendar getDateLivraisonDu() {
		return dateLivraisonDu;
	}
	public void setDateLivraisonDu(Calendar dateLivraisonDu) {
		this.dateLivraisonDu = dateLivraisonDu;
	}
	public Calendar getDateLivraisonTo() {
		return dateLivraisonTo;
	}
	public void setDateLivraisonTo(Calendar dateLivraisonTo) {
		this.dateLivraisonTo = dateLivraisonTo;
	}
	
	public boolean isOptimized() {
		return isOptimized;
	}
	public void setOptimized(boolean isOptimized) {
		this.isOptimized = isOptimized;
	}
	
	
	
	
	
	public String getEtatCoupe() {
		return etatCoupe;
	}
	public void setEtatCoupe(String etatCoupe) {
		this.etatCoupe = etatCoupe;
	}
	public String getEtatFabrication() {
		return etatFabrication;
	}
	public void setEtatFabrication(String etatFabrication) {
		this.etatFabrication = etatFabrication;
	}
	public String getEtatConditionnement() {
		return etatConditionnement;
	}
	public void setEtatConditionnement(String etatConditionnement) {
		this.etatConditionnement = etatConditionnement;
	}
	public String getEtatCollisage() {
		return etatCollisage;
	}
	public void setEtatCollisage(String etatCollisage) {
		this.etatCollisage = etatCollisage;
	}
	public String getEtatExpedition() {
		return etatExpedition;
	}
	public void setEtatExpedition(String etatExpedition) {
		this.etatExpedition = etatExpedition;
	}
	public String getEtatEngagement() {
		return etatEngagement;
	}
	public void setEtatEngagement(String etatEngagement) {
		this.etatEngagement = etatEngagement;
	}
	@Override
	public String toString() {
		return "RechercheMulticritereOrdreFabricationValue [vNumero=" + vNumero + ", vCompositionBC=" + vCompositionBC
				+ ", vCompositionClient=" + vCompositionClient + ", vEtat=" + vEtat + ", vDateIntroductionDu="
				+ vDateIntroductionDu + ", vDateIntroductionAu=" + vDateIntroductionAu + ", clientId=" + clientId
				+ ", produitId=" + produitId + ", dateLivraisonDu=" + dateLivraisonDu + ", dateLivraisonTo="
				+ dateLivraisonTo + "]";
	}
	public Long getStatut() {
		return statut;
	}
	public void setStatut(Long statut) {
		this.statut = statut;
	}

}
