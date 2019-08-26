package com.gpro.consulting.tiers.gpao.persitance.fichecolisage.utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;





import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.DetailsColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisElementValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageElementValue;
import com.gpro.consulting.tiers.gpao.persitance.bonsortiefini.entity.BonSortieFiniEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.ColisEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.DetailsColisageEntity;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.entity.FicheColisageEntity;




@Component
public class FicheColisagePersistanceUtility {
	
	public FicheColisageValue toValue(FicheColisageEntity entity){
		
		FicheColisageValue dto = new FicheColisageValue();
		
		dto.setId(entity.getId());
		dto.setDateLancement(entity.getDateLancement());
		dto.setObservations(entity.getObservations());
		// A changer ulterieurement
		dto.setOrdreFabricationId(entity.getOrdreFabricationId());
		dto.setOrdreFabricationNumero(entity.getNumeroOf());
		dto.setNombreColis(entity.getNombreColis());
		dto.setQuantiteColis(entity.getQuantiteColis());
		dto.setQuantiteTotale(entity.getQuantiteTotale());
		dto.setClientAbreviation(entity.getClientAbreviation());
		dto.setClientId(entity.getClientId());
		dto.setClientReference(entity.getClientReference());
		dto.setCouleur(entity.getCouleur());
		dto.setProduitDesignation(entity.getProduitDesignation());
		dto.setProduitId(entity.getProduitId());
		dto.setProduitReference(entity.getProduitReference());
	    dto.setSemaine(entity.getSemaine());
		
	   // //System.out.println("*-****UTILITIES    entity.getQuantiteColis() :   "+entity.getQuantiteColis());
	    
	    dto.setQuantiteColisBefore(entity.getQuantiteColis());
		if(entity.getListColis() != null){
	    	List<ColisValue> list = new ArrayList<ColisValue>();
		     for (ColisEntity colisEntity : entity.getListColis()) {
		    	 ColisValue colisValue = toValueOrdreFiche(colisEntity);
		    	// ColisValue colisValue = toValue(colisEntity);
		    	 list.add(colisValue);
		    }
		     Collections.sort(list);
		     dto.setListColis(new TreeSet<ColisValue>(list));
		}
	    if(entity.getListDetails ()!= null){
	    	List<DetailsColisageValue> list = new ArrayList<DetailsColisageValue>() ;
	    	
	    	////System.out.println("##### Liste  Size  :"+entity.getListDetails ().size());
	    	for(DetailsColisageEntity detailsEntity : entity.getListDetails()){
	    		DetailsColisageValue detailsValue = toValue(detailsEntity);
	    		list.add(detailsValue);
	    	}
	    	Collections.sort(list);
	    	dto.setListDetails(new TreeSet<DetailsColisageValue>(list));
	    }
		return dto;
	}
	
