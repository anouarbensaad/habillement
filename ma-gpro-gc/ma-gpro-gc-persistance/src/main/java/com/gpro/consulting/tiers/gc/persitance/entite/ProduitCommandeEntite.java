package com.gpro.consulting.tiers.gc.persitance.entite;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.gpro.consulting.tiers.commun.persistance.entity.ProduitEntity;
import com.gpro.consulting.tiers.gc.coordination.IConstanteGC;
/**
* Classe: ProduitCommande
* @author $AMENI
* 
*/
@Entity
@Table(name = IConstanteGC.TABLE_GC_PRODUITCOMMANDE)
public class ProduitCommandeEntite implements Serializable{
	private static final long serialVersionUID = -4053870380839663708L;

	@Id
	@SequenceGenerator(name="PRODUITCOMMANDE_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_PRODUITCOMMANDE,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUITCOMMANDE_ID_GENERATOR")
    private Long id;
	
	/** The prix. */
	@Column(name = "prix")
	private Double prix;

	/** The devise. */
	@Column(name = "devise")
	private String devise;
	
	/** The quantite. */
	@Column(name = "quantite")
	private Long quantite;
	
	/** The date livraison. */
	@Column(name="date_livraison")
	private Calendar dateLivraison;
	
	/** many-to-one association to Produit**. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_produit_id")
	private ProduitEntity produit;
	
	/** many-to-one association to CommandeVente**. */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "gc_commandevente_id")
	private CommandeVenteEntite commandeVente;
	
	@OneToMany(mappedBy="produitCommande", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DetailsProduitCommandeEntity> listDetailsProduitCommande;
	
	/************* Getters & Setters *****************/
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the prix
	 */
	public Double getPrix() {
		return prix;
	}

	/**
	 * @param prix the prix to set
	 */
	public void setPrix(Double prix) {
		this.prix = prix;
	}

	/**
	 * @return the devise
	 */
	public String getDevise() {
		return devise;
	}

	/**
	 * @param devise the devise to set
	 */
	public void setDevise(String devise) {
		this.devise = devise;
	}

	/**
	 * @return the quantite
	 */
	public Long getQuantite() {
		return quantite;
	}

	/**
	 * @param quantite the quantite to set
	 */
	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	/**
	 * @return the dateLivraison
	 */
	public Calendar getDateLivraison() {
		return dateLivraison;
	}

	/**
	 * @param dateLivraison the dateLivraison to set
	 */
	public void setDateLivraison(Calendar dateLivraison) {
		this.dateLivraison = dateLivraison;
	}

	/**
	 * @return the produit
	 */
	public ProduitEntity getProduit() {
		return produit;
	}

	/**
	 * @param produit the produit to set
	 */
	public void setProduit(ProduitEntity produit) {
		this.produit = produit;
	}

	/**
	 * @return the commandeVente
	 */
	public CommandeVenteEntite getCommandeVente() {
		return commandeVente;
	}

	/**
	 * @param commandeVente the commandeVente to set
	 */
	public void setCommandeVente(CommandeVenteEntite commandeVente) {
		this.commandeVente = commandeVente;
	}
	
	/************* Equals & ToString *****************/
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

	public Set<DetailsProduitCommandeEntity> getListDetailsProduitCommande() {
		return listDetailsProduitCommande;
	}

	public void setListDetailsProduitCommande(
			Set<DetailsProduitCommandeEntity> listDetailsProduitCommande) {
		this.listDetailsProduitCommande = listDetailsProduitCommande;
	}

	  
}
