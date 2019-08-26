package com.gpro.consulting.tiers.gpao.domaine.bonlivraison.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.domaine.bonlivraison.IDetLivraisonVenteDomaine;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.IDetLivraisonVentePersistance;

/**
 * Implementation of {@link IDetLivraisonVenteDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class DetLivraisonVenteDomaineImpl implements IDetLivraisonVenteDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonDomaineImpl.class);
	
	@Autowired
	private IDetLivraisonVentePersistance detLivraisonVentePersistance;
	
	@Autowired
	private IProduitPersistance produitPersistance ;

	
	@Override
	public String create(DetLivraisonVenteValue bonLivraisonValue) {
		
		return detLivraisonVentePersistance.create(bonLivraisonValue);
	}

	@Override
	public DetLivraisonVenteValue getById(Long id) {
		
		return detLivraisonVentePersistance.getById(id);
	}

	@Override
	public String update(DetLivraisonVenteValue bonLivraisonValue) {
		
		return detLivraisonVentePersistance.update(bonLivraisonValue);
	}

	@Override
	public void delete(Long id) {
		
		detLivraisonVentePersistance.delete(id);
	}

	

}
