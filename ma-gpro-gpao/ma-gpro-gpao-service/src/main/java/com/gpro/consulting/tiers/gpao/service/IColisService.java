package com.gpro.consulting.tiers.gpao.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;

/**
 * @author Hamdi etteib
 * @since 06/12/2017
 */
public interface IColisService {
	
	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheColisValue rechercherMultiCritere(
			RechercheMulticritereColisValue request);

	

}