	public FicheColisageEntity toEntity(FicheColisageValue dto){
		
		FicheColisageEntity entity = new FicheColisageEntity();
		
		entity.setId(dto.getId());
		entity.setDateLancement(dto.getDateLancement());
		entity.setObservations(dto.getObservations());
		entity.setOrdreFabricationId(dto.getOrdreFabricationId());
		
	
		entity.setNombreColis(dto.getNombreColis());
		entity.setQuantiteColis(dto.getQuantiteColis());
		entity.setQuantiteTotale(dto.getQuantiteTotale());
		entity.setProduitReference(dto.getProduitReference());
		entity.setProduitDesignation(dto.getProduitDesignation());
		entity.setProduitId(dto.getProduitId());
		
		entity.setNumeroOf(dto.getOrdreFabricationNumero());
		entity.setProduitDesignation(dto.getProduitDesignation());
		entity.setCouleur(dto.getCouleur());
		entity.setClientId(dto.getClientId());
		entity.setClientAbreviation(dto.getClientAbreviation());
		entity.setClientReference(dto.getClientAbreviation());
		entity.setSemaine(dto.getSemaine());
		
		if(dto.getListColis() != null){
		     Set<ColisEntity> list = new HashSet <ColisEntity>();
		     for (ColisValue colisValue : dto.getListColis()) {
		    	 ColisEntity colisEntity = toEntity(colisValue);
		    	 colisEntity.setFicheColisage(entity);
		    	 list.add(colisEntity);
		    }
		     entity.setListColis(list);
		}
	    
	    if(dto.getListDetails() != null ){
	    	Set<DetailsColisageEntity> listDetails = new HashSet<DetailsColisageEntity>() ;
	    	for(DetailsColisageValue detailsValue: dto.getListDetails()){
	    		DetailsColisageEntity detailsEntity = toEntity(detailsValue);
	    		detailsEntity.setFicheColisage(entity);
	    		listDetails.add(detailsEntity) ;
	    	}
	    	entity.setListDetails(listDetails);
	    }
		
	   
		return entity;
	}
	
	
	public static ColisValue toValue(ColisEntity entity){
		
		ColisValue dto = new ColisValue();
		
		dto.setId(entity.getId());
		dto.setNum(entity.getNum());
		dto.setCouleurId(entity.getCouleurId());
		dto.setTailleId(entity.getTailleId());
		dto.setQuantite(entity.getQuantite());
		dto.setOrdre(entity.getOrdre());
		dto.setTailleDesignation(entity.getDesignationTaille());
		dto.setCouleurDesignation(entity.getDesignationCouleur());
		dto.setPoidsBrut(entity.getPoidsBrut());
		dto.setPoidsNet(entity.getPoidsNet());
		dto.setQuantiteDesignation(entity.getQuantiteDesignation());
		dto.setPalette(entity.getPalette());
		
        dto.setOrdrePalette(entity.getOrdrePalette());
		
		dto.setAncienOrdre(entity.getOrdre());
        
		dto.setOrdreFiche(entity.getOrdreFiche());
		
		
        if (entity.getOrdrePalette()!=null) {
			if (!entity.getOrdrePalette().equals(""))
			dto.setOrdre(new Long (entity.getOrdrePalette()));
			
		}
        
        
        dto.setFictif(entity.getFictif());
		
		if(entity.getFicheColisage() != null){
			dto.setFicheColisageId(entity.getFicheColisage().getId());
		}
		if (entity.getBonSortie()!=null){
			dto.setBonSortie(entity.getBonSortie().getId());
		}
		
		return dto;
	}
	public static ColisValue toValueOrdreFiche(ColisEntity entity){
		
		ColisValue dto = new ColisValue();
		
		dto.setId(entity.getId());
		dto.setNum(entity.getNum());
		dto.setCouleurId(entity.getCouleurId());
		dto.setTailleId(entity.getTailleId());
		dto.setQuantite(entity.getQuantite());
		dto.setOrdre(entity.getOrdre());
		dto.setTailleDesignation(entity.getDesignationTaille());
		dto.setCouleurDesignation(entity.getDesignationCouleur());
		dto.setPoidsBrut(entity.getPoidsBrut());
		dto.setPoidsNet(entity.getPoidsNet());
		dto.setQuantiteDesignation(entity.getQuantiteDesignation());
		dto.setPalette(entity.getPalette());
		
        dto.setOrdrePalette(entity.getOrdrePalette());
		
		dto.setAncienOrdre(entity.getOrdre());
        
		dto.setOrdreFiche(entity.getOrdreFiche());
		
		/*
        if (entity.getOrdrePalette()!=null) {
			if (!entity.getOrdrePalette().equals(""))
			dto.setOrdre(new Long (entity.getOrdrePalette()));
			
		}
        */
		
		dto.setOrdre(new Long (entity.getOrdreFiche()));
        dto.setFictif(entity.getFictif());
		
		if(entity.getFicheColisage() != null){
			dto.setFicheColisageId(entity.getFicheColisage().getId());
		}
		if (entity.getBonSortie()!=null){
			dto.setBonSortie(entity.getBonSortie().getId());
			dto.setRefPalette(entity.getBonSortie().getReferenceBon());
		}
		
		return dto;
	}
	
