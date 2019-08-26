package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;
import com.gpro.consulting.tiers.gpao.domaine.IFicheColisageDomaine;

import com.gpro.consulting.tiers.gpao.service.IFicheColisageService;

/**
 * implementation of {@link IFicheColisageService}
 * 
 * @author Hamdi etteieb
 * @since 06/12/2017
 *
 */

@Service
@Transactional
public class FicheColisageServiceImpl implements IFicheColisageService{
	
	private static final Logger logger = LoggerFactory.getLogger(FicheColisageServiceImpl.class);

	@Autowired
	IFicheColisageDomaine ficheColisageDomaine;
	

	
	@Override
	public String create(FicheColisageValue ficheColisageValue) {
		
		//LOGGER.info("createMachine: Delegating request to Domaine layer.");
		
		return ficheColisageDomaine.create(ficheColisageValue);
	}

	@Override
	public FicheColisageValue getById(Long id) {
		
		//LOGGER.info("getMachineById: Delegating request {} to Domaine layer.",id);
		
		return ficheColisageDomaine.getById(id);
	}

	@Override
	public String update(FicheColisageValue ficheColisageValue) {
		
		//LOGGER.info("updateMachine: Delegating request to Domaine layer.");

		return ficheColisageDomaine.update(ficheColisageValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteficheColisageValue: Delegating request {} to Domaine layer.",id);
		
		ficheColisageDomaine.delete(id);
	}

	@Override
	public List<FicheColisageValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return ficheColisageDomaine.getAll();
	}

	@Override
	public ResultatRechecheFicheColisageValue rechercherMultiCritere(
			RechercheMulticritereFicheColisageValue request) {
		
		//LOGGER.info("rechercherMultiCritere: Delegating request to Domaine layer.");
		
		return ficheColisageDomaine.rechercherMultiCritere(request);
	}

	@Override
	public Set<ColisValue> getPaquetListByOfIdAndQuantiteColis(
			Long ordreFabricationId, Long quantitePaquet) {
		
		//LOGGER.info("getPaquetListByOfIdAndQuantitePaquet: Delegating request to Domaine layer.");
		
		return ficheColisageDomaine.getPaquetListByOfIdAndQuantiteColis(ordreFabricationId, quantitePaquet);
	}
	
}
