package com.gpro.consulting.tiers.gpao.service.bonsortiefini.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.domaine.bonsortiefini.IBonSortieFiniDomain;
import com.gpro.consulting.tiers.gpao.service.bonsortiefini.IBonSortieFiniService;

/**
 * Implementation of {@link IBonSortieFiniService}
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Service
@Transactional
public class BonSortieFiniServiceImpl implements IBonSortieFiniService{
	
	private static final Logger logger = LoggerFactory.getLogger(BonSortieFiniServiceImpl.class);
	
	@Autowired
	private IBonSortieFiniDomain bonSortieFiniDomain;

	@Override
	public String createBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {
		
		logger.info("createBonSortieFini: Delegating request to Domain layer.");
		
		return bonSortieFiniDomain.createBonSortieFini(bonSortieFiniValue);
	}

	@Override
	public BonSortieFiniValue getBonSortieFiniById(Long id) {
		
		logger.info("getBonSortieFiniById: Delegating request to Domain layer to retrieve BonSortieFiniValue with id {}"+id);

		return bonSortieFiniDomain.getBonSortieFiniById(id);
	}

	@Override
	public List<ColisValue> validateBonSortieFini(List<String> barreCodeList, Long id) {

		return bonSortieFiniDomain.validateBonSortieFini(barreCodeList, id);
	}

	@Override
	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(
			RechercheMulticritereBonSortieFiniValue request) {
		
		return bonSortieFiniDomain.rechercherMultiCritere(request);
	}

	@Override
	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {

		return bonSortieFiniDomain.updateBonSortieFini(bonSortieFiniValue);
	}

	@Override
	public void deleteBonSortieFini(Long id) {
		
		bonSortieFiniDomain.deleteBonSortieFini(id);
	}

	@Override
	public ListProduitElementValue getProduitElementList(
			List<String> refBonSortieList, Long livraisonVenteId) {
		
		return bonSortieFiniDomain.getProduitElementList(refBonSortieList, livraisonVenteId);
	}

	@Override
	public List<String> getListBonSortieFiniRef() {

		return bonSortieFiniDomain.getListBonSortieFiniRef();
	}

	@Override
	public ListTraitFaconElementValue getTraitFaconElementList(List<String> refBonLivraisonList, Long factureVenteId) {
		return bonSortieFiniDomain.getTraitFaconElementList(refBonLivraisonList, factureVenteId);
	}
// Added By Ghazi on 23102017
	
	@Override
	public List<String> getListBonSortieFaconRef() {

		return bonSortieFiniDomain.getListBonSortieFaconRef();
	}
	
}
