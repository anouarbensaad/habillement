package com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value;


/**
 * 
 * @author Wahid Gazzah
 * @since 29/01/2016
 *
 */
public class BonLivraisonReportProductValue {
	
	private Long produitId;
	private String produitCode;
	private String produitDesignation;
	private String mise;
	private Long quantite;
	private String unite;
	private Long nombreColis;
	private Double prixUHT;
	private Double remise;
	private Double montantHT;
	
    private Boolean principal;
	
	
	
    
    
    public Boolean getPrincipal() {
		return principal;
	}
	public void setPrincipal(Boolean principal) {
		this.principal = principal;
	}
	//added on 18/02/2016, by Wahid Gazzah
	private Long detLivraisonVenteId;
	
	//updated on 18/02/2016, by Wahid Gazzah, from Long to String
	private String choix;
	
	
	
	private String numeroOF;
	
	private Long quantiteColis;
	
	private String palette;
	
	
	private String composition;
	
	
	private InformationOfValue informationOfValue;
	
	
	
	public InformationOfValue getInformationOfValue() {
		return informationOfValue;
	}
	public void setInformationOfValue(InformationOfValue informationOfValue) {
		this.informationOfValue = informationOfValue;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getProduitCode() {
		return produitCode;
	}
	public void setProduitCode(String produitCode) {
		this.produitCode = produitCode;
	}
	public String getProduitDesignation() {
		return produitDesignation;
	}
	public void setProduitDesignation(String produitDesignation) {
		this.produitDesignation = produitDesignation;
	}
	public String getMise() {
		return mise;
	}
	public void setMise(String mise) {
		this.mise = mise;
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
	public Double getPrixUHT() {
		return prixUHT;
	}
	public void setPrixUHT(Double prixUHT) {
		this.prixUHT = prixUHT;
	}
	public Double getRemise() {
		return remise;
	}
	public void setRemise(Double remise) {
		this.remise = remise;
	}
	public Double getMontantHT() {
		return montantHT;
	}
	public void setMontantHT(Double montantHT) {
		this.montantHT = montantHT;
	}
	public Long getProduitId() {
		return produitId;
	}
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	public Long getDetLivraisonVenteId() {
		return detLivraisonVenteId;
	}
	public void setDetLivraisonVenteId(Long detLivraisonVenteId) {
		this.detLivraisonVenteId = detLivraisonVenteId;
	}
	public String getChoix() {
		return choix;
	}
	public void setChoix(String choix) {
		this.choix = choix;
	}
	public String getNumeroOF() {
		return numeroOF;
	}
	public void setNumeroOF(String numeroOF) {
		this.numeroOF = numeroOF;
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
	
	

	
}
