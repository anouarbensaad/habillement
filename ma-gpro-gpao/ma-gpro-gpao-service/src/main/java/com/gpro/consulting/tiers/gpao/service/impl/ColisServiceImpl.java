package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;
import com.gpro.consulting.tiers.gpao.domaine.IColisDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IFicheColisageDomaine;
import com.gpro.consulting.tiers.gpao.service.IColisService;
import com.gpro.consulting.tiers.gpao.service.IFicheColisageService;

/**
 * implementation of {@link IFicheColisageService}
 * 
 * @author Hamdi etteieb
 * @since 06/12/2017
 *
 */

@Service
@Transactional
public class ColisServiceImpl implements IColisService{
	
	private static final Logger logger = LoggerFactory.getLogger(ColisServiceImpl.class);

	@Autowired
	IColisDomaine colisageDomaine;

	@Override
	public ResultatRechecheColisValue rechercherMultiCritere(
			RechercheMulticritereColisValue request) {
		// TODO Auto-generated method stub
		return colisageDomaine.rechercherMultiCritere(request);
	}
	
		
}
