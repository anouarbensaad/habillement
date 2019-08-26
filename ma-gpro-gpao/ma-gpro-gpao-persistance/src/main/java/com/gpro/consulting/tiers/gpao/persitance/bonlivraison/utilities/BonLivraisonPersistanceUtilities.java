package com.gpro.consulting.tiers.gpao.persitance.bonlivraison.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteFnReportingVue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.entitie.DetLivraisonVenteEntity;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.entitie.LivraisonVenteEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonLivraisonPersistanceUtilities {
	
	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonPersistanceUtilities.class);
	
	public LivraisonVenteVue toVue(LivraisonVenteEntity entity) {
		
		LivraisonVenteVue dto = new LivraisonVenteVue();
		dto.setReferenceBL(entity.getReference());
		
		return dto;
	}
	
	public LivraisonVenteFnReportingVue toFnReportingVue(LivraisonVenteEntity entity) {
		
		LivraisonVenteFnReportingVue dto = new LivraisonVenteFnReportingVue();
		dto.setClientId(entity.getPartieIntId());
		// TODO: calcule du chiffre Affaire dans le domaine de LivVente
		return dto;
	}
	
	public LivraisonVenteValue toValue(LivraisonVenteEntity entity) {
		
		LivraisonVenteValue dto = new LivraisonVenteValue();
		
		if(entity.getId()!=null){
			dto.setId(entity.getId());
		}
		
		dto.setReference(entity.getReference());
		dto.setDate(entity.getDate());
		dto.setMontantHTaxe(entity.getMontantHTaxe());
		dto.setMontantTTC(entity.getMontantTTC());
		dto.setMontantTaxe(entity.getMontantTaxe());
		dto.setMontantRemise(entity.getMontantRemise());
		dto.setObservations(entity.getObservations());
		dto.setInfoSortie(entity.getInfoSortie());
		dto.setPartieIntId(entity.getPartieIntId());
		//dto.setBlSuppression(entity.isBlSuppression());
		dto.setDateSuppression(entity.getDateSuppression());
		dto.setDateCreation(entity.getDateCreation());
		dto.setDateModification(entity.getDateModification());
		dto.setVersion(entity.getVersion());
		dto.setEtat(entity.getEtat());
		dto.setTransporteur(entity.getTransporteur());
		dto.setMetrageTotal(entity.getMetrageTotal());
		dto.setNatureLivraison(entity.getNatureLivraison().trim());
		
	    dto.setPoidsBrut(entity.getPoidsBrut());
		dto.setPoidsNet(entity.getPoidsNet());
		
		dto.setTotalColis(entity.getTotalColis());
		dto.setTotalPalette(entity.getTotalPalette());
		
		dto.setTotalColisReel(entity.getTotalColisReel());
		
		
		if(entity.getListDetLivraisonVente() != null){
	    	List<DetLivraisonVenteValue> list = new ArrayList <DetLivraisonVenteValue>();
		     for (DetLivraisonVenteEntity detLivraisonVenteEntity : entity.getListDetLivraisonVente()) {
		    	 DetLivraisonVenteValue detLivraisonVenteValue = toValue(detLivraisonVenteEntity);
		    	 list.add(detLivraisonVenteValue);
		    }
		     dto.setListDetLivraisonVente(list);
		}
	    
	
		
		return dto;
	}

	public LivraisonVenteEntity toEntity(LivraisonVenteValue dto) {
		
		LivraisonVenteEntity entity = new LivraisonVenteEntity();
		
		entity.setId(dto.getId());
		entity.setReference(dto.getReference());
		entity.setDate(dto.getDate());
		entity.setMontantHTaxe(dto.getMontantHTaxe());
		entity.setMontantTTC(dto.getMontantTTC());
		entity.setMontantTaxe(dto.getMontantTaxe());
		entity.setMontantRemise(dto.getMontantRemise());
		entity.setObservations(dto.getObservations());
		entity.setInfoSortie(dto.getInfoSortie());
		entity.setPartieIntId(dto.getPartieIntId());
		//entity.setBlSuppression(dto.isBlSuppression());
		entity.setDateSuppression(dto.getDateSuppression());
		entity.setDateCreation(dto.getDateCreation());
		entity.setDateModification(dto.getDateModification());
		entity.setVersion(dto.getVersion());
		entity.setEtat(dto.getEtat());
		entity.setTransporteur(dto.getTransporteur());
		entity.setMetrageTotal(dto.getMetrageTotal());
		entity.setNatureLivraison(dto.getNatureLivraison().trim());
		
		entity.setPoidsBrut(dto.getPoidsBrut());
		entity.setPoidsNet(dto.getPoidsNet());
		
		entity.setTotalColis(dto.getTotalColis());
		entity.setTotalPalette(dto.getTotalPalette());
		
		entity.setTotalColisReel(dto.getTotalColisReel());
	
		
		
		
		if(dto.getListDetLivraisonVente() != null){
		     Set<DetLivraisonVenteEntity> list = new HashSet <DetLivraisonVenteEntity>();
		     for (DetLivraisonVenteValue detLivraisonVenteValue : dto.getListDetLivraisonVente()) {
		    	 DetLivraisonVenteEntity detLivraisonVenteEntity = toEntity(detLivraisonVenteValue);
		    	 detLivraisonVenteEntity.setLivraisonVente(entity);
		    	 list.add(detLivraisonVenteEntity);
		    }
		     entity.setListDetLivraisonVente(list);
		}
	    
	
		return entity;
	}

	public DetLivraisonVenteEntity toEntity(DetLivraisonVenteValue dto) {
		
		DetLivraisonVenteEntity entity = new DetLivraisonVenteEntity();
		
		entity.setId(dto.getId());
		entity.setProduitId(dto.getProduitId());
		entity.setQuantite(dto.getQuantite());
		entity.setUnite(dto.getUnite());
		entity.setNombreColis(dto.getNombreColis());
		entity.setRemise(dto.getRemise());
		entity.setChoix(dto.getChoix());
		entity.setTraitementFaconId(dto.getTraitementFaconId());
		entity.setPrixUnitaireHT(dto.getPrixUnitaireHT());
		entity.setPrixTotalHT(dto.getPrixTotalHT());
		entity.setFicheId(dto.getFicheId());
		
		entity.setNumeroOF(dto.getNumeroOF());
		entity.setOfId(dto.getOfId());
		entity.setQuantiteColis(dto.getQuantiteColis());
		
		
		
		entity.setProduitReference(dto.getProduitReference());
		
		entity.setProduitDesignation(dto.getProduitDesignation());
		
		entity.setPalette(dto.getPalette());
		
		entity.setComposition(dto.getComposition());
		entity.setCodeDouane(dto.getCodeDouane());
		
		entity.setReferenceTissu1(dto.getReferenceTissu1());
		entity.setReferenceTissu2(dto.getReferenceTissu2());
		entity.setReferenceTissu3(dto.getReferenceTissu3());
		entity.setReferenceTissu4(dto.getReferenceTissu4());
		
		entity.setDesignationTissu1(dto.getDesignationTissu1());
		entity.setDesignationTissu2(dto.getDesignationTissu2());
		entity.setDesignationTissu3(dto.getDesignationTissu3());
		entity.setDesignationTissu4(dto.getDesignationTissu4());
		
		entity.setEmploiTissu1(dto.getEmploiTissu1());
		entity.setEmploiTissu2(dto.getEmploiTissu2());
		entity.setEmploiTissu3(dto.getEmploiTissu3());
		entity.setEmploiTissu4(dto.getEmploiTissu4());
		
		entity.setTypeTissu1(dto.getTypeTissu1());
		entity.setTypeTissu2(dto.getTypeTissu2());
		entity.setTypeTissu3(dto.getTypeTissu3());
		entity.setTypeTissu4(dto.getTypeTissu4());
		
		entity.setPrixTissu1(dto.getPrixTissu1());
		entity.setPrixTissu2(dto.getPrixTissu2());
		entity.setPrixTissu3(dto.getPrixTissu3());
		entity.setPrixTissu4(dto.getPrixTissu4());
		
		
		
		
		if(dto.getLivraisonVenteId() != null){
			LivraisonVenteEntity livraisonVenteEntity = new LivraisonVenteEntity();
			livraisonVenteEntity.setId(dto.getLivraisonVenteId());
			entity.setLivraisonVente(livraisonVenteEntity);
		}
		
		return entity;
	}

	public DetLivraisonVenteValue toValue(DetLivraisonVenteEntity entity) {
		
		DetLivraisonVenteValue dto = new DetLivraisonVenteValue();
		
		dto.setId(entity.getId());
		dto.setProduitId(entity.getProduitId());
		dto.setQuantite(entity.getQuantite());
		dto.setUnite(entity.getUnite());
		dto.setNombreColis(entity.getNombreColis());
		dto.setRemise(entity.getRemise());
		dto.setChoix(entity.getChoix());
		dto.setTraitementFaconId(entity.getTraitementFaconId());
		dto.setPrixUnitaireHT(entity.getPrixUnitaireHT());
		dto.setPrixTotalHT(entity.getPrixTotalHT());
		dto.setFicheId(entity.getFicheId());
		
		dto.setNumeroOF(entity.getNumeroOF());
		dto.setOfId(entity.getOfId());
		dto.setQuantiteColis(entity.getQuantiteColis());
		
		
		
		dto.setProduitReference(entity.getProduitReference());
		
		dto.setProduitDesignation(entity.getProduitDesignation());
			
		dto.setPalette(entity.getPalette());
		
		dto.setComposition(entity.getComposition());	
		dto.setCodeDouane(entity.getCodeDouane());
		
		dto.setReferenceTissu1(entity.getReferenceTissu1());
		dto.setReferenceTissu2(entity.getReferenceTissu2());
		dto.setReferenceTissu3(entity.getReferenceTissu3());
		dto.setReferenceTissu4(entity.getReferenceTissu4());
		
		dto.setDesignationTissu1(entity.getDesignationTissu1());
		dto.setDesignationTissu2(entity.getDesignationTissu2());
		dto.setDesignationTissu3(entity.getDesignationTissu3());
		dto.setDesignationTissu4(entity.getDesignationTissu4());
		
		dto.setEmploiTissu1(entity.getEmploiTissu1());
		dto.setEmploiTissu2(entity.getEmploiTissu2());
		dto.setEmploiTissu3(entity.getEmploiTissu3());
		dto.setEmploiTissu4(entity.getEmploiTissu4());
		
		dto.setPrixTissu1(entity.getPrixTissu1());
		dto.setPrixTissu2(entity.getPrixTissu2());
		dto.setPrixTissu3(entity.getPrixTissu3());
		dto.setPrixTissu4(entity.getPrixTissu4());
		
		
		dto.setTypeTissu1(entity.getTypeTissu1());
		dto.setTypeTissu2(entity.getTypeTissu2());
		dto.setTypeTissu3(entity.getTypeTissu3());
		dto.setTypeTissu4(entity.getTypeTissu4());
		
		
		
		
		if(entity.getLivraisonVente() != null){
			dto.setLivraisonVenteId(entity.getLivraisonVente().getId());
		}
		
		return dto;
	}


	

	

	public List<LivraisonVenteValue> listLivraisonVenteEntitytoValue(List<LivraisonVenteEntity> listEntity) {
		
		List<LivraisonVenteValue> list = new ArrayList<LivraisonVenteValue>();
		
		for(LivraisonVenteEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	
	

}
