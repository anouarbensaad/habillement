package com.gpro.consulting.tiers.gc.persitance.livraisonVente.entite;

import java.io.Serializable;
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
 * @author Ameni Berrich
 *
 */
@Entity
@Table(name=IConstanteGC.TABLE_GC_PRODUITLIVRAISON)
public class ProduitLivraisonEntite implements Serializable{

	private static final long serialVersionUID = -3411834275733289143L;
	
	@Id
	@SequenceGenerator(name="PRODUITLIVRAISONVENTE_ID_GENERATOR", sequenceName=IConstanteGC.SEQUENCE_GC_PRODUITLIVRAISON,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUITLIVRAISONVENTE_ID_GENERATOR")
    private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "eb_produit_id")
	private ProduitEntity produit;
	
	@Column(name="prix")
	private Double prix;
	
	@Column(name="devise")
	private String devise;

	@Column(name="quantite")
	private Long quantite;
	
	@Column(name = "reference_commande")
	private String referenceCommande;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "gc_livraisonvente_id")
	private LivraisonVenteEntite livraisonVente;
	
	@OneToMany(mappedBy="produitLivraison", cascade = CascadeType.ALL, orphanRemoval=true)
	private Set<DetailProduitLivraisonEntite> listDetailsProduitLivraison;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProduitEntity getProduit() {
		return produit;
	}

	public void setProduit(ProduitEntity produit) {
		this.produit = produit;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public String getDevise() {
		return devise;
	}

	public void setDevise(String devise) {
		this.devise = devise;
	}

	public Long getQuantite() {
		return quantite;
	}

	public void setQuantite(Long quantite) {
		this.quantite = quantite;
	}

	public LivraisonVenteEntite getLivraisonVente() {
		return livraisonVente;
	}

	public void setLivraisonVente(LivraisonVenteEntite livraisonVente) {
		this.livraisonVente = livraisonVente;
	}

	public Set<DetailProduitLivraisonEntite> getListDetailsProduitLivraison() {
		return listDetailsProduitLivraison;
	}

	public void setListDetailsProduitLivraison(
			Set<DetailProduitLivraisonEntite> listDetailsProduitLivraison) {
		this.listDetailsProduitLivraison = listDetailsProduitLivraison;
	}

	public String getReferenceCommande() {
		return referenceCommande;
	}

	public void setReferenceCommande(String referenceCommande) {
		this.referenceCommande = referenceCommande;
	}

	@Override
	public String toString() {
		return "ProduitLivraisonEntite [id=" + id + ", produit=" + produit + ", referenceCommande=" + referenceCommande
				+ ", livraisonVente=" + livraisonVente + "]";
	}

}
