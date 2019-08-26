package com.gpro.consulting.tiers.gpao.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;

/**
 * @author Wahid Gazzah
 * @since 16/05/2016
 */
public interface IFicheEclatementService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(FicheEclatementValue ficheEclatementValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheEclatementValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(FicheEclatementValue ficheEclatementValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<FicheEclatementValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFicheEclatementValue rechercherMultiCritere(
			RechercheMulticritereFicheEclatementValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Set<PaquetValue> getPaquetListByOfIdAndQuantitePaquet(
			Long ordreFabricationId, Long quantitePaquet);
	@Transactional(readOnly = false, rollbackFor = Exception.class)

	public String updateAll();

}
