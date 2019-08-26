package com.gpro.consulting.tiers.gpao.persitance.stockfini.utilities;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.persitance.stockfini.entity.MouvementFiniEntity;

/**
 * The Class StockFiniPersistanceUtilities.
 * 
 * @author Samer
 */

@Component
public class StockFiniPersistanceUtilities {

	private static final Logger logger = LoggerFactory.getLogger(StockFiniPersistanceUtilities.class);
	
	/**
	 * Instanciation du gestionnaire de persistance
	 */
	private static StockFiniPersistanceUtilities instance = new StockFiniPersistanceUtilities();

	/******************************** ToValue *************************************/


	public static MouvementFiniValue toValue(MouvementFiniEntity pMouvementFiniEntite) {
		
		MouvementFiniValue vMouvementFiniValue = new MouvementFiniValue();
		
		vMouvementFiniValue.setId(pMouvementFiniEntite.getId());
		vMouvementFiniValue.setDetailOfId(pMouvementFiniEntite.getDetailOfId());
		vMouvementFiniValue.setNumeroBon(pMouvementFiniEntite.getNumeroBon());
		vMouvementFiniValue.setNumeroOf(pMouvementFiniEntite.getNumeroOf());
		vMouvementFiniValue.setDate(pMouvementFiniEntite.getDate());
		vMouvementFiniValue.setType(pMouvementFiniEntite.getType());
		vMouvementFiniValue.setQuantite(pMouvementFiniEntite.getQuantite());
	

		return vMouvementFiniValue;
	}

	
	public static MouvementFiniEntity toEntity( MouvementFiniValue pMouvementFiniValue) {
		
		MouvementFiniEntity vMouvementFiniEntity = new MouvementFiniEntity();
		
		vMouvementFiniEntity.setId(pMouvementFiniValue.getId());
		vMouvementFiniEntity.setDetailOfId(pMouvementFiniValue.getDetailOfId());
		vMouvementFiniEntity.setNumeroBon(pMouvementFiniValue.getNumeroBon());
		vMouvementFiniEntity.setNumeroOf(pMouvementFiniValue.getNumeroOf());
		vMouvementFiniEntity.setDate(pMouvementFiniValue.getDate());
		vMouvementFiniEntity.setType(pMouvementFiniValue.getType());
		vMouvementFiniEntity.setQuantite(pMouvementFiniValue.getQuantite());
	

		return vMouvementFiniEntity;
	}


	/********* Getter & Setter ********/
	/**
	 * @return the instance
	 */
	public static StockFiniPersistanceUtilities getInstance() {
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */
	public static void setInstance(StockFiniPersistanceUtilities instance) {
		StockFiniPersistanceUtilities.instance = instance;
	}

}
