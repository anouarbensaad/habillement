package com.gpro.consulting.tiers.gs.persitance.charts.utilities;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gs.coordination.chart.variation.value.VariationChartValue;
import com.gpro.consulting.tiers.gs.persitance.entite.MouvementEntite;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 26/04/2016
 *
 */

@Component
public class VariationChartPersistanceUtilities {

	public VariationChartValue toValue(MouvementEntite entity) {

		VariationChartValue dto = new VariationChartValue();
		
		dto.setTypeMouvement(entity.getType());
		dto.setQuantite(entity.getQuantite());
		dto.setQuantiteReelle(entity.getQuantiteReelle());
		dto.setPoids(entity.getPoids());
		dto.setPoidsReel(entity.getPoidsReel());
		dto.setCones(entity.getCones());
		dto.setConesReel(entity.getConesReel());
		dto.setFincones(entity.getFinCones());
		dto.setFinconesReel(entity.getFinConesReel());
		dto.setNbRouleaux(entity.getNbRouleaux());
		dto.setNbRouleauxReel(entity.getNbRouleauxReel());
		
		if(entity.getBonMouvement() != null){
			dto.setDate(entity.getBonMouvement().getDate());
		}
		
		return dto;
	}

}
