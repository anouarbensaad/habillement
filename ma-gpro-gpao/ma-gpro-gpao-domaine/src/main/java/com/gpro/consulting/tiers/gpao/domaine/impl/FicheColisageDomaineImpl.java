package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.DetailsColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheFicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IFicheColisageDomaine;
import com.gpro.consulting.tiers.gpao.domaine.bonsortiefini.IBonSortieFiniDomain;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IFicheColisagePersistance;

/**
 * implementation of {@link IFicheColisageDomaine}
 * 
 * @author Hamdi Etteieb
 * @since 07/12/2017
 *
 */

@Component
public class FicheColisageDomaineImpl implements IFicheColisageDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(FicheColisageDomaineImpl.class);
	
	private static final Long ZERO_L = 0L;
	
	@Autowired
	private IFicheColisagePersistance ficheColisagePersistance;
	
	@Autowired
	private IPartieInteresseePersistance partieInteressePersistance;

	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistence;
	
	@Autowired
	IBonSortieFiniDomain bonSortieFiniDomain;
	
	@Override
	public FicheColisageValue getById(Long id) {
		
		//LOGGER.info("getFicheColisageById: Delegating request {} to Persistance layer.",id);
		
		FicheColisageValue ficheColisage = ficheColisagePersistance.getById(id);

		
		return ficheColisage;
	}
	
	@Override
	public String create(FicheColisageValue ficheColisageValue) {
		
		//LOGGER.info("createFicheColisage: Delegating request to Persistance layer.");
		
		if(ficheColisageValue != null){
			
			Long nombrePaquet = ZERO_L;
			Long quantiteEclate = ZERO_L;
			
			if(ficheColisageValue.getListColis() != null){
				
				
				if (ficheColisageValue.getColPalette() != null && ficheColisageValue.getColPalette() == true)
				{
					RechercheMulticritereBonSortieFiniValue request;
				    for (ColisValue cv:ficheColisageValue.getListColis())
				    {
				    	if (cv.getRefPalette() != null && !cv.getRefPalette().equals(""))
				    	{
				    		request= new RechercheMulticritereBonSortieFiniValue();
				    		request.setReferenceBon(cv.getRefPalette());
				    		ResultatRechecheBonSortieFiniValue res = bonSortieFiniDomain.rechercherMultiCritere(request);
				    		
				    		if (res != null) 
				    		{
				    			if (res.getNombreResultaRechercher() > 0)
				    			{
				    				cv.setBonSortie(res.getList().iterator().next().getId());
				    			}
				    		}
				    	}
				    }
				}
				
				
				nombrePaquet = Long.valueOf(ficheColisageValue.getListColis().size());
				
				for(ColisValue element : ficheColisageValue.getListColis()){
						
					quantiteEclate = quantiteEclate + ((element.getQuantite() != null) ? element.getQuantite() : ZERO_L);
				}
				
				ficheColisageValue.setQuantiteColis(quantiteEclate);
				ficheColisageValue.setNombreColis(nombrePaquet);
			
			// Mise a jour de l'ordre de fabrication 
				
				if(ficheColisageValue.getOrdreFabricationNumero()!=null){
				OrdreFabricationValue ordreFabrication =ordreFabricationPersistence.getByNumero(ficheColisageValue.getOrdreFabricationNumero());
			       
				if(ordreFabrication!=null){
					
					ficheColisageValue.setOrdreFabricationId(ordreFabrication.getId());
					
				ordreFabrication.setQtColisage(ordreFabrication.getQtColisage()+ficheColisageValue.getQuantiteColis());
			
				//ordreFabrication.setSolder(ficheColisageValue.getSolder());
				
				if (ficheColisageValue.getColPalette() != null && ficheColisageValue.getColPalette() == true)
				{
					ordreFabrication.setPrixUnitaire(ficheColisageValue.getOfPrixUnitaire());
				}
				
				      ordreFabricationPersistence.modifierOrdreFabrication(ordreFabrication);
				      
				      
				}     
				}
			
			
			}
			
		/*	Long quantiteTotale = ZERO_L;
			
			if(ficheColisageValue.getListDetails()!=null){
				
				for (DetailsColisageValue det:ficheColisageValue.getListDetails()){
					quantiteTotale+=det.getQuantite();
				}
				ficheColisageValue.setQuantiteTotale(quantiteTotale);
			}
		*/	
		}
		
		PartieInteresseValue partieInteresseValue=partieInteressePersistance.recherchePartieInteresseeParId(ficheColisageValue.getClientId());
		ficheColisageValue.setClientAbreviation(partieInteresseValue.getAbreviation());
        ficheColisageValue.setClientReference(partieInteresseValue.getReference());		
		
		return ficheColisagePersistance.create(ficheColisageValue);
	}

	@Override
	public String update(FicheColisageValue ficheColisageValue) {
		
		
		//Correction de Calcul Mode Edition 
		//LOGGER.info("updateFicheColisage: Delegating request to Persistance layer.");
		Long nombrePaquet = ZERO_L;
		Long quantiteEclate = ZERO_L;
		
		if(ficheColisageValue.getListColis() != null){
			
			if (ficheColisageValue.getColPalette() != null && ficheColisageValue.getColPalette() == true)
			{
				RechercheMulticritereBonSortieFiniValue request;
			    for (ColisValue cv:ficheColisageValue.getListColis())
			    {
			    	if (cv.getOrdrePalette() != null && cv.getOrdrePalette().equals("")) cv.setOrdrePalette(null);
			    		
			    	if (cv.getRefPalette() != null)
			    	{
			    		if (!cv.getRefPalette().equals(""))
			    		{
			    			request= new RechercheMulticritereBonSortieFiniValue();
				    		request.setReferenceBon(cv.getRefPalette());
				    		ResultatRechecheBonSortieFiniValue res = bonSortieFiniDomain.rechercherMultiCritere(request);
				    		
				    		if (res != null) 
				    		{
				    			if (res.getNombreResultaRechercher() > 0)
				    			{
				    				cv.setBonSortie(res.getList().iterator().next().getId());
				    			}
				    		}
			    		}
			    		else 
			    		{
			    			cv.setBonSortie(null);
			    			cv.setOrdrePalette(null);
			    			cv.setFictif(null);
			    		}
			    
			    	}
			    }
			}
			
			
			nombrePaquet = Long.valueOf(ficheColisageValue.getListColis().size());
			
			for(ColisValue element : ficheColisageValue.getListColis()){
				
				quantiteEclate = quantiteEclate + ((element.getQuantite() != null) ? element.getQuantite() : ZERO_L);
			}
			
			ficheColisageValue.setQuantiteColis(quantiteEclate);
			ficheColisageValue.setNombreColis(nombrePaquet);
		
		
			if(ficheColisageValue.getOrdreFabricationNumero()!=null){
				OrdreFabricationValue ordreFabrication =ordreFabricationPersistence.getByNumero(ficheColisageValue.getOrdreFabricationNumero());
			       
				if(ordreFabrication!=null){
					
					ficheColisageValue.setOrdreFabricationId(ordreFabrication.getId());
					////System.out.println("/***********ordreFabrication.getQtColisage()  :  "+ordreFabrication.getQtColisage());
					////System.out.println("/*//////////////////////ficheColisageValue.getQuantiteColisBefore() : "+ficheColisageValue.getQuantiteColisBefore());
				//	//System.out.println("/*//////////////ficheColisageValue.getQuantiteColis() :  "+ficheColisageValue.getQuantiteColis());
					
				ordreFabrication.setQtColisage(ordreFabrication.getQtColisage()-ficheColisageValue.getQuantiteColisBefore()+ficheColisageValue.getQuantiteColis());
			//	ordreFabrication.setSolder(ficheColisageValue.getSolder());
				      ordreFabricationPersistence.modifierOrdreFabrication(ordreFabrication);
				      
				}
				}
			
			
	/*	Long quantiteTotale = ZERO_L;
		
		if(ficheColisageValue.getListDetails()!=null){
			
			for (DetailsColisageValue det:ficheColisageValue.getListDetails()){
				quantiteTotale+=det.getQuantite();
			}
			ficheColisageValue.setQuantiteTotale(quantiteTotale);
		}  */
		
	}
		PartieInteresseValue partieInteresseValue=partieInteressePersistance.recherchePartieInteresseeParId(ficheColisageValue.getClientId());
		ficheColisageValue.setClientAbreviation(partieInteresseValue.getAbreviation());
        ficheColisageValue.setClientReference(partieInteresseValue.getReference());	
		
		return ficheColisagePersistance.update(ficheColisageValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteficheColisageValue: Delegating request {} to Persistance layer.",id);
		FicheColisageValue  ficheColisageValue =ficheColisagePersistance.getById(id);
		if(ordreFabricationPersistence!=null){
		if(ficheColisageValue.getOrdreFabricationNumero()!=null){
			OrdreFabricationValue ordreFabrication =ordreFabricationPersistence.getByNumero(ficheColisageValue.getOrdreFabricationNumero());
		         ordreFabrication.setQtColisage(ordreFabrication.getQtColisage()-ficheColisageValue.getQuantiteColis());
			
			      ordreFabricationPersistence.modifierOrdreFabrication(ordreFabrication);
			}
		}
		
		ficheColisagePersistance.delete(id);
	}

	@Override
	public List<FicheColisageValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Persistance layer.");
		
		return ficheColisagePersistance.getAll();
	}

	@Override
	public ResultatRechecheFicheColisageValue rechercherMultiCritere(
			RechercheMulticritereFicheColisageValue request) {
		
		//LOGGER.info("rechercherMultiCritere: Delegating request to Persistance layer.");
		
		return ficheColisagePersistance.rechercherMultiCritere(request);
	}

	@Override
	public Set<ColisValue> getPaquetListByOfIdAndQuantiteColis(
			Long ordreFabricationId, Long quantitePaquet) {
		
		List<ColisValue> listPaquet = new ArrayList<ColisValue>();
		
		
		return (new TreeSet<>(listPaquet));
	}

	@Override
	public Set<ColisValue> getPaquetListByOfId(Long ordreFabricationId) {
		//TODO 
		List<ColisValue> result = new ArrayList<ColisValue>();
		
		 return null;
	}
	
}
	

