package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;
import java.util.Set;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;

/**
 * Fiche Eclatement Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

public interface IFicheColisageDomaine {
	
	public String create(FicheColisageValue ficheColisageValue);

	public FicheColisageValue getById(Long id);

	public String update(FicheColisageValue ficheColisageValue);

	public void delete(Long id);

	public List<FicheColisageValue> getAll();

	public ResultatRechecheFicheColisageValue rechercherMultiCritere(
			RechercheMulticritereFicheColisageValue request);

	public Set<ColisValue> getPaquetListByOfIdAndQuantiteColis(
			Long ordreFabricationId, Long quantitePaquet);
	
	public Set<ColisValue> getPaquetListByOfId(Long ordreFabricationId);

}