	public ColisEntity toEntity(ColisValue dto){
		
		//System.out.println("##### TO COLIS ENTITY ######");
		
		ColisEntity entity = new ColisEntity();
		
		entity.setId(dto.getId());
		entity.setNum(dto.getNum());
		entity.setCouleurId(dto.getCouleurId());
		entity.setTailleId(dto.getTailleId());
		entity.setQuantite(dto.getQuantite());
		entity.setOrdre(dto.getOrdre());
	    entity.setDesignationTaille(dto.getTailleDesignation());
	    entity.setDesignationCouleur(dto.getCouleurDesignation());
	    entity.setPoidsBrut(dto.getPoidsBrut());
	    entity.setPoidsNet(dto.getPoidsNet());
		entity.setQuantiteDesignation(dto.getQuantiteDesignation());
		entity.setPalette(dto.getPalette());
		
		entity.setOrdrePalette(dto.getOrdrePalette());
        entity.setFictif(dto.getFictif());
		
		entity.setOrdreFiche(dto.getOrdreFiche());
        
        
	    if(dto.getFicheColisageId() != null){
			
	    	FicheColisageEntity FicheColisageEntity = new FicheColisageEntity();
			FicheColisageEntity.setId(dto.getFicheColisageId());
			entity.setFicheColisage(FicheColisageEntity);
		}
	    
	    if (dto.getBonSortie()!=null){
	    	
	    	//System.out.println("##########   dto.getBonSortie()!=null");
	    	//System.out.println("*-****-*****   dto.getBonSortie() :   "+dto.getBonSortie());
	    	BonSortieFiniEntity bonSortie =new BonSortieFiniEntity();
	    	bonSortie.setId(dto.getBonSortie());
	    	entity.setBonSortie(bonSortie);
	    	entity.setDateSortie(dto.getDateSortie());
	    }
	    
		
		return entity;
	}
	
	public DetailsColisageValue toValue(DetailsColisageEntity entity){
		DetailsColisageValue dto = new DetailsColisageValue() ;
		dto.setId(entity.getId());
		dto.setCouleurDesignation(entity.getCouleurDesignation());
		dto.setCouleurId(entity.getCouleurId());
		dto.setPoidsBrut(entity.getPoidsBrut());
		dto.setPoidsNet(entity.getPoidsNet());
		dto.setQuantite(entity.getQuantite());
		dto.setQuantiteRestante(entity.getQuantiteRestante());
		dto.setTailleId(entity.getTailleId());
		dto.setTailleDesignation(entity.getTailleDesignation());
		dto.setPcb(entity.getPcb());
		if (entity.getFicheColisage()!= null){
			dto.setFicheColisageId(entity.getFicheColisage().getId());
		}
		
		
		return dto ;
		
	}
	
	public DetailsColisageEntity toEntity(DetailsColisageValue dto){
		DetailsColisageEntity entity = new DetailsColisageEntity() ;
		entity.setId(dto.getId());
		entity.setCouleurDesignation(dto.getCouleurDesignation());
		entity.setCouleurId(dto.getCouleurId());
		entity.setPoidsBrut(dto.getPoidsBrut());
		entity.setPoidsNet(dto.getPoidsNet());
		entity.setQuantite(dto.getQuantite());
		entity.setQuantiteRestante(dto.getQuantiteRestante());
		entity.setTailleId(dto.getTailleId());
		entity.setTailleDesignation(dto.getTailleDesignation());
		entity.setPcb(dto.getPcb());
		if(dto.getFicheColisageId() != null){
			FicheColisageEntity FicheColisageEntity = new FicheColisageEntity();
			FicheColisageEntity.setId(dto.getFicheColisageId());
			entity.setFicheColisage(FicheColisageEntity);
		}
		
		
		return entity ;
		
	}
	public List<FicheColisageValue> listFicheColisageToValue(List<FicheColisageEntity> listEntity) {
		
		List<FicheColisageValue> list = new ArrayList<FicheColisageValue>();
		
		for(FicheColisageEntity entity : listEntity){
			list.add(toValue(entity));
		}
		
		return list;
	}

