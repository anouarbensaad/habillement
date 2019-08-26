package com.gpro.consulting.tiers.gpao.domaine;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.DetailSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;

/**
 * @author Ameni Berrich
 *
 */
public interface IDetailSaisieDomaine {

	public ResultatRechecheDetailSaisieValue rechercherMultiCritere(
			RechercheMulticritereDetailSaisieValue request);
	
	/**
	 * @author Zeineb Medimagh
	 * @since 29/11/2016
	 */
	
	public Set<DetailSaisieElementValue> getListDetailsSaisieElement(RechercheMulticritereDetailSaisieValue request);
	
	/**
	 * @author Zeineb Medimagh
	 * @since 02/12/2016
	 */
	
	public Long getSommeQteProduite(Long operationId, Calendar dateMin, Calendar dateMax);
	
	public Map<Date, Long> productionParOperation(Calendar dateDebut, Calendar dateFin, Long operationId, Long chaineId) ;
	
	public Map<Long, Long> recapProductionParChaine(Calendar dateDebut, Calendar dateFin, Long operationId) ;

	public Long getSommeQteProduiteProduit(Long produitId, Long operationId,
			Calendar dateMin, Calendar dateMax);

	public Long getSommeQteProduiteOF(Long ofId, Long operationId, Calendar dateMin,
			Calendar dateMax);

 
}
