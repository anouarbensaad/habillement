package com.gpro.consulting.tiers.gc.persitance.factureVente;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.RechercheMulticritereFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ResultatRechecheFactureVenteValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IFactureVentePersistance {

	public String creerFactureVente(FactureVenteValue pFactureVenteValue);
	
	public void supprimerFactureVenteValue(Long pId);
	
	public String modifierFactureVenteValue(FactureVenteValue pFactureVenteValue);
	
	public FactureVenteValue rechercheFactureVenteValueParId(Long pId);
	
	public ResultatRechecheFactureVenteValue rechercherFactureVenteMultiCritere(
			RechercheMulticritereFactureVenteValue pRechercheFactureVenteValueMulitCritere);
	
	public List<FactureVenteValue> getAllByReference(List<String> refBLList);

}
