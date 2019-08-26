package com.gpro.consulting.tiers.gpao.persitance.fichecolisage;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;

public interface IColisPersistance {

	

	public ResultatRechecheColisValue rechercherMultiCritere(
			RechercheMulticritereColisValue request);

	public String modifierColis(ColisValue pProduitValue);

	public List<ColisValue> getRouleauFiniListByBarreCodeList(
			List<String> barreCodeList, Long id);

}
