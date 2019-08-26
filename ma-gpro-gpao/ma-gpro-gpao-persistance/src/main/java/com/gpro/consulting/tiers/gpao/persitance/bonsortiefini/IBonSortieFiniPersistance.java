package com.gpro.consulting.tiers.gpao.persitance.bonsortiefini;

import java.util.List;

/**
 * BonSortieFini Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
public interface IBonSortieFiniPersistance {

	public String createBonSortieFini(com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue request);

	public com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue getBonSortieFiniById(Long id);

	public com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ResultatRechecheBonSortieFiniValue rechercherMultiCritere(com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue request);

	public String updateBonSortieFini(com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue bonSortieFiniValue);

	public void deleteBonSortieFini(Long id);

	public List<com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue> getListByBonSortieList(List<String> refBonSortieList);

	public List<com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue> getAll();

	
	

	public List<String> listerReferenceFacon();

	public List<String> listerReferenceFini();
}
