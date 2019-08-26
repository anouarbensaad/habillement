package com.gpro.consulting.tiers.gpao.rest.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gpro.consulting.tiers.gpao.coordination.vue.MouvementOfVue;
import com.gpro.consulting.tiers.gpao.service.IReservationProduitService;


/**
 * @author Wahid Gazzah
 * @since 09/05/2016
 */

@RestController
@RequestMapping("/reservationProduit")
public class ReservationProduitRestImpl {
	
	private static final Logger logger = LoggerFactory.getLogger(ReservationProduitRestImpl.class);
	
	@Autowired
	IReservationProduitService reservationProduitService;
	
	@RequestMapping(value = "/getByOrdreFabricationId", method = RequestMethod.GET, produces = "application/json")
	public List<MouvementOfVue> getByOrdreFabricationId(
			@RequestParam(value="magasinId") Long magasinId,
			@RequestParam(value="ordreFabricationId") Long ordreFabricationId) {
		  
		//LOGGER.info("Delegating request to Service layer magasinId: {}, and ordreFabricationId: {}",magasinId, ordreFabricationId);
		
		return reservationProduitService.getByOrdreFabricationId(ordreFabricationId, magasinId);
	}

}
