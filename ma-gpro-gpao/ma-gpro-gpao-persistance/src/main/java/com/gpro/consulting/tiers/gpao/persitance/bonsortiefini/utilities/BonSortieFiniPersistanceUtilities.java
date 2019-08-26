package com.gpro.consulting.tiers.gpao.persitance.bonsortiefini.utilities;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.persitance.bonsortiefini.entity.BonSortieFiniEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.ColisEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.FicheColisageEntity;

/**
 * Mapping Class from DTO to Entity, and from Entity to DTO
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Component
public class BonSortieFiniPersistanceUtilities {

	
	public BonSortieFiniEntity toEntity(BonSortieFiniValue request) {
		
		BonSortieFiniEntity bonSortieFiniEntity = new BonSortieFiniEntity();
		
		if(request.getId() != null){
			bonSortieFiniEntity.setId(request.getId());
		}
		bonSortieFiniEntity.setDateSortie(request.getDateSortie());
		bonSortieFiniEntity.setMetrageTotal(request.getMetrageTotal());
		bonSortieFiniEntity.setNbColis(request.getNbColis());
		bonSortieFiniEntity.setObservation(request.getObservation());
		bonSortieFiniEntity.setPartieInt(request.getPartieInt());
		bonSortieFiniEntity.setReference(request.getReference());
		bonSortieFiniEntity.setType(request.getType());
		bonSortieFiniEntity.setPoidsEcru(request.getPoidsEcru());
		bonSortieFiniEntity.setPoidsFini(request.getPoidsFini());
		
		bonSortieFiniEntity.setBlExport(request.getBlExport());
		bonSortieFiniEntity.setFini(request.getFini());
		
		bonSortieFiniEntity.setReferenceBon(request.getReferenceBon());
		bonSortieFiniEntity.setSpecial(request.getSpecial());
		
		
		
		// TODO Liaisonssssss ///
		//System.out.println("##### PERSIST ####  request.getListeRouleauFini()  :  "+request.getListeRouleauFini().size());
	    if(request.getListeRouleauFini()!= null){
	    	
		     Set <ColisEntity> listeRouleauFini = new HashSet<ColisEntity>();
		     
		     for (ColisValue rouleauFiniValue : request.getListeRouleauFini()) {
		    	 ColisEntity rouleauFiniEntity = toEntity(rouleauFiniValue, bonSortieFiniEntity);
		   
		    	 listeRouleauFini.add(rouleauFiniEntity);
		    }
		     
		     bonSortieFiniEntity.setListeRouleauFini(listeRouleauFini);
		}
		
		//System.out.println("##### PERSIST #### convert  request.getListeRouleauFini()  :  "+bonSortieFiniEntity.getListeRouleauFini().size());

	    
		return bonSortieFiniEntity;
	}

	//TODO 
	private ColisEntity toEntity(ColisValue rouleauFiniValue,
			BonSortieFiniEntity bonSortieFiniEntity) {
		ColisEntity rouleauFiniEntity = new ColisEntity();
		
		if(rouleauFiniValue.getId() != null){
			rouleauFiniEntity.setId(rouleauFiniValue.getId());
		}
	    //rouleauFiniEntity.setBlSuppression(rouleauFiniValue.isBlSuppression());
	    rouleauFiniEntity.setChoix(rouleauFiniValue.getChoix());
	   // rouleauFiniEntity.setCodeBarre(rouleauFiniValue.getCodeBarre());
	   
	    rouleauFiniEntity.setCouleurId(rouleauFiniValue.getCouleurId());
	    rouleauFiniEntity.setDesignationCouleur(rouleauFiniValue.getCouleurDesignation());
	    rouleauFiniEntity.setDesignationTaille(rouleauFiniValue.getTailleDesignation());
	    rouleauFiniEntity.setNum(rouleauFiniValue.getNum());
	    rouleauFiniEntity.setOf(rouleauFiniValue.getOrdreNumero());
	    rouleauFiniEntity.setOrdre(rouleauFiniValue.getOrdre());
	    rouleauFiniEntity.setQuantite(rouleauFiniValue.getQuantite());
	    rouleauFiniEntity.setBonSortie(bonSortieFiniEntity);
	    rouleauFiniEntity.setQuantiteDesignation(rouleauFiniValue.getQuantiteDesignation());
	    rouleauFiniEntity.setOrdreFiche(rouleauFiniValue.getOrdreFiche());
	    
	    rouleauFiniEntity.setFictif(rouleauFiniValue.getFictif());
	    rouleauFiniEntity.setOrdrePalette(rouleauFiniValue.getOrdrePalette());
	    
	    if (rouleauFiniValue.getFicheColisageId()!=null)
	    {
	    	FicheColisageEntity fiche= new FicheColisageEntity();
	    	  fiche.setId(rouleauFiniValue.getFicheColisageId());
	    	  rouleauFiniEntity.setFicheColisage(fiche);
	    }
	    
	    
		return rouleauFiniEntity;
	}

	public BonSortieFiniValue toValue(BonSortieFiniEntity bonSortieFiniEntity) {
		
		BonSortieFiniValue bonSortieFiniValue = new BonSortieFiniValue();
		
		bonSortieFiniValue.setId(bonSortieFiniEntity.getId());
		bonSortieFiniValue.setDateSortie(bonSortieFiniEntity.getDateSortie());
		bonSortieFiniValue.setMetrageTotal(bonSortieFiniEntity.getMetrageTotal());
		bonSortieFiniValue.setNbColis(bonSortieFiniEntity.getNbColis());
		bonSortieFiniValue.setObservation(bonSortieFiniEntity.getObservation());
		bonSortieFiniValue.setPartieInt(bonSortieFiniEntity.getPartieInt());
		bonSortieFiniValue.setReference(bonSortieFiniEntity.getReference());
		bonSortieFiniValue.setType(bonSortieFiniEntity.getType());
		bonSortieFiniValue.setPoidsEcru(bonSortieFiniEntity.getPoidsEcru());
		bonSortieFiniValue.setPoidsFini(bonSortieFiniEntity.getPoidsFini());
		
		bonSortieFiniValue.setBlExport(bonSortieFiniEntity.getBlExport());
		bonSortieFiniValue.setFini(bonSortieFiniEntity.getFini());
		
		bonSortieFiniValue.setReferenceBon(bonSortieFiniEntity.getReferenceBon());
		
		bonSortieFiniValue.setNbColisReel(bonSortieFiniEntity.getNbColis());
		
		bonSortieFiniValue.setSpecial(bonSortieFiniEntity.getSpecial());
		//TODO 
		
		
	    if(bonSortieFiniEntity.getListeRouleauFini()!= null){
	    	
		     List<ColisValue> listeRouleauFini = new ArrayList<ColisValue>();
		     
		     for (ColisEntity rouleauFiniEntity : 
		    	 bonSortieFiniEntity.getListeRouleauFini()) {
		    	 ColisValue rouleauFiniValue = toValue(rouleauFiniEntity);
		    	 listeRouleauFini.add(rouleauFiniValue);
		    }
		     
	     bonSortieFiniValue.setListeRouleauFini(listeRouleauFini);
		}
		
		return bonSortieFiniValue;
	}

	
	//TODO 
	
	
	private ColisValue toValue(ColisEntity entity) {
		
		ColisValue dto = new ColisValue();
		
		dto.setId(entity.getId());
		dto.setChoix(entity.getChoix());
		
		
		dto.setCouleurId(entity.getCouleurId());
		dto.setCouleurDesignation(entity.getDesignationCouleur());
		dto.setTailleDesignation(entity.getDesignationTaille());
		dto.setNum(entity.getNum());
		dto.setOrdreNumero(entity.getOf());
		
		dto.setOrdreFiche(entity.getOrdreFiche());
		dto.setAncienOrdre(entity.getOrdre());
		if (entity.getOrdrePalette()!=null)
		dto.setOrdre(new Long (entity.getOrdrePalette()));
		else 
			dto.setOrdre(
					entity.getOrdre());
		
		
		dto.setQuantite(entity.getQuantite());
		dto.setQuantiteDesignation(entity.getQuantiteDesignation());
	   // rouleauFiniEntity.set
	    
	  
		dto.setFictif(entity.getFictif());
		dto.setOrdrePalette(entity.getOrdrePalette());
		
		if (entity.getFicheColisage()!=null)
	    {
	  
	    	dto.setFicheColisageId(entity.getFicheColisage().getId());
	    }
	    
		
		
		
		if(entity.getBonSortie() != null){
			dto.setBonSortie(entity.getBonSortie().getId());
		}
		
	
		
		return dto;
	}

	public List<BonSortieFiniValue> toValue(List<BonSortieFiniEntity> listEntity) {
		List<BonSortieFiniValue> list = new ArrayList<BonSortieFiniValue>();
		
		for(BonSortieFiniEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}
}
