package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.domaine.IDetailSaisieDomaine;
import com.gpro.consulting.tiers.gpao.service.IDetailSaisieService;

/**
 * @author Ameni Berrich
 *
 */
@Service
@Transactional
public class DetailSaisieServiceImpl implements IDetailSaisieService{

	private static final Logger logger = LoggerFactory.getLogger(DetailSaisieServiceImpl.class);

	@Autowired
	IDetailSaisieDomaine detailSaisieDomaine;
	
	@Override
	public ResultatRechecheDetailSaisieValue rechercherMultiCritere(
			RechercheMulticritereDetailSaisieValue request) {
		//LOGGER.info("rechercheMulticritere: Delegating request to domaine layer.");

		return detailSaisieDomaine.rechercherMultiCritere(request);
	}

	@Override
	public Long getSommeQteProduite(Long operationId, Calendar dateMin, Calendar dateMax) {
		return detailSaisieDomaine.getSommeQteProduite(operationId, dateMin, dateMax);
	}

	@Override
	public Map<Date, Long> productionParOperation(Calendar dateDebut, Calendar dateFin, Long operationId, Long chaineId) {
		return detailSaisieDomaine.productionParOperation(dateDebut, dateFin, operationId, chaineId);
	}

	@Override
	public Map<Long, Long> recapProductionParChaine(Calendar dateDebut, Calendar dateFin, Long operationId) {
		// TODO Auto-generated method stub
		return detailSaisieDomaine.recapProductionParChaine(dateDebut, dateFin, operationId);
	}
	
	@Override
	public Long getSommeQteProduiteOF(Long idOF,Long operationId, Calendar dateMin, Calendar dateMax) {
		return detailSaisieDomaine.getSommeQteProduiteOF(idOF,operationId, dateMin, dateMax);
	}
	
	@Override
	public Long getSommeQteProduiteProduit(Long idProduit, Long operationId, Calendar dateMin, Calendar dateMax) {
		return detailSaisieDomaine.getSommeQteProduiteProduit(idProduit,operationId, dateMin, dateMax);
	}	
	
}
