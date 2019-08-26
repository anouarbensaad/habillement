package com.gpro.consulting.tiers.gpao.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;

/**
 * @author Hamdi etteib
 * @since 06/12/2017
 */
public interface IFicheColisageService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(FicheColisageValue FicheColisageValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheColisageValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(FicheColisageValue FicheColisageValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<FicheColisageValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFicheColisageValue rechercherMultiCritere(
			RechercheMulticritereFicheColisageValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Set<ColisValue> getPaquetListByOfIdAndQuantiteColis(
			Long ordreFabricationId, Long quantitePaquet);

}
