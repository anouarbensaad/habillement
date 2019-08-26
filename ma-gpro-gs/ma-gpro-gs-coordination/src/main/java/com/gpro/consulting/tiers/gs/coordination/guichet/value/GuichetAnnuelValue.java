package com.gpro.consulting.tiers.gs.coordination.guichet.value;

public class GuichetAnnuelValue {

	
	/** id */
	private Long id;
	
	/** Annnée */
	 private Long annee;

	  private Long numReferenceBonEntree;

	  private Long numReferenceBonSortie;
	  
	  /**
	 * Constructeur
	 */
	public GuichetAnnuelValue() {

	}

	/**
	 * Constructeur
	 * @param annee
	 * @param numReferenceCourant
	 */
	public GuichetAnnuelValue(Long annee, Long numReferenceCourant) {
		super();
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

	public Long getNumReferenceBonEntree() {
		return numReferenceBonEntree;
	}

	public void setNumReferenceBonEntree(Long numReferenceBonEntree) {
		this.numReferenceBonEntree = numReferenceBonEntree;
	}

	public Long getNumReferenceBonSortie() {
		return numReferenceBonSortie;
	}

	public void setNumReferenceBonSortie(Long numReferenceBonSortie) {
		this.numReferenceBonSortie = numReferenceBonSortie;
	}

}
