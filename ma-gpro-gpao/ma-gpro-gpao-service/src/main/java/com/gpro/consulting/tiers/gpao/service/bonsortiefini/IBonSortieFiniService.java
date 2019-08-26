package com.gpro.consulting.tiers.gpao.service.bonsortiefini;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;

/**
 * BonSortieFini Service Interface
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
public interface IBonSortieFiniService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<ColisValue> validateBonSortieFini(List<String> barreCodeList, Long id);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createBonSortieFini(BonSortieFiniValue bonSortieFiniValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public BonSortieFiniValue getBonSortieFiniById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(RechercheMulticritereBonSortieFiniValue request);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteBonSortieFini(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ListProduitElementValue getProduitElementList(List<String> refBonSortieList, Long livraisonVenteId);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<String> getListBonSortieFiniRef();

	// Added on 03/10/2016, by Zeineb Medimagh
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ListTraitFaconElementValue getTraitFaconElementList(
			List<String> refBonLivraisonList, Long factureVenteId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getListBonSortieFaconRef();
}
