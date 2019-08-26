package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.vue.MouvementOfVue;

/**
 * Besoin Article Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 09/05/2016
 *
 */
public interface IReservationProduitDomaine {

	List<MouvementOfVue> getByOrdreFabricationId(Long ordreFabricationId, Long magasinId);

}
