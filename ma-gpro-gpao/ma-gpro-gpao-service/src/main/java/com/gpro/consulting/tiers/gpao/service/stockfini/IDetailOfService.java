package com.gpro.consulting.tiers.gpao.service.stockfini;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;

/**
 * @author $Ameni
 *
 */
public interface IDetailOfService {


	/**
	 * Methode de Modification d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return Id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierDetailOf(
			DetailOfValue pDetailOfValue);

	/**
	 * Methode de recherche par ID d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public DetailOfValue rechercheDetailOfParId(
			Long pOrdreFabricationId);
	
	

	/**
	 * Méthode de recherche des ordres par critères
	 * 
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatMulticritereDetailOfValue rechercherDetailOfMultiCritere(
			RechercheMulticritereDetailOfValue pRechercheDetailOfMulitCritere);

	
}
