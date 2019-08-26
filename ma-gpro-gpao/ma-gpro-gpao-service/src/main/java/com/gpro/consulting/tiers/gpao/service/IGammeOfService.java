package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;

/**
 * Gamme OFService interface
 *  
 * @author Wahid Gazzah
 * @since 10/05/2016
 */
public interface IGammeOfService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(GammeOfValue gammeOfValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeOfValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(GammeOfValue gammeOfValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<GammeOfValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrdreFabricationValue> getOrdreFabricationListAvailable();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrdreFabricationValue> getOrdreFabricationListUsed();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeOfValue getGammeOfByOrdreFabricationId(Long ordreFabricationId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeOfValue validateByOrdreFabricationId(Long ordreFabricationId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request, boolean allegee);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeOfValue getByOFId(Long ordreFabricationId);
}
