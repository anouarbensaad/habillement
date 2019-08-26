package com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value;

/**
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public class DetLivraisonVenteValue implements Comparable<DetLivraisonVenteValue>{
	
	private Long id;
	private Long produitId;
	private Long quantite;
	private String unite;
	private Long nombreColis;
	private Long livraisonVenteId;
	private Double remise;
	
	
	private String choix;
	
	
	
	// this values used only on validate action
	private String produitDesignation;
	private String produitReference;
	
	
	
	private String numeroOF;
	private Long ofId;
	private Long quantiteColis;
	
	private String palette;
	
	
	

	

	private Double temps;
	
	
	private String codeDouane ;

	private String composition;
	
	
	//TISSU 1
	
	private String referenceTissu1;
	
	private String designationTissu1;
	
	
	private Double emploiTissu1;
	
	private Double prixTissu1;
	
	private String typeTissu1;
	
	
	//TISSU 2
	
	
	private String referenceTissu2;
	
	private String designationTissu2;
	
	
	private Double emploiTissu2;
	
	private Double prixTissu2;
	
	private String typeTissu2;
	
	
	
	//TISSU 3
	
	private String referenceTissu3;
	
	private String designationTissu3;
	
	
	private Double emploiTissu3;
	
	private Double prixTissu3;
	
	private String typeTissu3;
	
	//TISSU 4
	
	private String referenceTissu4;
	
	private String designationTissu4;
	
	
	private Double emploiTissu4;
	
	private Double prixTissu4;
	
	private String typeTissu4;

	
	
	public Double getTemps() {
		return temps;
	}

	public void setTemps(Double temps) {
		this.temps = temps;
	}

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

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getPalette() {
		return palette;
	}

	public void setPalette(String palette) {
		this.palette = palette;
	}

	private Double prixUnitaireHT;
	private Double prixTotalHT;
	
	//added on 18/02/2016, by Wahid Gazzah
	//updated on 24/02/2016, by Wahid Gazzah, from Long to String
	
	private String typeRapport;
	
	//Added on 03/10/2016 - Zeineb Medimagh
	private Long traitementFaconId;
	
	//Added on 17/10/2016 - Zeineb Medimagh
	private Long ficheId;
	private String refMiseList;
	
	@Override
	public int compareTo(DetLivraisonVenteValue o) {
		DetLivraisonVenteValue element = (DetLivraisonVenteValue)o;
		return (element.getFicheId().compareTo(ficheId));
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
	public Long getLivraisonVenteId() {
		return livraisonVenteId;
	}
	public void setLivraisonVenteId(Long livraisonVenteId) {
		this.livraisonVenteId = livraisonVenteId;
	}
	public Double getRemise() {
		return remise;
	}
	public void setRemise(Double remise) {
		this.remise = remise;
	}
	public String getProduitDesignation() {
		return produitDesignation;
	}
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public String getProduitReference() {
		return produitReference;
	}
	public void setProduitReference(String produitReference) {
		this.produitReference = produitReference;
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
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	public String getTypeRapport() {
		return typeRapport;
	}
	public void setTypeRapport(String typeRapport) {
		this.typeRapport = typeRapport;
	}
	public Long getTraitementFaconId() {
		return traitementFaconId;
	}
	public void setTraitementFaconId(Long traitementFaconId) {
		this.traitementFaconId = traitementFaconId;
	}

	public Long getFicheId() {
		return ficheId;
	}
	public void setFicheId(Long ficheId) {
		this.ficheId = ficheId;
	}
	public String getRefMiseList() {
		return refMiseList;
	}
	public void setRefMiseList(String refMiseList) {
		this.refMiseList = refMiseList;
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

	@Override
	public String toString() {
		return "DetLivraisonVenteValue [id=" + id + ", produitId=" + produitId + ", quantite=" + quantite + ", unite="
				+ unite + ", nombreColis=" + nombreColis + ", livraisonVenteId=" + livraisonVenteId + ", remise="
				+ remise + ", produitDesignation=" + produitDesignation + ", produitReference=" + produitReference
				+ ", prixUnitaireHT=" + prixUnitaireHT + ", prixTotalHT=" + prixTotalHT + ", choix=" + choix
				+ ", typeRapport=" + typeRapport + ", traitementFaconId=" + traitementFaconId + ", ficheId=" + ficheId
				+ ", refMiseList=" + refMiseList + "]";
	}
	
	
}
