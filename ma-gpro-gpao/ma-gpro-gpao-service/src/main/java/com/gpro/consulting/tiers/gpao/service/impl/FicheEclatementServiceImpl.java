package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.domaine.IFicheEclatementDomaine;
import com.gpro.consulting.tiers.gpao.service.IFicheEclatementService;

/**
 * implementation of {@link IFicheEclatementService}
 * 
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

@Service
@Transactional
public class FicheEclatementServiceImpl implements IFicheEclatementService{
	
	private static final Logger logger = LoggerFactory.getLogger(FicheEclatementServiceImpl.class);

	@Autowired
	IFicheEclatementDomaine ficheEclatementDomaine;
	
	@Override
	public String create(FicheEclatementValue ficheEclatementValue) {
		
		//LOGGER.info("createMachine: Delegating request to Domaine layer.");
		
		return ficheEclatementDomaine.create(ficheEclatementValue);
	}

	@Override
	public FicheEclatementValue getById(Long id) {
		
		//LOGGER.info("getMachineById: Delegating request {} to Domaine layer.",id);
		
		return ficheEclatementDomaine.getById(id);
	}

	@Override
	public String update(FicheEclatementValue ficheEclatementValue) {
		
		//LOGGER.info("updateMachine: Delegating request to Domaine layer.");
		
		return ficheEclatementDomaine.update(ficheEclatementValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteficheEclatementValue: Delegating request {} to Domaine layer.",id);
		
		ficheEclatementDomaine.delete(id);
	}

	@Override
	public List<FicheEclatementValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return ficheEclatementDomaine.getAll();
	}

	@Override
	public ResultatRechecheFicheEclatementValue rechercherMultiCritere(
			RechercheMulticritereFicheEclatementValue request) {
		
		//LOGGER.info("rechercherMultiCritere: Delegating request to Domaine layer.");
		
		return ficheEclatementDomaine.rechercherMultiCritere(request);
	}

	@Override
	public Set<PaquetValue> getPaquetListByOfIdAndQuantitePaquet(
			Long ordreFabricationId, Long quantitePaquet) {
		
		//LOGGER.info("getPaquetListByOfIdAndQuantitePaquet: Delegating request to Domaine layer.");
		
		return ficheEclatementDomaine.getPaquetListByOfIdAndQuantitePaquet(ordreFabricationId, quantitePaquet);
	}
	@Override
	public String updateAll() {
		
		//LOGGER.info("getPaquetListByOfIdAndQuantitePaquet: Delegating request to Domaine layer.");
		
		return ficheEclatementDomaine.updateAll();
	}
}
