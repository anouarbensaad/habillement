package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.FicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.RechercheMulticritereFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.ResultatRechecheFicheEclatementValue;
import com.gpro.consulting.tiers.gpao.coordination.value.DetailOfValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IFicheEclatementDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IFicheEclatementPersistance;

/**
 * implementation of {@link IFicheEclatementDomaine}
 * 
 * @author Wahid Gazzah
 * @since 16/05/2016
 *
 */

@Component
public class FicheEclatementDomaineImpl implements IFicheEclatementDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(FicheEclatementDomaineImpl.class);
	
	private static final Long ZERO_L = 0L;
	
	@Autowired
	private IFicheEclatementPersistance ficheEclatementPersistance;
	
	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;

	
	
	
	@Override
	public FicheEclatementValue getById(Long id) {
		
		//LOGGER.info("getFicheEclatementById: Delegating request {} to Persistance layer.",id);
		
		return ficheEclatementPersistance.getById(id);
	}
	
	@Override
	public String create(FicheEclatementValue ficheEclatementValue) {
		
		//LOGGER.info("createFicheEclatement: Delegating request to Persistance layer.");
		
		if(ficheEclatementValue != null){
			
			Long nombrePaquet = ZERO_L;
			Long quantiteEclate = ZERO_L;
			
			if(ficheEclatementValue.getListPaquet() != null){
				
				nombrePaquet = Long.valueOf(ficheEclatementValue.getListPaquet().size());
				
				for(PaquetValue element : ficheEclatementValue.getListPaquet()){
					
					quantiteEclate = quantiteEclate + ((element.getQuantite() != null) ? element.getQuantite() : ZERO_L);
				}
				
				ficheEclatementValue.setQuantiteEclate(quantiteEclate);
				ficheEclatementValue.setNombrePaquet(nombrePaquet);
			}
			
			
			
			
			if (ficheEclatementValue.getOrdreFabricationId()==null && ficheEclatementValue.getOrdreFabricationNumero()!=null && !ficheEclatementValue.equals(""))
			
			{ OrdreFabricationValue ordre= ordreFabricationPersistance.getByNumero(ficheEclatementValue.getOrdreFabricationNumero());
			 if (ordre!=null){
				 Long idOF= ordre.getId();
			
			    ficheEclatementValue.setOrdreFabricationId(idOF);
			
			 }
			}
			
		}
		
		return ficheEclatementPersistance.create(ficheEclatementValue);
	}

	@Override
	public String update(FicheEclatementValue ficheEclatementValue) {
		
		//LOGGER.info("updateFicheEclatement: Delegating request to Persistance layer.");
		
		
		if(ficheEclatementValue != null){
			
			Long nombrePaquet = ZERO_L;
			Long quantiteEclate = ZERO_L;
			
			if(ficheEclatementValue.getListPaquet() != null){
				
				nombrePaquet = Long.valueOf(ficheEclatementValue.getListPaquet().size());
				
				for(PaquetValue element : ficheEclatementValue.getListPaquet()){
					
					quantiteEclate = quantiteEclate + ((element.getQuantite() != null) ? element.getQuantite() : ZERO_L);
				}
				
				ficheEclatementValue.setQuantiteEclate(quantiteEclate);
				ficheEclatementValue.setNombrePaquet(nombrePaquet);
			}
		}
		
		return ficheEclatementPersistance.update(ficheEclatementValue);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteficheEclatementValue: Delegating request {} to Persistance layer.",id);
		
		ficheEclatementPersistance.delete(id);
	}

	@Override
	public List<FicheEclatementValue> getAll(){
		
		//LOGGER.info("getAll: Delegating request to Persistance layer.");
		
		return ficheEclatementPersistance.getAll();
	}

	@Override
	public ResultatRechecheFicheEclatementValue rechercherMultiCritere(
			RechercheMulticritereFicheEclatementValue request) {
		
		//LOGGER.info("rechercherMultiCritere: Delegating request to Persistance layer.");
		
		if (request.getOrdreFabricationId()==null && request.getNumOF()!=null && !request.equals(""))
			
		{ 
			
		OrdreFabricationValue of=ordreFabricationPersistance.getByNumero(request.getNumOF())	;
		 if (of!=null){
		 Long idOF= of.getId();
	     request.setOrdreFabricationId(idOF);
		 }
	   
	}
		
		
		return ficheEclatementPersistance.rechercherMultiCritere(request);
	}

	@Override
	public Set<PaquetValue> getPaquetListByOfIdAndQuantitePaquet(
			Long ordreFabricationId, Long quantitePaquet) {
		
		List<PaquetValue> listPaquet = new ArrayList<PaquetValue>();
		
		
		
		
		
		
		if(ordreFabricationId != null && quantitePaquet != null){
			
			OrdreFabricationValue ordreFabrication = ordreFabricationPersistance.rechercheOrdreFabricationParId(ordreFabricationId);
			
			//Ordonner details OF par couleur puis par taille 
			//TODO A refaire 
			if(ordreFabrication != null){
				
				Long ordre = 1L;
				
				List <DetailOfValue> listDetails=new ArrayList<DetailOfValue>();
				
				for (DetailOfValue det:ordreFabrication.getDetailsOF()){
					//attribut pour appliquer un ordre 
					Long rang=10000*det.getCouleurId()+det.getTailleId();
					det.setOrdre(rang);
					listDetails.add(det);					
					
					
				}
				
				Collections.sort(listDetails);
				
				
				
				
				
				for(DetailOfValue detail : listDetails){
					
					if(detail.getQuantite() != null){
						
						Long nbrPaquetsComplet = detail.getQuantite() / quantitePaquet;
						Long reste = detail.getQuantite() % quantitePaquet;
						
						for(int i=0; i < nbrPaquetsComplet; i++ ){
							
							PaquetValue paquet = new PaquetValue();
							
							paquet.setQuantite(quantitePaquet);
							paquet.setCouleurId(detail.getCouleurId());
							paquet.setTailleId(detail.getTailleId());
							
							paquet.setOrdre(ordre);
							paquet.setNum(ordre);
							
							ordre++;
							
							listPaquet.add(paquet);
						}
						
						if(reste != 0){
							
							PaquetValue paquet = new PaquetValue();
							
							paquet.setQuantite(reste);
							paquet.setCouleurId(detail.getCouleurId());
							paquet.setTailleId(detail.getTailleId());
							
							paquet.setOrdre(ordre);
							paquet.setNum(ordre);
							
							ordre++;
							
							listPaquet.add(paquet);
						}
				}
			}
		}
	}
		
		Collections.sort(listPaquet);
		return (new TreeSet<>(listPaquet));
	}

	@Override
	public Set<PaquetValue> getPaquetListByOfId(Long ordreFabricationId) {
		
		List<PaquetValue> result = new ArrayList<PaquetValue>();
		
		List<FicheEclatementValue> listFicheEclatement = ficheEclatementPersistance.getByOrdreFabricationId(ordreFabricationId);
		
		if(listFicheEclatement != null){
			
			for(FicheEclatementValue element : listFicheEclatement){
				
				result.addAll(element.getListPaquet());
			}
			
			Collections.sort(result);
			return (new TreeSet<>(result));
		}
		
		else return null;
	}
	
	@Override
	public String updateAll() {
		
		List <FicheEclatementValue>  list=new ArrayList<FicheEclatementValue>();
		list=this.getAll();
		for (FicheEclatementValue ficheEclatementValue:list)
		{   int ordre=1;
			List<PaquetValue> listPaquet=new ArrayList<PaquetValue>();
			//List<PaquetValue> listInit=ficheEclatementValue.getListPaquet();
			if(ficheEclatementValue != null){
			
			for (PaquetValue paquet :ficheEclatementValue.getListPaquet()){
				listPaquet.add(paquet);	
			}
			
			Collections.sort(listPaquet);
			
			for (int i=0;i<listPaquet.size();i++){
				int rg=i+1;
				listPaquet.get(i).setOrdre(new Long(rg));
			}
				
				
				
		}
		 ficheEclatementValue.setListPaquet(new TreeSet<PaquetValue>(listPaquet));
		 ficheEclatementPersistance.update(ficheEclatementValue);
	}
		return "OK";
	}
}
	

