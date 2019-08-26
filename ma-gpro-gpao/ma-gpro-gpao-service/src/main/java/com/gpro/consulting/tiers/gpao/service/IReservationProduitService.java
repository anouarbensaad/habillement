package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.vue.MouvementOfVue;

/**
 * @author Wahid Gazzah
 * @since 09/05/2016
 */
public interface IReservationProduitService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<MouvementOfVue> getByOrdreFabricationId(Long ordreFabricationId, Long magasinId);

}
