package com.gpro.consulting.tiers.gpao.persitance.abc.utility;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.abc.value.ABCArticleDetailEtapeJourValue;
import com.gpro.consulting.tiers.gpao.persitance.abc.entity.ABCArticleDetailEtapeJourEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 03/05/2016
 *
 */

@Component
public class ABCArticleDetailEtapeJourUtility {
	
	public ABCArticleDetailEtapeJourValue toValue(ABCArticleDetailEtapeJourEntity entity){
		
		ABCArticleDetailEtapeJourValue dto = new ABCArticleDetailEtapeJourValue();
		
		dto.setId(entity.getId());
		dto.setQte(entity.getQte());
		dto.setVariationPra(entity.getVariationPra());
		dto.setVariationThe(entity.getVariationThe());
		dto.setDateSaisie(entity.getDateSaisie());
		dto.setRefArticle(entity.getRefArticle());
		dto.setRefCommande(entity.getRefCommande());
		dto.setTaille(entity.getTaille());
		dto.setCouleur(entity.getCouleur());
		dto.setNomPhase(entity.getNomPhase());
		dto.setQteCmd(entity.getQteCmd());
		dto.setLieuExcecution(entity.getLieuExcecution());
		dto.setClientAbrevation(entity.getClientAbrevation());
		dto.setPhaseId(entity.getPhaseId());
		dto.setAbcarticledetailId(entity.getAbcarticledetailId());
		
		//
		dto.setProduitAbrevation(entity.getRefArticle());
		
		return dto;
	}
	
	public ABCArticleDetailEtapeJourEntity toEntity(ABCArticleDetailEtapeJourValue dto){
		
		ABCArticleDetailEtapeJourEntity entity = new ABCArticleDetailEtapeJourEntity();
		
		entity.setId(dto.getId());
		entity.setQte(dto.getQte());
		entity.setVariationPra(dto.getVariationPra());
		entity.setVariationThe(dto.getVariationThe());
		entity.setDateSaisie(dto.getDateSaisie());
		entity.setRefArticle(dto.getRefArticle());
		entity.setRefCommande(dto.getRefCommande());
		entity.setTaille(dto.getTaille());
		entity.setCouleur(dto.getCouleur());
		entity.setNomPhase(dto.getNomPhase());
		entity.setQteCmd(dto.getQteCmd());
		entity.setLieuExcecution(dto.getLieuExcecution());
		entity.setClientAbrevation(dto.getClientAbrevation());
		entity.setPhaseId(dto.getPhaseId());
		entity.setAbcarticledetailId(dto.getAbcarticledetailId());
		entity.setProduitAbrevation(dto.getProduitAbrevation());
		
		return entity;
	}

}
