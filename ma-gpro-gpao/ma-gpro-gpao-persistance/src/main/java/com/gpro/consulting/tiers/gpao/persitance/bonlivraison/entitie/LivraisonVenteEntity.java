package com.gpro.consulting.tiers.gpao.persitance.bonlivraison.entitie;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstanteCommerciale;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Entity
@Table(name=IConstanteCommerciale.TABLE_GC_LIVRAISONVENTE)
public class LivraisonVenteEntity implements Serializable{

	private static final long serialVersionUID = 7019588587457251275L;

	@Id
	@SequenceGenerator(name="LIVRAISONVENTE_ID_GENERATOR", sequenceName=IConstanteCommerciale.SEQUENCE_GC_CLV_SEQ, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="LIVRAISONVENTE_ID_GENERATOR")
    private Long id;
	
	@Column(name="REFERENCE")
	private String reference;
	
	@Column(name="DATE")
	private Calendar date;
	
	@Column(name="MONTANTHTAXE")
	private Double montantHTaxe;
	
	@Column(name="MONTANTTTC")
	private Double montantTTC;
			
	@Column(name="MONTANT_TAXE")
	private Double montantTaxe;
					
	@Column(name="MONTANT_REMISE")
	private Double montantRemise;
	
	@Column(name="OBSERVATIONS")
	private String observations;
											
	@Column(name="INFO_SORTIE")
	private String infoSortie;//list des ref bonSorties | patterne: ref1-ref2-ref3 ....
													
	@Column(name="PI_PARTIEINT_ID")
	private Long partieIntId;
	
	@Column(name="METRAGE_TOTAL")
	private Double metrageTotal;
	
	@Column(name="ETAT")
	private String etat;
	
	@Column(name="TRANSPORTEUR")
	private String transporteur;
	
	@OneToMany(mappedBy = "livraisonVente", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DetLivraisonVenteEntity> listDetLivraisonVente;
	
	
															
	@Column(name="BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name="DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name="DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name="DATE_MODIFICATION")
	private Calendar dateModification;
																							
	@Column(name="VERSION")
	private String version;
	
	//added on 18/02/2016, by Wahid Gazzah
	@Column(name="MODIFIER")
	private Boolean modifier;
	
	//added on 19/02/2016, by Wahid Gazzah
	@Column(name="GC_MODEPAIEMENT_ID")
	private Long modepaiementId;
	
	@Column(name="GC_MARCHE_ID")
	private Long marcheId;
	
	//added on 05/10/2016, by Zeineb Medimagh
	@Column(name="NATURE_LIVRAISON")
	private String natureLivraison;
		
	
	
	@Column(name="poids_net")
	private Double poidsNet;
		
		
	@Column(name="poids_brut")
	private Double poidsBrut;
		
		
	@Column(name="total_colis")
	private Long totalColis;
		
		
	@Column(name="total_palette")
	private Long  totalPalette;
	
	
	@Column(name="total_colis_reel")
	private Long totalColisReel;
	
	
	
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

	public Boolean isBlSuppression() {
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

	public Set<DetLivraisonVenteEntity> getListDetLivraisonVente() {
		return listDetLivraisonVente;
	}

	public void setListDetLivraisonVente(Set<DetLivraisonVenteEntity> listDetLivraisonVente) {
		this.listDetLivraisonVente = listDetLivraisonVente;
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

	public Boolean isModifier() {
		return modifier;
	}

	public void setModifier(Boolean modifier) {
		this.modifier = modifier;
	}

	public Long getModepaiementId() {
		return modepaiementId;
	}

	public void setModepaiementId(Long modepaiementId) {
		this.modepaiementId = modepaiementId;
	}

	public Long getMarcheId() {
		return marcheId;
	}

	public void setMarcheId(Long marcheId) {
		this.marcheId = marcheId;
	}

	/** contructeur DropList & Cache */
	public LivraisonVenteEntity(String reference) {
		super();
		this.reference = reference;
	}
	/** constructeurgolbal */
	public LivraisonVenteEntity() {
		super();
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
		return "LivraisonVenteEntity [id=" + id + ", reference=" + reference
				+ ", date=" + date + ", montantHTaxe=" + montantHTaxe
				+ ", montantTTC=" + montantTTC + ", montantTaxe=" + montantTaxe
				+ ", montantRemise=" + montantRemise + ", observations="
				+ observations + ", infoSortie=" + infoSortie
				+ ", partieIntId=" + partieIntId + ", metrageTotal="
				+ metrageTotal + ", etat=" + etat + ", transporteur="
				+ transporteur + ", listDetLivraisonVente="
				+ listDetLivraisonVente + ", blSuppression=" + blSuppression
				+ ", dateSuppression=" + dateSuppression + ", dateCreation="
				+ dateCreation + ", dateModification=" + dateModification
				+ ", version=" + version + ", modifier=" + modifier
				+ ", modepaiementId=" + modepaiementId + ", marcheId="
				+ marcheId + ", natureLivraison=" + natureLivraison + "]";
	}

	public Long getTotalColisReel() {
		return totalColisReel;
	}

	public void setTotalColisReel(Long totalColisReel) {
		this.totalColisReel = totalColisReel;
	}

	
	
}
