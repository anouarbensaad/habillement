package com.gpro.consulting.tiers.commun.coordination.value;
import java.util.Calendar;
// TODO: Auto-generated Javadoc

/**
 * The Class DetailsPrixProduitValue.
 * @author med
 */
public class DetailsPrixProduitValue   {
	
	/** The id. */
	private Long id;
	
	/** The produit id. */
	private Long produitId;
	
	/** The methode. */
	private String methode;
	
	/** The prix vente. */
	private double prixVente;
	
	/** The quantite inferieur. */
	private Long quantiteInferieur;
	
	/** The quantite superieur. */
	private Long quantiteSuperieur;
	
	/** The date deb. */
	private Calendar dateDeb;
	
	/** The date fin. */
	private Calendar dateFin;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * Gets the produit id.
	 *
	 * @return the produit id
	 */
	public Long getProduitId() {
		return produitId;
	}
	
	/**
	 * Sets the produit id.
	 *
	 * @param produitId the new produit id
	 */
	public void setProduitId(Long produitId) {
		this.produitId = produitId;
	}
	
	/**
	 * Gets the methode.
	 *
	 * @return the methode
	 */
	public String getMethode() {
		return methode;
	}
	
	/**
	 * Sets the methode.
	 *
	 * @param methode the new methode
	 */
	public void setMethode(String methode) {
		this.methode = methode;
	}
	
	/**
	 * Gets the prix vente.
	 *
	 * @return the prix vente
	 */
	public double getPrixVente() {
		return prixVente;
	}
	
	/**
	 * Sets the prix vente.
	 *
	 * @param prixVente the new prix vente
	 */
	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}
	
	/**
	 * Gets the quantite inferieur.
	 *
	 * @return the quantite inferieur
	 */
	public Long getQuantiteInferieur() {
		return quantiteInferieur;
	}
	
	/**
	 * Sets the quantite inferieur.
	 *
	 * @param quantiteInferieur the new quantite inferieur
	 */
	public void setQuantiteInferieur(Long quantiteInferieur) {
		this.quantiteInferieur = quantiteInferieur;
	}
	
	/**
	 * Gets the quantite superieur.
	 *
	 * @return the quantite superieur
	 */
	public Long getQuantiteSuperieur() {
		return quantiteSuperieur;
	}
	
	/**
	 * Sets the quantite superieur.
	 *
	 * @param quantiteSuperieur the new quantite superieur
	 */
	public void setQuantiteSuperieur(Long quantiteSuperieur) {
		this.quantiteSuperieur = quantiteSuperieur;
	}
	
	/**
	 * Gets the date deb.
	 *
	 * @return the date deb
	 */
	public Calendar getDateDeb() {
		return dateDeb;
	}
	
	/**
	 * Sets the date deb.
	 *
	 * @param dateDeb the new date deb
	 */
	public void setDateDeb(Calendar dateDeb) {
		this.dateDeb = dateDeb;
	}
	
	/**
	 * Gets the date fin.
	 *
	 * @return the date fin
	 */
	public Calendar getDateFin() {
		return dateFin;
	}
	
	/**
	 * Sets the date fin.
	 *
	 * @param dateFin the new date fin
	 */
	public void setDateFin(Calendar dateFin) {
		this.dateFin = dateFin;
	}
	
}