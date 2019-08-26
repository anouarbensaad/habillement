package com.gpro.consulting.tiers.gc.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.value.CommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereCommandeVenteValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheCommandeVenteValue;

/**
 * @author $Ameni
 *
 */
public interface ICommandeVentePersistance {
	
	public String creerCommandeVente(CommandeVenteValue pCommandeVenteValue);
	
	public void supprimerCommandeVente(Long pId);
	
	public String modifierCommandeVente(CommandeVenteValue pCommandeVenteValue);
	
	public CommandeVenteValue rechercheCommandeVenteParId(Long pId);
	
	public List<CommandeVenteValue> listeCommandeVente();
	
	public ResultatRechecheCommandeVenteValue rechercherCommandeVenteMultiCritere(
			RechercheMulticritereCommandeVenteValue pRechercheCommandeVenteMulitCritere);

	public List<CommandeVenteValue> getAllByReference(List<String> refBonCmdList);

	public List<CommandeVenteValue> getAll();

	public boolean existeOF(String numeroOF);

}
