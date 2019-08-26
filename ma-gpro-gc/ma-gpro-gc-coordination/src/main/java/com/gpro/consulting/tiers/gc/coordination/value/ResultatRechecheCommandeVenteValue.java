/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.List;

/**
 * @author $Ameni
 *
 */
public class ResultatRechecheCommandeVenteValue {

	private Long nombreResultaRechercher;

	private List<CommandeVenteValue> commandeVenteValues;

	/*********** Getter & Settet ************/
	/**
	 * @return the commandeVenteValues
	 */
	public List<CommandeVenteValue> getCommandeVenteValues() {
		return commandeVenteValues;
	}

	/**
	 * @param commandeVenteValues
	 *            the commandeVenteValues to set
	 */
	public void setCommandeVenteValues(
			List<CommandeVenteValue> commandeVenteValues) {
		this.commandeVenteValues = commandeVenteValues;
	}

	/**
	 * @return the nombreResultaRechercher
	 */
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	/**
	 * @param nombreResultaRechercher
	 *            the nombreResultaRechercher to set
	 */
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}
}
