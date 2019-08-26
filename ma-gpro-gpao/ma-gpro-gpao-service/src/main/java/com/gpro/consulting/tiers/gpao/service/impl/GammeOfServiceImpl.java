package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IGammeOfDomaine;
import com.gpro.consulting.tiers.gpao.service.IGammeOfService;

/**
 * implementation of {@link IGammeOfService}
 * 
 * @author Wahid Gazzah
 * @since10/05/2016
 *
 */

@Service
@Transactional
public class GammeOfServiceImpl implements IGammeOfService{

	private static final Logger logger = LoggerFactory.getLogger(GammeOfServiceImpl.class);

	@Autowired
	IGammeOfDomaine gammeOfDomaine;
	
	@Override
	public String create(GammeOfValue gammeOfValue) {
		
		//LOGGER.info("createGammeOf: Delegating request to Domaine layer.");
		
		return gammeOfDomaine.create(gammeOfValue);
	}

	@Override
	public GammeOfValue getById(Long id) {
		
		//LOGGER.info("getGammeOfById: Delegating request {} to Domaine layer."+id);
		
		return gammeOfDomaine.getById(id);
	}

	@Override
	public String update(GammeOfValue gammeOfValue) {
		
		//LOGGER.info("updateGammeOf: Delegating request to Domaine layer.");
		
		return gammeOfDomaine.update(gammeOfValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteGammeOf: Delegating request {} to Domaine layer."+id);
		
		gammeOfDomaine.delete(id);
	}

	@Override
	public List<GammeOfValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return gammeOfDomaine.getAll();
	}

	@Override
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request) {
		
		return gammeOfDomaine.rechercherMultiCritere(request);
	}
	
	@Override
	public List<OrdreFabricationValue> getOrdreFabricationListAvailable() {
		
		//LOGGER.info("getOrdreFabricationListAvailable: Delegating request to Domaine layer.");
		
		return gammeOfDomaine.getOrdreFabricationListAvailable();
	}

	@Override
	public List<OrdreFabricationValue> getOrdreFabricationListUsed() {
		
		//LOGGER.info("getOrdreFabricationListUsed: Delegating request to Domaine layer.");
		
		return gammeOfDomaine.getOrdreFabricationListUsed();
	}


	@Override
	public GammeOfValue getGammeOfByOrdreFabricationId(Long ordreFabricationId) {
		
		//LOGGER.info("getGammeOfByOrdreFabricationId: Delegating request {} to Domaine layer.",ordreFabricationId);
		
		return gammeOfDomaine.getByOrdreFabricationId(ordreFabricationId);
	}
	
	@Override
	public GammeOfValue validateByOrdreFabricationId(Long ordreFabricationId) {
		
		//LOGGER.info("validateByOrdreFabricationId: Delegating request {} to Domaine layer.",ordreFabricationId);
		
		return gammeOfDomaine.validateByOrdreFabricationId(ordreFabricationId);
	}

	@Override
	public ResultatRechecheGammeOfValue rechercherMultiCritere(RechercheMulticritereGammeOfValue request,
			boolean allegee) {
		return gammeOfDomaine.rechercherMultiCritere(request, allegee);
	}




	@Override
	public GammeOfValue getByOFId(Long ordreFabricationId) {
		
		return gammeOfDomaine.getByOFId(ordreFabricationId);
	}








}
