package com.gpro.consulting.tiers.gpao.service;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue.FicheSuiveuseVue;

/**
 * @author Wahid Gazzah
 * @since 20/05/2016
 */
public interface IFicheSuiveuseService {

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FicheSuiveuseVue getByOrdreFabricationId(Long ordreFabricationId);

}
