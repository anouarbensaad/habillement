/**
 * 
 */
package com.gpro.consulting.tiers.gpao.coordination.vue;

/**
 * @author $Ameni
 *
 */
public class QuantiteVue {
	/** The id. */
    private Long id;
	
    /** CommandeId. */
	private Long CommandeId;
	
	/** The quantiteBC. */
	private Long quantiteBC;
	
	/** The quantiteRestante. */
	private Long quantiteRestante;
	
	/** The numeroBC. */
	private String numeroBC;
	

	/************* Getter & Setter ***********/
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
	 * @return the quantiteBC
	 */
	public Long getQuantiteBC() {
		return quantiteBC;
	}

	/**
	 * @param quantiteBC the quantiteBC to set
	 */
	public void setQuantiteBC(Long quantiteBC) {
		this.quantiteBC = quantiteBC;
	}

	/**
	 * @return the quantiteRestante
	 */
	public Long getQuantiteRestante() {
		return quantiteRestante;
	}

	/**
	 * @param quantiteRestante the quantiteRestante to set
	 */
	public void setQuantiteRestante(Long quantiteRestante) {
		this.quantiteRestante = quantiteRestante;
	}

	/**
	 * @return the numeroBC
	 */
	public String getNumeroBC() {
		return numeroBC;
	}

	/**
	 * @param numeroBC the numeroBC to set
	 */
	public void setNumeroBC(String numeroBC) {
		this.numeroBC = numeroBC;
	}

	/**
	 * @return the commandeId
	 */
	public Long getCommandeId() {
		return CommandeId;
	}

	/**
	 * @param commandeId the commandeId to set
	 */
	public void setCommandeId(Long commandeId) {
		CommandeId = commandeId;
	}
}
