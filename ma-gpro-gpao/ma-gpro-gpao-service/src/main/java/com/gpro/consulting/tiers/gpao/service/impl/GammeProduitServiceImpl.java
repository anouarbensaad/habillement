package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;
import com.gpro.consulting.tiers.gpao.domaine.IGammeProduitDomaine;
import com.gpro.consulting.tiers.gpao.service.IGammeProduitService;
import com.gpro.consulting.tiers.gpao.service.IOperationService;

/**
 * implementation of {@link IOperationService}
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */

@Service
@Transactional
public class GammeProduitServiceImpl implements IGammeProduitService{
	
	private static final Logger logger = LoggerFactory.getLogger(GammeProduitServiceImpl.class);

	@Autowired
	IGammeProduitDomaine gammeProduitDomaine;
	
	@Override
	public String create(GammeProduitValue gammeProduitValue) {
		
		//LOGGER.info("createGammeProduit: Delegating request to Domaine layer.");
		
		return gammeProduitDomaine.create(gammeProduitValue);
	}

	@Override
	public GammeProduitValue getById(Long id) {
		
		//LOGGER.info("getGammeProduitById: Delegating request {} to Domaine layer."+id);
		
		return gammeProduitDomaine.getById(id);
	}

	@Override
	public String update(GammeProduitValue gammeProduitValue) {
		
		//LOGGER.info("updateGammeProduit: Delegating request to Domaine layer.");
		
		return gammeProduitDomaine.update(gammeProduitValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteGammeProduit: Delegating request {} to Domaine layer."+id);
		
		gammeProduitDomaine.delete(id);
	}

	@Override
	public List<GammeProduitValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Domaine layer.");
		
		return gammeProduitDomaine.getAll();
	}

	@Override
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request) {
		
		return gammeProduitDomaine.rechercherMultiCritere(request);
	}

	@Override
	public List<ProduitValue> getProduitListAvailable() {
		
		//LOGGER.info("getProduitListAvailable: Delegating request to Domaine layer.");
		
		return gammeProduitDomaine.getProduitListAvailable();
	}

	@Override
	public List<ProduitValue> getProduitListUsed() {
		
		//LOGGER.info("getProduitListUsed: Delegating request to Domaine layer.");
		
		return gammeProduitDomaine.getProduitListUsed();
	}

	@Override
	public GammeProduitValue getByProduitId(Long produitId) {
		
		//LOGGER.info("getGammeProduitByProduitId: Delegating request {} to Domaine layer."+produitId);
		
		return gammeProduitDomaine.getByProduitId(produitId);
	}

	@Override
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(RechercheMulticritereGammeProduitValue request,
			boolean allegee) {
		// TODO Auto-generated method stub
		return gammeProduitDomaine.rechercherMultiCritere(request, allegee);
	}

}
