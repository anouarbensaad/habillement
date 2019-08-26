/**
 * 
 */
package com.gpro.consulting.tiers.gc.coordination.value;

import java.util.List;

/**
 * @author $Ameni
 *
 */
public class ResultatRechecheCommandeAchatValue {

	private Long nombreResultaRechercher;

	private List<CommandeAchatValue> commandeAchatValues;

	/*********** Getter & Settet ************/
	/**
	 * @return the nombreResultaRechercher
	 */
	public Long getNombreResultaRechercher() {
		return nombreResultaRechercher;
	}

	/**
	 * @param nombreResultaRechercher the nombreResultaRechercher to set
	 */
	public void setNombreResultaRechercher(Long nombreResultaRechercher) {
		this.nombreResultaRechercher = nombreResultaRechercher;
	}

	/**
	 * @return the commandeAchatValues
	 */
	public List<CommandeAchatValue> getCommandeAchatValues() {
		return commandeAchatValues;
	}

	/**
	 * @param commandeAchatValues the commandeAchatValues to set
	 */
	public void setCommandeAchatValues(List<CommandeAchatValue> commandeAchatValues) {
		this.commandeAchatValues = commandeAchatValues;
	}

}
