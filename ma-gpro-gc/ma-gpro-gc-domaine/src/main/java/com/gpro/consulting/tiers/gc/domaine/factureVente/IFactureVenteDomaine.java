package com.gpro.consulting.tiers.gc.domaine.factureVente;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.RechercheMulticritereFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ResultatRechecheFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue.LivraisonVenteVue;

/**
 * @author Ameni Berrich
 *
 */
public interface IFactureVenteDomaine {

	public String creerFactureVente(FactureVenteValue pFactureVenteValue);
	
	public void supprimerFactureVenteValue(Long pId);
	
	public String modifierFactureVenteValue(FactureVenteValue pFactureVenteValue);
	
	public FactureVenteValue rechercheFactureVenteValueParId(Long pId);
	
	public ResultatRechecheFactureVenteValue rechercherFactureVenteMultiCritere(
			RechercheMulticritereFactureVenteValue pRechercheFactureVenteValueMulitCritere);
	
	public LivraisonVenteVue getProduitFactureByReferenceBLList(List<String> refBLList);

}
