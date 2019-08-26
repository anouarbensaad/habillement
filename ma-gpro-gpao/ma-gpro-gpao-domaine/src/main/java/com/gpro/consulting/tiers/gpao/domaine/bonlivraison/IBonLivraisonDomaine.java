package com.gpro.consulting.tiers.gpao.domaine.bonlivraison;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListTraitFaconElementValue;

/**
 * BonLivraison Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonLivraisonDomaine {

	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request);

	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public LivraisonVenteValue getBonLivraisonById(Long id);

	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue);

	public void deleteBonLivraison(Long id);

	public ListProduitElementValue getProduitElementList(
			List<String> refBonLivraisonList, Long factureVenteId);

	public List<String> getListBonLivraisonRef();

	public LivraisonVenteValue getByReference(String refBL);
	
	public List<LivraisonVenteVue> getListBonLivraisonRefByClient(Long idClient);
	
	
	
	public List<LivraisonVenteValue> getAll();
	
}