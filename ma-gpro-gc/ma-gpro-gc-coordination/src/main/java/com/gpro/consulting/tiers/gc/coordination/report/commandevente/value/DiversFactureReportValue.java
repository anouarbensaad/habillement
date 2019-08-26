package com.gpro.consulting.tiers.gc.coordination.report.commandevente.value;

/**
 * @author Ameni Berrich
 *
 */
public class DiversFactureReportValue {

	private Long id;
	private String designation;
	private Double prix;
	private Long quantite;
	private Double prixTotal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public Double getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Double prixTotal) {
		this.prixTotal = prixTotal;
	}

	@Override
	public String toString() {
		return "DiversFactureReportValue [id=" + id + ", designation=" + designation + ", prix=" + prix + ", quantite="
				+ quantite + ", prixTotal=" + prixTotal + "]";
	}

}
