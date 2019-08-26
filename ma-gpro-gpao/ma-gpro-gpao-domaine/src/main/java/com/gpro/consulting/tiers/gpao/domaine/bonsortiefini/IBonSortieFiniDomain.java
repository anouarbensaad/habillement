package com.gpro.consulting.tiers.gpao.domaine.bonsortiefini;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;

/**
 * BonSortieFini Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
public interface IBonSortieFiniDomain {

	public List<ColisValue> validateBonSortieFini(List<String> barreCodeList, Long id);
	
	public String createBonSortieFini(BonSortieFiniValue bonSortieFiniValue);

	public BonSortieFiniValue getBonSortieFiniById(Long id);

	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(RechercheMulticritereBonSortieFiniValue request);

	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue);

	public void deleteBonSortieFini(Long id);

	public ListProduitElementValue getProduitElementList(List<String> refBonSortieList, Long livraisonVenteId);

	public List<String> getListBonSortieFiniRef();


	// Added on 03/10/2016, by Zeineb Medimagh
	public ListTraitFaconElementValue getTraitFaconElementList(
			List<String> refBonLivraisonList, Long factureVenteId);

	public List<String> getListBonSortieFaconRef();
}
