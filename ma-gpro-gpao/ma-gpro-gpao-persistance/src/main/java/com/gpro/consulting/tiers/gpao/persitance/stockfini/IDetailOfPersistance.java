package com.gpro.consulting.tiers.gpao.persitance.stockfini;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereDetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;


/**
 * @author $Ameni
 *
 */
public interface IDetailOfPersistance {


	public String modifierDetailOf(
			DetailOfValue pDetailOfValue);

	/**
	 * Methode de recherche par ID d'un ordre de fabrication dans a BD
	 * 
	 * @param pDetailOfValue
	 * @return
	 */
	public DetailOfValue rechercheDetailOfParId(
			Long pDetailOfId);

	/**


	 */
	public ResultatMulticritereDetailOfValue rechercherDetailOfMultiCritere(
			RechercheMulticritereDetailOfValue pRechercheOrdreFaricationMulitCritere);


	

}
