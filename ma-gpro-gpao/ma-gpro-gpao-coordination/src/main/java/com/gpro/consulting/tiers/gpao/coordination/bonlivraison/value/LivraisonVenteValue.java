package com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value;

import java.util.Calendar;
import java.util.List;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class LivraisonVenteValue implements Comparable<LivraisonVenteValue>{
	
	private Long id;
	private String reference;
	private Calendar date;
	private Double montantHTaxe;
	private Double montantTTC;
	private Double montantTaxe;
	private Double montantRemise;
	private String observations;
	private String infoSortie;
	private Long partieIntId;
	private String partieIntAbreviation;
	private Double metrageTotal;
	private String etat;
	private String transporteur;
	private Boolean blSuppression;
	private Calendar dateSuppression;
	private Calendar dateCreation;
	private Calendar dateModification;
	private String version;
	
	private String natureLivraison;
	
	
	
	private Double poidsNet;
		
		
	
	private Double poidsBrut;
		
		
	
	private Long totalColis;
		
		
	
	private Long  totalPalette;
	
	
	
	private String shipped ;
	
	
	
	private Long totalColisReel;
	
	
	
	
	
	
	
	
	
	
	private List<DetLivraisonVenteValue> listDetLivraisonVente;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Double getMontantHTaxe() {
		return montantHTaxe;
	}
	public void setMontantHTaxe(Double montantHTaxe) {
		this.montantHTaxe = montantHTaxe;
	}
	public Double getMontantTTC() {
		return montantTTC;
	}
	public void setMontantTTC(Double montantTTC) {
		this.montantTTC = montantTTC;
	}
	public Double getMontantTaxe() {
		return montantTaxe;
	}
	public void setMontantTaxe(Double montantTaxe) {
		this.montantTaxe = montantTaxe;
	}
	public Double getMontantRemise() {
		return montantRemise;
	}
	public void setMontantRemise(Double montantRemise) {
		this.montantRemise = montantRemise;
	}
	public String getObservations() {
		return observations;
	}
	public void setObservations(String observations) {
		this.observations = observations;
	}
	public String getInfoSortie() {
		return infoSortie;
	}
	public void setInfoSortie(String infoSortie) {
		this.infoSortie = infoSortie;
	}
	public Long getPartieIntId() {
		return partieIntId;
	}
	public void setPartieIntId(Long partieIntId) {
		this.partieIntId = partieIntId;
	}
	public String getPartieIntAbreviation() {
		return partieIntAbreviation;
	}
	public void setPartieIntAbreviation(String partieIntAbreviation) {
		this.partieIntAbreviation = partieIntAbreviation;
	}
	public Double getMetrageTotal() {
		return metrageTotal;
	}
	public void setMetrageTotal(Double metrageTotal) {
		this.metrageTotal = metrageTotal;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getTransporteur() {
		return transporteur;
	}
	public void setTransporteur(String transporteur) {
		this.transporteur = transporteur;
	}
	public Boolean getBlSuppression() {
		return blSuppression;
	}
	public void setBlSuppression(Boolean blSuppression) {
		this.blSuppression = blSuppression;
	}
	public Calendar getDateSuppression() {
		return dateSuppression;
	}
	public void setDateSuppression(Calendar dateSuppression) {
		this.dateSuppression = dateSuppression;
	}
	public Calendar getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Calendar dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Calendar getDateModification() {
		return dateModification;
	}
	public void setDateModification(Calendar dateModification) {
		this.dateModification = dateModification;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<DetLivraisonVenteValue> getListDetLivraisonVente() {
		return listDetLivraisonVente;
	}
	public void setListDetLivraisonVente(
			List<DetLivraisonVenteValue> listDetLivraisonVente) {
		this.listDetLivraisonVente = listDetLivraisonVente;
	}

	
	
	
	
	public String getNatureLivraison() {
		return natureLivraison;
	}
	public void setNatureLivraison(String natureLivraison) {
		this.natureLivraison = natureLivraison;
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
	public Long getTotalColis() {
		return totalColis;
	}
	public void setTotalColis(Long totalColis) {
		this.totalColis = totalColis;
	}
	public Long getTotalPalette() {
		return totalPalette;
	}
	public void setTotalPalette(Long totalPalette) {
		this.totalPalette = totalPalette;
	}
	@Override
	public String toString() {
		return "LivraisonVenteValue [id=" + id + ", reference=" + reference
				+ ", date=" + date + ", montantHTaxe=" + montantHTaxe
				+ ", montantTTC=" + montantTTC + ", montantTaxe=" + montantTaxe
				+ ", montantRemise=" + montantRemise + ", observations="
				+ observations + ", infoSortie=" + infoSortie
				+ ", partieIntId=" + partieIntId + ", partieIntAbreviation="
				+ partieIntAbreviation + ", metrageTotal=" + metrageTotal
				+ ", etat=" + etat + ", transporteur=" + transporteur
				+ ", blSuppression=" + blSuppression + ", dateSuppression="
				+ dateSuppression + ", dateCreation=" + dateCreation
				+ ", dateModification=" + dateModification + ", version="
				+ version + ", listDetLivraisonVente=" + listDetLivraisonVente
				+ "]";
	}
	@Override
	public int compareTo(LivraisonVenteValue element) {
		return (element.getReference().compareTo(reference));
	}
	public String getShipped() {
		return shipped;
	}
	public void setShipped(String shipped) {
		this.shipped = shipped;
	}
	public Long getTotalColisReel() {
		return totalColisReel;
	}
	public void setTotalColisReel(Long totalColisReel) {
		this.totalColisReel = totalColisReel;
	}
	
	
	
		
}
