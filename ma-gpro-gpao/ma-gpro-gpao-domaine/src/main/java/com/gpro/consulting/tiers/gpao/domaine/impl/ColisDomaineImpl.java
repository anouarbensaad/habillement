package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.DetailsColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IColisDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IFicheColisageDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IColisPersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IFicheColisagePersistance;

/**
 * implementation of {@link IFicheColisageDomaine}
 * 
 * @author Hamdi Etteieb
 * @since 07/12/2017
 *
 */

@Component
public class ColisDomaineImpl implements IColisDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(ColisDomaineImpl.class);
	
	private static final Long ZERO_L = 0L;
	
	@Autowired
	private IColisPersistance colisPersistance;

	@Override
	public ResultatRechecheColisValue rechercherMultiCritere(
			RechercheMulticritereColisValue request) {
		
		return colisPersistance.rechercherMultiCritere(request);
	}
	
	
		
}
	

