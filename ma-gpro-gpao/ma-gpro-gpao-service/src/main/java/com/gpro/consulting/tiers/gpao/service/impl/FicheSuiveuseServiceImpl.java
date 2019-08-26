package com.gpro.consulting.tiers.gpao.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue.FicheSuiveuseVue;
import com.gpro.consulting.tiers.gpao.domaine.IFicheSuiveuseDomaine;
import com.gpro.consulting.tiers.gpao.service.IFicheSuiveuseService;

/**
 * implementation of {@link IFicheSuiveuseService}
 * 
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */

@Service
@Transactional
public class FicheSuiveuseServiceImpl implements IFicheSuiveuseService{

	private static final Logger logger = LoggerFactory.getLogger(GammeOfServiceImpl.class);

	@Autowired
	private IFicheSuiveuseDomaine ficheSuiveuseDomaine;

	@Override
	public FicheSuiveuseVue getByOrdreFabricationId(Long ordreFabricationId) {
		
		//LOGGER.info("getFicheSuiveuse: Delegating request to Domaine layer.");
		
		return ficheSuiveuseDomaine.getByOrdreFabricationId(ordreFabricationId);
	}
}
