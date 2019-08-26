package com.gpro.consulting.tiers.gpao.persitance.bonlivraison.entitie;

import java.io.Serializable;

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

import com.gpro.consulting.tiers.gpao.coordination.IConstanteCommerciale;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Entity
@Table(name = IConstanteCommerciale.TABLE_GC_DETLIVRAISONVENTE)
public class DetLivraisonVenteEntity implements Serializable {

	private static final long serialVersionUID = 569048653919573589L;

	@Id
	@SequenceGenerator(name = "DETLIVRAISONVENTE_ID_GENERATOR", sequenceName = IConstanteCommerciale.SEQUENCE_GC_CDLV_SEQ, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DETLIVRAISONVENTE_ID_GENERATOR")
	private Long id;

	@Column(name = "QUANTITE")
	private Long quantite;

	@Column(name = "UNITE")
	private String unite;

	@Column(name = "NOMBRE_COLIS")
	private Long nombreColis;

	@Column(name = "EB_PRODUIT_ID")
	private Long produitId;

	@Column(name = "REMISE")
	private Double remise;

	/** many-to-one association to {@link LivraisonVenteEntity}. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "GC_LIVVENTE_ID")
	private LivraisonVenteEntity livraisonVente;

	// added on 18/02/2016, by Wahid Gazzah
	// updated on 24/02/2016, by Wahid Gazzah from Long to String
	@Column(name = "CHOIX")
	private String choix;

	// Added on 03/10/2016 - Zeineb Medimagh
	@Column(name = "GL_TRAITEMENTFACON_ID")
	private Long traitementFaconId;

	// Added on 11/10/2016 - Zeineb Medimagh
	@Column(name = "PUHT")
	private Double prixUnitaireHT;

	@Column(name = "PTHT")
	private Double prixTotalHT;

	// Added on 17/10/2016 - Zeineb Medimagh
	@Column(name = "FICHE_ID")
	private Long ficheId;

	@Column(name = "numero")
	private String numeroOF;

	@Column(name = "of_id")
	private Long ofId;

	@Column(name = "qte_colis")
	private Long quantiteColis;

	@Column(name = "produit_reference")
	private String produitReference;

	@Column(name = "produit_designation")
	private String produitDesignation;

	@Column(name = "palette")
	private String palette;

	// Added By Samer :champs supplementaires cas ISSATEX 08/04/2019

	@Column(name = "code_douane")
	private String codeDouane;

	@Column(name = "composition")
	private String composition;

	//TISSU 1
	
	@Column(name = "reference_tissu1")
	private String referenceTissu1;

	@Column(name = "designation_tissu1")
	private String designationTissu1;

	@Column(name = "emploi_tissu1")
	private Double emploiTissu1;

	@Column(name = "prix_tissu1")

	private Double prixTissu1;

	@Column(name = "type_tissu1")
	private String typeTissu1;

	//TISSU 2
	
	@Column(name = "reference_tissu2")
	private String referenceTissu2;

	@Column(name = "designation_tissu2")
	private String designationTissu2;

	@Column(name = "emploi_tissu2")
	private Double emploiTissu2;

	@Column(name = "prix_tissu2")
	private Double prixTissu2;

	@Column(name = "type_tissu2")
	private String typeTissu2;
	
	// TISSU 3
	@Column(name = "reference_tissu3")
	private String referenceTissu3;

	@Column(name = "designation_tissu3")
	private String designationTissu3;

	@Column(name = "emploi_tissu3")
	private Double emploiTissu3;

	@Column(name = "prix_tissu3")
	private Double prixTissu3;

	@Column(name = "type_tissu3")
	private String typeTissu3;
	
	// TISSU 4
	
	@Column(name = "reference_tissu4")
	private String referenceTissu4;

	@Column(name = "designation_tissu4")
	private String designationTissu4;

	@Column(name = "emploi_tissu4")
	private Double emploiTissu4;

	@Column(name = "prix_tissu4")
	private Double prixTissu4;

	@Column(name = "type_tissu4")
	private String typeTissu4;

	public String getCodeDouane() {
		return codeDouane;
	}

	public void setCodeDouane(String codeDouane) {
		this.codeDouane = codeDouane;
	}

	public String getReferenceTissu1() {
		return referenceTissu1;
	}

	public void setReferenceTissu1(String referenceTissu1) {
		this.referenceTissu1 = referenceTissu1;
	}

	public String getDesignationTissu1() {
		return designationTissu1;
	}

	public void setDesignationTissu1(String designationTissu1) {
		this.designationTissu1 = designationTissu1;
	}

	public Double getEmploiTissu1() {
		return emploiTissu1;
	}

	public void setEmploiTissu1(Double emploiTissu1) {
		this.emploiTissu1 = emploiTissu1;
	}

	public Double getPrixTissu1() {
		return prixTissu1;
	}

	public void setPrixTissu1(Double prixTissu1) {
		this.prixTissu1 = prixTissu1;
	}

	public String getTypeTissu1() {
		return typeTissu1;
	}

	public void setTypeTissu1(String typeTissu1) {
		this.typeTissu1 = typeTissu1;
	}

	public String getReferenceTissu2() {
		return referenceTissu2;
	}

	public void setReferenceTissu2(String referenceTissu2) {
		this.referenceTissu2 = referenceTissu2;
	}

	public String getDesignationTissu2() {
		return designationTissu2;
	}

	public void setDesignationTissu2(String designationTissu2) {
		this.designationTissu2 = designationTissu2;
	}

	public Double getEmploiTissu2() {
		return emploiTissu2;
	}

	public void setEmploiTissu2(Double emploiTissu2) {
		this.emploiTissu2 = emploiTissu2;
	}

	public Double getPrixTissu2() {
		return prixTissu2;
	}

	public void setPrixTissu2(Double prixTissu2) {
		this.prixTissu2 = prixTissu2;
	}

	public String getTypeTissu2() {
		return typeTissu2;
	}

	public void setTypeTissu2(String typeTissu2) {
		this.typeTissu2 = typeTissu2;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProduitId() {
		return produitId;
	}

	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public Long getNombreColis() {
		return nombreColis;
	}

	public void setNombreColis(Long nombreColis) {
		this.nombreColis = nombreColis;
	}

	public LivraisonVenteEntity getLivraisonVente() {
		return livraisonVente;
	}

	public void setLivraisonVente(LivraisonVenteEntity livraisonVente) {
		this.livraisonVente = livraisonVente;
	}

	public Double getRemise() {
		return remise;
	}

	public void setRemise(Double remise) {
		this.remise = remise;
	}

	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public Long getTraitementFaconId() {
		return traitementFaconId;
	}

	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}

	public Double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	public void setPrixUnitaireHT(Double prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}

	public Double getPrixTotalHT() {
		return prixTotalHT;
	}

	public void setPrixTotalHT(Double prixTotalHT) {
		this.prixTotalHT = prixTotalHT;
	}

	public Long getFicheId() {
		return ficheId;
	}

	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}

	public String getNumeroOF() {
		return numeroOF;
	}

	public void setNumeroOF(String numeroOF) {
		this.numeroOF = numeroOF;
	}

	public Long getOfId() {
		return ofId;
	}

	public void setOfId(Long ofId) {
		this.ofId = ofId;
	}

	public Long getQuantiteColis() {
		return quantiteColis;
	}

	public void setQuantiteColis(Long quantiteColis) {
		this.quantiteColis = quantiteColis;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	public String getReferenceTissu3() {
		return referenceTissu3;
	}

	public void setReferenceTissu3(String referenceTissu3) {
		this.referenceTissu3 = referenceTissu3;
	}

	public String getDesignationTissu3() {
		return designationTissu3;
	}

	public void setDesignationTissu3(String designationTissu3) {
		this.designationTissu3 = designationTissu3;
	}

	public Double getEmploiTissu3() {
		return emploiTissu3;
	}

	public void setEmploiTissu3(Double emploiTissu3) {
		this.emploiTissu3 = emploiTissu3;
	}

	public Double getPrixTissu3() {
		return prixTissu3;
	}

	public void setPrixTissu3(Double prixTissu3) {
		this.prixTissu3 = prixTissu3;
	}

	public String getTypeTissu3() {
		return typeTissu3;
	}

	public void setTypeTissu3(String typeTissu3) {
		this.typeTissu3 = typeTissu3;
	}

	public String getReferenceTissu4() {
		return referenceTissu4;
	}

	public void setReferenceTissu4(String referenceTissu4) {
		this.referenceTissu4 = referenceTissu4;
	}

	public String getDesignationTissu4() {
		return designationTissu4;
	}

	public void setDesignationTissu4(String designationTissu4) {
		this.designationTissu4 = designationTissu4;
	}

	public Double getEmploiTissu4() {
		return emploiTissu4;
	}

	public void setEmploiTissu4(Double emploiTissu4) {
		this.emploiTissu4 = emploiTissu4;
	}

	public Double getPrixTissu4() {
		return prixTissu4;
	}

	public void setPrixTissu4(Double prixTissu4) {
		this.prixTissu4 = prixTissu4;
	}

	public String getTypeTissu4() {
		return typeTissu4;
	}

	public void setTypeTissu4(String typeTissu4) {
		this.typeTissu4 = typeTissu4;
	}
	
	

}
