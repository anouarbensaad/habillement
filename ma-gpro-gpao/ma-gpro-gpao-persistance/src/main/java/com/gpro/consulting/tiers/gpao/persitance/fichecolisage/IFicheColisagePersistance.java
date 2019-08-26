package com.gpro.consulting.tiers.gpao.persitance.fichecolisage;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;

public interface IFicheColisagePersistance {

	public String create(FicheColisageValue ficheColisageValue);

	public FicheColisageValue getById(Long id);

	public String update(FicheColisageValue ficheColisageValue);

	public void delete(Long id);

	public List<FicheColisageValue> getAll();

	public ResultatRechecheFicheColisageValue rechercherMultiCritere(
			RechercheMulticritereFicheColisageValue request);

	public List<FicheColisageValue> getByOrdreFabricationId(Long ordreFabricationId);
}
