package com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.report.value;

/**
 * 
 * @author Wahid Gazzah
 * @since 12/01/2016
 *
 */
public class GroupementRouleauFiniChoixReportValue {
	
	
	private String choix;
	
	private Double quantite;

	private Boolean fictif;
	
	private String carton;
	
	public String getChoix() {
		return choix;
	}

	public void setChoix(String choix) {
		this.choix = choix;
	}

	public Double getQuantite() {
		return quantite;
	}

	public void setQuantite(Double quantite) {
		this.quantite = quantite;
	}

	@Override
	public String toString() {
		return "GroupementRouleauFiniChoixReportValue [choix=" + choix
				+ ", quantite=" + quantite + "]";
	}

	public Boolean getFictif() {
		return fictif;
	}

	public void setFictif(Boolean fictif) {
		this.fictif = fictif;
	}

	public String getCarton() {
		return carton;
	}

	public void setCarton(String carton) {
		this.carton = carton;
	}
	

}
