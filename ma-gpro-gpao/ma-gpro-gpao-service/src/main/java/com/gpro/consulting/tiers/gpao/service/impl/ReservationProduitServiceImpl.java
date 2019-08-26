package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.vue.MouvementOfVue;
import com.gpro.consulting.tiers.gpao.domaine.IReservationProduitDomaine;
import com.gpro.consulting.tiers.gpao.service.IReservationProduitService;


/**
 * implementation of {@link IReservationProduitService}
 * 
 * @author Wahid Gazzah
 * @since 09/05/2016
 *
 */

@Service
@Transactional
public class ReservationProduitServiceImpl implements IReservationProduitService{

	private static final Logger logger = LoggerFactory.getLogger(ReservationProduitServiceImpl.class);
	
	@Autowired
	IReservationProduitDomaine reservationProduitDomaine;
	
	@Override
	public List<MouvementOfVue> getByOrdreFabricationId(Long ordreFabricationId, Long magasinId) {
		
		//LOGGER.info("Delegating request to Domain layer.");
		
		return reservationProduitDomaine.getByOrdreFabricationId(ordreFabricationId, magasinId);
	}

}
