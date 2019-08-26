package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;
import java.util.Set;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;

/**
 * Fiche Eclatement Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

public interface IFicheEclatementDomaine {
	
	public String create(FicheEclatementValue ficheEclatementValue);

	public FicheEclatementValue getById(Long id);

	public String update(FicheEclatementValue ficheEclatementValue);

	public void delete(Long id);

	public List<FicheEclatementValue> getAll();

	public ResultatRechecheFicheEclatementValue rechercherMultiCritere(
			RechercheMulticritereFicheEclatementValue request);

	public Set<PaquetValue> getPaquetListByOfIdAndQuantitePaquet(
			Long ordreFabricationId, Long quantitePaquet);
	
	public Set<PaquetValue> getPaquetListByOfId(Long ordreFabricationId);

	public String updateAll();

}
