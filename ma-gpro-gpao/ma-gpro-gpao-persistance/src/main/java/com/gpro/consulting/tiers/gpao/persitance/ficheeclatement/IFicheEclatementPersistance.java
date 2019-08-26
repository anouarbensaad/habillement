package com.gpro.consulting.tiers.gpao.persitance.ficheeclatement;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;

/**
 * Fiche Eclatement Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */
public interface IFicheEclatementPersistance {

	public String create(FicheEclatementValue ficheEclatementValue);

	public FicheEclatementValue getById(Long id);

	public String update(FicheEclatementValue ficheEclatementValue);

	public void delete(Long id);

	public List<FicheEclatementValue> getAll();

	public ResultatRechecheFicheEclatementValue rechercherMultiCritere(
			RechercheMulticritereFicheEclatementValue request);

	public List<FicheEclatementValue> getByOrdreFabricationId(Long ordreFabricationId);
}