	public ResultatRechecheFicheColisageElementValue toResultatRechecheFicheColisageElementValue(
			FicheColisageEntity entity) {
		
		ResultatRechecheFicheColisageElementValue result = new ResultatRechecheFicheColisageElementValue();
		
		result.setId(entity.getId());
		result.setNombreColis(entity.getNombreColis());
		result.setQuantiteColis(entity.getQuantiteColis());
		result.setOrdreFabricationId(entity.getOrdreFabricationId());
		result.setOrdreFabricationNumero(entity.getNumeroOf());
		result.setProduitReference(entity.getProduitReference());
		result.setProduitDesignation(entity.getProduitDesignation());
		result.setQuaniteTotale(entity.getQuantiteTotale());
		result.setCouleur(entity.getCouleur());
		result.setSemaine(entity.getSemaine());
		result.setClientAbreviation(entity.getClientAbreviation());
		
		
		return result;
	}
	
	
	public ResultatRechecheColisElementValue toResultatRechecheColisElementValue(
			ColisEntity entity) {
		
		ResultatRechecheColisElementValue result = new ResultatRechecheColisElementValue();
		
		result.setId(entity.getId());
		result.setNum(entity.getNum());
		result.setCouleurId(entity.getCouleurId());
		result.setTailleId(entity.getTailleId());
		result.setQuantite(entity.getQuantite());
		result.setOrdre(entity.getOrdre());
		result.setTailleDesignation(entity.getDesignationTaille());
		result.setCouleurDesignation(entity.getDesignationCouleur());
		result.setPoidsBrut(entity.getPoidsBrut());
		result.setPoidsNet(entity.getPoidsNet());
		result.setQuantiteDesignation(entity.getQuantiteDesignation());
		result.setPalette(entity.getPalette());
		
        result.setOrdrePalette(entity.getOrdrePalette());
		

        
		result.setOrdreFiche(entity.getOrdreFiche());
		
		result.setOrdreNumero(entity.getFicheColisage().getNumeroOf());
		
		result.setProduitReference(entity.getFicheColisage().getProduitReference());
		result.setProduitDesignation(entity.getFicheColisage().getProduitDesignation());
		
	/*	result.setOrdreFabricationId(entity.getFicheColisage().getOrdreFabricationId());
		result.setOrdreNumero(entity.getFicheColisage().getNumeroOf());
		result.setProduitReference(entity.getFicheColisage().getProduitReference());
		result.setProduitDesignation(entity.getFicheColisage().getProduitDesignation());
		result.setTailleDesignation(entity.getDesignationTaille());s
	
		result.setCouleurDesignation(entity.getFicheColisage().getCouleur());
		result.setSemaine(entity.getFicheColisage().getSemaine());
		result.setClientAbreviation(entity.getFicheColisage().getClientAbreviation());
		result.setQuantite(entity.getQuantite());
		result.setOrdre(entity.getOrdre());
		result.setQuantite(entity.getQuantite());
		result.setPalette(entity.getPalette());
		
		*/
	
		
		
        if (entity.getOrdrePalette()!=null) {
			if (!entity.getOrdrePalette().equals(""))
			result.setOrdre(new Long (entity.getOrdrePalette()));
			
		}
        
        
        result.setFictif(entity.getFictif());
        
    	if(entity.getFicheColisage() != null){
			result.setFicheColisageId(entity.getFicheColisage().getId());
		}
		
		if (entity.getBonSortie()!=null)
			{ if (entity.getBonSortie().getId()!=null)
		       result.setBonSortie(entity.getBonSortie().getId());
			}
		
		
		return result;
	}
	
}
