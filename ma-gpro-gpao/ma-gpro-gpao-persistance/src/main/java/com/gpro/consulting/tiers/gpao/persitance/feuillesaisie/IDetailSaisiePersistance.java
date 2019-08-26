package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.DetailSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;

public interface IDetailSaisiePersistance {

	public ResultatRechecheDetailSaisieValue rechercherMultiCritere(
			RechercheMulticritereDetailSaisieValue request);

	/**
	 * @author Zeineb Medimagh
	 * @param request
	 * @return
	 */
	public List<DetailSaisieElementValue> getListDetailsSaisieAllegee(RechercheMulticritereDetailSaisieValue request);
	
	/**
	 * @author Zeineb Medimagh
	 * @param operationId
	 * @param dateMin
	 * @param dateMax
	 * @return
	 */
	public List<DetailSaisieElementValue> rechercherMultiCritere(Long elementGammeOfId, Calendar dateMin, Calendar dateMax);
	
	public Long getSommeQteProduite(Long elementGammeOfId, Calendar dateMin, Calendar dateMax);

	public Long getSommeQteProduiteForOperationOF(Long ofId, Long elementGammeOfId,
			Calendar dateMin, Calendar dateMax);

	public Long getSommeQteProduiteForOpratationProduit(Long produitId,
			Long elementGammeOfId, Calendar dateMin, Calendar dateMax);

	
	
	/**
	 * @author Ghazi ATROUSSI
	 * @param request
	 * @return
	 */
	
	public List<Long> rechercherMultiCritereOF(RechercheMulticritereDetailSaisieValue request);

	
	public boolean codeExistence(String codeBarre);
	
	
	

}
