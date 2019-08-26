package com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gpao.coordination.IConstante;
import com.gpro.consulting.tiers.gpao.persitance.bonsortiefini.entity.BonSortieFiniEntity;



@Entity
@Table(name=IConstante.TABLE_GP_COLIS)
public class ColisEntity implements Serializable{
	
	private static final long serialVersionUID = -7070495944006203209L;
	
	@Id
	@SequenceGenerator(name="COLIS_ID_GENERATOR", sequenceName=IConstante.SEQUENCE_GP_PAQUET, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COLIS_ID_GENERATOR")
    private Long id;
	
	@Column(name = "NUM")
	private Long num;
	
	@Column(name = "EB_COULEUR_ID")
	private Long couleurId;
	
	@Column(name = "EB_TAILLE_ID")
	private Long tailleId;
	
	@Column(name = "QUANTITE")
	private Long quantite;
	
	@Column(name = "ORDRE")
	private Long ordre;	
	
	
	@Column(name = "designation_taille")
	private String designationTaille;
	
	@Column(name = "designation_couleur")
	private String designationCouleur; 
	
	@ManyToOne
	@JoinColumn(name = "GP_FICHE_ID")
	private FicheColisageEntity ficheColisage;
	
	@Column(name = "BL_SUPPRESSION")
	private Boolean blSuppression;
	
	@Column(name = "DATE_SUPPRESSION")
	private Calendar dateSuppression;
	
	@Column(name = "DATE_CREATION")
	private Calendar dateCreation;
	
	@Column(name = "DATE_MODIFICATION")
	private Calendar dateModification;
	
	@Column(name = "VERSION")
	private String version;

	@Column(name = "poids_net")
	private Double poidsNet ;
	
	@Column(name = "poids_brut")
	private Double poidsBrut ;
	
	@Column(name = "quantite_designation")
	private String quantiteDesignation ;
	
	
	@Column(name = "palette")
	private String palette ;
	
	
	@Column(name = "choix")
	private String choix ;
	
	
	@Column(name = "of")
	private String of ;
	
	@Column(name = "ordre_fiche")
	private Long ordreFiche;
	
	
	
	//added on 21/01/2019, by Wahid Gazzah
		@ManyToOne(fetch=FetchType.LAZY)
		@JoinColumn(name = "BON_SORTIE_ID")
		private BonSortieFiniEntity bonSortie;
		
		
		
		@Column(name="DATE_SORTIE")
		private Calendar dateSortie;
		
	
	
		@Column(name = "ordre_palette")
		private String ordrePalette ;
		
		@Column(name = "fictif")
		private Boolean fictif;
		
		
		
		
	
	
	
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

	public Long getOrdre() {
		return ordre;
	}

	public void setOrdre(Long ordre) {
		this.ordre = ordre;
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

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public FicheColisageEntity getFicheColisage() {
		return ficheColisage;
	}

	public void setFicheColisage(FicheColisageEntity ficheColisage) {
		this.ficheColisage = ficheColisage;
	}

	public String getDesignationTaille() {
		return designationTaille;
	}

	public void setDesignationTaille(String designationTaille) {
		this.designationTaille = designationTaille;
	}

	public String getDesignationCouleur() {
		return designationCouleur;
	}

	public void setDesignationCouleur(String designationCouleur) {
		this.designationCouleur = designationCouleur;
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

	public String getOf() {
		return of;
	}

	public void setOf(String of) {
		this.of = of;
	}

	public BonSortieFiniEntity getBonSortie() {
		return bonSortie;
	}

	public void setBonSortie(BonSortieFiniEntity bonSortie) {
		this.bonSortie = bonSortie;
	}

	public Calendar getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Calendar dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Long getOrdreFiche() {
		return ordreFiche;
	}

	public void setOrdreFiche(Long ordreFiche) {
		this.ordreFiche = ordreFiche;
	}

	

	
	
	
}
