package com.gpro.consulting.tiers.gpao.service.bonlivraison.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.domaine.bonlivraison.IDetLivraisonVenteDomaine;
import com.gpro.consulting.tiers.gpao.service.bonlivraison.IDetLivraisonVenteService;

/**
 * implementation of {@link IDetLivraisonVenteService}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
@Service
@Transactional
public class DetLivraisonVenteServiceImpl implements IDetLivraisonVenteService{
	
	private static final Logger logger = LoggerFactory.getLogger(DetLivraisonVenteServiceImpl.class);

	@Autowired
	private IDetLivraisonVenteDomaine detLivraisonVenteDomaine;

	@Override
	public String create(DetLivraisonVenteValue detLivraisonVenteValue) {
		
		return detLivraisonVenteDomaine.create(detLivraisonVenteValue);
	}

	@Override
	public DetLivraisonVenteValue getById(Long id) {
		
		return detLivraisonVenteDomaine.getById(id);
	}

	@Override
	public String update(DetLivraisonVenteValue detLivraisonVenteValue) {
		
		return detLivraisonVenteDomaine.update(detLivraisonVenteValue);
	}

	@Override
	public void delete(Long id) {
		
		detLivraisonVenteDomaine.delete(id);
	}

}
