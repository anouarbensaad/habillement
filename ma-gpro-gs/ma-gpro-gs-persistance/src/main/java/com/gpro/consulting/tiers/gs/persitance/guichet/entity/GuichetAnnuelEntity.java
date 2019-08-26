package com.gpro.consulting.tiers.gs.persitance.guichet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gpro.consulting.tiers.gs.coordination.IConstante;


/**
 * @author rkhaskho
 *
 */
@Entity 
@Table(name = IConstante.TABLE_GENERATEUR_ANNUEL)
public class GuichetAnnuelEntity {
	/** L'id de la table: l'id */
	  @Id
	  private Long id;
	  
	  
	  /** Année courante. */
	  @Column(name = "annee")
	  private Long annee;


	
	  
	  /** Numéro Bon entree. */
	  @Column(name = "ref_bon_entree")
	  private Long numReferenceBonEntreeCourante;

	  /** Numéro Bon sortie. */
	  @Column(name = "ref_bon_sortie")
	  private Long numReferenceBonSortieCourante;
 
	  
	  /**
	 * @return the annee
	 */
	public Long getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(Long annee) {
		this.annee = annee;
	}

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

	public Long getNumReferenceBonEntreeCourante() {
		return numReferenceBonEntreeCourante;
	}

	public void setNumReferenceBonEntreeCourante(Long numReferenceBonEntreeCourante) {
		this.numReferenceBonEntreeCourante = numReferenceBonEntreeCourante;
	}

	public Long getNumReferenceBonSortieCourante() {
		return numReferenceBonSortieCourante;
	}

	public void setNumReferenceBonSortieCourante(Long numReferenceBonSortieCourante) {
		this.numReferenceBonSortieCourante = numReferenceBonSortieCourante;
	}

}
