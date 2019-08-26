package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.util.HSSFColor.AQUA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.domaine.ICouleurDomaine;
import com.gpro.consulting.tiers.commun.domaine.ITailleDomaine;
import com.gpro.consulting.tiers.commun.service.IGestionnaireCommunCacheService;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue.FicheSuiveuseVue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.domaine.IFicheEclatementDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IFicheSuiveuseDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IGammeOfDomaine;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;

/**
 * implementation of {@link IFicheSuiveuseDomaine}
 * 
 * @author Wahid Gazzah
 * @since 20/05/2016
 *
 */

@Component
public class FicheSuiveuseDomaineImpl implements IFicheSuiveuseDomaine{
	
	private static final Logger logger = LoggerFactory.getLogger(FicheSuiveuseDomaineImpl.class);
	
	@Autowired
	private IFicheEclatementDomaine ficheEclatementDomaine;
	
	@Autowired
	private IGammeOfDomaine gammeOfDomaine;
	
	@Autowired
	private IOperationPersistance operationPersistance;
	
	@Autowired
	private IMachinePersistance machinePersistance;
	
	@Autowired
	private ISectionPersistance sectionPersistance;

	@Autowired
	private ICouleurDomaine couleurDomaine;
	
	@Autowired
	ITailleDomaine tailleDomaine;
	
	@Override
	public FicheSuiveuseVue getByOrdreFabricationId(Long ordreFabricationId) {
		
		FicheSuiveuseVue result = new FicheSuiveuseVue();
		
		List<OperationValue> listOperations = new ArrayList<OperationValue>();
		
		GammeOfValue gammeOF = gammeOfDomaine.getByOFId(ordreFabricationId);
		
		if(gammeOF != null){
			
			// Map< operationId , OperationValue >
			Map<Long, OperationValue> mapOperations = new HashMap<Long, OperationValue>();
			
			// Map< machineId , machineDesignation >
			Map<Long, String> mapMachineIdDesignation = new HashMap<Long, String>();
			List<MachineValue> listMachine = machinePersistance.getAll();
			
			for(MachineValue machine : listMachine){
				mapMachineIdDesignation.put(machine.getId(), machine.getDesignation());
			}
			
			// Map< sectionId , sectionDesignation >
			List<SectionValue> listSection = sectionPersistance.getAll();
			Map<Long, String> mapSectionIdDesignation = new HashMap<Long, String>();
			
			for(SectionValue section : listSection){
				mapSectionIdDesignation.put(section.getId(), section.getDesignation());
			}
			
			for(ElementGammeOfValue element : gammeOF.getListElementGammeOf()){
				
				Long key = element.getOperationId();
				
				if(key != null){
					
					if (mapOperations.get(key) == null) {
						
						OperationValue operation = operationPersistance.getById(key);
						
						if(operation != null){
							
							operation.setOrdreElementGammeOf(element.getOrdre());
							
							if(operation.getMachineId() != null){
								
								operation.setMachineDesignation(mapMachineIdDesignation.get(operation.getMachineId()));
							}
							
							if(operation.getSectionId() != null){
								
								operation.setSectionDesignation(mapSectionIdDesignation.get(operation.getSectionId()));
							}
							
							mapOperations.put(key, operation);
						}
						
					}
					
				}
				
			}
			
			Iterator it = mapOperations.entrySet().iterator();
		    while (it.hasNext()) {
		    	
				Map.Entry <Long, OperationValue> pair = (Map.Entry<Long, OperationValue>)it.next();
		    	
		    	listOperations.add(pair.getValue());
		    }
			
		}
		
		
		Collections.sort(listOperations);
		
		result.setOperationsList(new TreeSet<>(listOperations));
		
		Set<PaquetValue> listPaquet=ficheEclatementDomaine.getPaquetListByOfId(ordreFabricationId);
		List<PaquetValue> listPaquetFinal=new ArrayList<PaquetValue>();
		List<CouleurValue> listCouleur = couleurDomaine.listeCouleur();
		Map<Long, String> mapCouleurIdDesignation = new HashMap<Long, String>();
		for (CouleurValue couleur : listCouleur) {
			mapCouleurIdDesignation.put(couleur.getId(), couleur.getDesignation());
		}

		List<TailleValue> listTaille = tailleDomaine.listeTaille();
		Map<Long, String> mapTailleIdDesignation = new HashMap<Long, String>();
		for (TailleValue taille : listTaille) {
			mapTailleIdDesignation.put(taille.getId(), taille.getDesignation());
		}
		
		
		for (PaquetValue paquet:listPaquet){
			
			if (paquet.getTailleId() != null && mapTailleIdDesignation.containsKey(paquet.getTailleId()))
				paquet.setTailleDesignation(mapTailleIdDesignation.get(paquet.getTailleId()));
				if (paquet.getCouleurId() != null && mapCouleurIdDesignation.containsKey(paquet.getCouleurId()))
					paquet.setCouleurDesignation(mapCouleurIdDesignation.get(paquet.getCouleurId()));
		    listPaquetFinal.add(paquet);
		}
		 Collections.sort(listPaquetFinal);
		
		result.setPaquetsList(new TreeSet<>(listPaquetFinal));
		
		return result;
	}

}
