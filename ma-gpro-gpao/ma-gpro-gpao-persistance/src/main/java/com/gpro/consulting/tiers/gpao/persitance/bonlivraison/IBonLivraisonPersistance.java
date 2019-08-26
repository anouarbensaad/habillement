package com.gpro.consulting.tiers.gpao.persitance.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteVue;

/**
 * BonLivraison Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonLivraisonPersistance {

	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request);

	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public LivraisonVenteValue getBonLivraisonById(Long id);

	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public void deleteBonLivraison(Long id);
	
	public List<LivraisonVenteValue> getAll();
	
	public List<LivraisonVenteValue> getProduitElementList(List<String> refBonLivraisonList);

	public LivraisonVenteValue getByReference(String reference);

	public List<LivraisonVenteValue> getByClientId(Long clientId);
	
	public List<LivraisonVenteVue> getReferenceBLByClientId(Long clientId);



	List<LivraisonVenteValue> getTraitementFaconElementList(List<String> refBonLivraisonList);
	
	

	public boolean existeBS(String referenceBL);
}
