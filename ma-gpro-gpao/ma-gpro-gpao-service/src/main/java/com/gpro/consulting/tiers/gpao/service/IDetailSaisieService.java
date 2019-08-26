package com.gpro.consulting.tiers.gpao.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IDetailSaisieService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheDetailSaisieValue rechercherMultiCritere(
			RechercheMulticritereDetailSaisieValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Long getSommeQteProduite(Long operationId, Calendar dateMin, Calendar dateMax);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Map<Date, Long> productionParOperation(Calendar dateDebut, Calendar dateFin, Long operationId, Long chaineId) ;
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Map<Long, Long> recapProductionParChaine(Calendar dateDebut, Calendar dateFin, Long operationId) ;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Long getSommeQteProduiteOF(Long idOF, Long operationId, Calendar dateMin,
			Calendar dateMax);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Long getSommeQteProduiteProduit(Long idProduit, Long operationId,
			Calendar dateMin, Calendar dateMax);

}
