package com.gpro.consulting.tiers.gc.domaine.livraisonVente;

import java.util.List;

import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ILivraisonVenteDomaine {
	
	public String creerLivraisonVente(LivraisonVenteValue pLivraisonVenteValue);
		
	public void supprimerLivraisonVenteValue(Long pId);
	
	public String modifierLivraisonVenteValue(LivraisonVenteValue pLivraisonVenteValue);
	
	public LivraisonVenteValue rechercheLivraisonVenteValueParId(Long pId);
	
	public ResultatRechecheLivraisonVenteValue rechercherLivraisonVenteMultiCritere(
			RechercheMulticritereLivraisonVenteValue pRechercheLivraisonVenteValueMulitCritere);

	public List<ProduitLivraisonValue> getProduitLivraisonListByRefBonCmdList(List<String> refBonCmdList);

	public List<String> getReferences();
}
