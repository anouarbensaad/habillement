package com.gpro.consulting.tiers.gpao.service.bonlivraison;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListTraitFaconElementValue;

/**
 * BonLivraison Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IBonLivraisonService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(
			RechercheMulticritereBonLivraisonValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public LivraisonVenteValue getBonLivraisonById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonLivraison(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(
			List<String> refBonLivraisonList, Long factureVenteId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getListBonLivraisonRef();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteVue> getListBonLivraisonRefByClient(Long idClient);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<LivraisonVenteValue> getAll();
	
	//Added on 11/10/2016 by Zeineb Medimagh
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ListTraitFaconElementValue getTraitementFaconElementList(
			List<String> refBonLivraisonList, Long factureVenteId);
	
}
