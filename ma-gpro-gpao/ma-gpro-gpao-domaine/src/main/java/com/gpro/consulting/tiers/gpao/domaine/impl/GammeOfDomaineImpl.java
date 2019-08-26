package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.commun.persistance.ICouleurPersistance;
import com.gpro.consulting.tiers.commun.persistance.ITaillePersistance;
import com.gpro.consulting.tiers.commun.persistance.impl.CouleurPersistanceImpl;
import com.gpro.consulting.tiers.commun.persistance.impl.ProduitCouleurPersistanceImpl;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.DetailSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.ResultatRechecheDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ElementGammeValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.DetailTailleCouleurAvancementOFValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.JourProductionAvancementOFValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.RechercheMulticritereGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ResultatRechecheGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.machine.value.MachineValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.report.reporting.rapportChaine.globalOperation.ChaineGlobalOperationElementReportValue;
import com.gpro.consulting.tiers.gpao.coordination.section.value.SectionValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IDetailSaisieDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IGammeOfDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IDetailSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.ficheeclatement.IPaquetPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IGammeProduitPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IMachinePersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.ISectionPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammeof.IGammeOfPersistance;

/**
 * implementation of {@link IGammeOfDomaine}
 * 
 * @author Wahid Gazzah
 * @since 10/05/2016
 *
 */

@Component
public class GammeOfDomaineImpl implements IGammeOfDomaine{

	private static final Logger logger = LoggerFactory.getLogger(GammeOfDomaineImpl.class);
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	private static final Double ZERO_D = 0D;
	private static final Long ZERO_L = 0L;
	
	@Autowired
	private IGammeOfPersistance gammeOfPersistance;
	
	@Autowired
	private IGammeProduitPersistance gammeProduitPersistance;
	
	@Autowired
	private IMachinePersistance machinePersistance;
	
	@Autowired
	private ISectionPersistance sectionPersistance;
	
	@Autowired
	private IOperationPersistance operationPersistance;
	
	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;
	
	@Autowired
	private IDetailSaisieDomaine detailSaisieDomaine;
	
	@Autowired
	private IPaquetPersistance paquetPersistance;
	
	@Autowired
	private ICouleurPersistance couleurPersistance;
	
	@Autowired
	private ITaillePersistance taillePersistance;
	
	@Override
	public String create(GammeOfValue gammeOf) {
		
		if(gammeOf != null){
			
			Double tempsTotal = ZERO_D;
			Long nbOperation = ZERO_L;
			
			if(gammeOf.getListElementGammeOf() != null){
				
				nbOperation = Long.valueOf(gammeOf.getListElementGammeOf().size());
			}
			
			for(ElementGammeOfValue elementGamme : gammeOf.getListElementGammeOf()){
				
				if (elementGamme.getOperationId() == null){
					
					OperationValue operation = new OperationValue();
					
					operation.setCode(elementGamme.getOperationCode());
					operation.setDesignation(elementGamme.getOperationDesignation());
					operation.setTemps(elementGamme.getTemps());
					operation.setObservations(elementGamme.getObservations());
					operation.setMachineId(elementGamme.getMachineId());
					operation.setSectionId(elementGamme.getSectionId());
					
					if(validDoubleValue(elementGamme.getTemps())){
						
						Long pdh = (long)(60 / elementGamme.getTemps());
						operation.setPdh(pdh);
					}
					
					String id = operationPersistance.create(operation);
					
					if(estNonVide(id)){
						
						elementGamme.setOperationId(Long.valueOf(id).longValue());
					}
					
				}
				
				Double temps = (elementGamme.getTemps() != null) ? elementGamme.getTemps() : ZERO_D;
				tempsTotal = tempsTotal + temps;
			}
			
			gammeOf.setTempsTotal(tempsTotal);
			gammeOf.setNbOperation(nbOperation);
			
		}
		
		return gammeOfPersistance.create(gammeOf);
	}
	

	@Override
	public String update(GammeOfValue gammeOf) {
		
		
		if(gammeOf != null){
			
			
			Double tempsTotal = ZERO_D;
			Long nbOperation = ZERO_L;
			
			if(gammeOf.getListElementGammeOf() != null){
				
				
				nbOperation = Long.valueOf(gammeOf.getListElementGammeOf().size());
			}
			
			
			for(ElementGammeOfValue elementGamme : gammeOf.getListElementGammeOf()){
				
				if (elementGamme.getOperationId() == null){
					
					OperationValue operation = new OperationValue();
					
					operation.setCode(elementGamme.getOperationCode());
					operation.setDesignation(elementGamme.getOperationDesignation());
					operation.setTemps(elementGamme.getTemps());
					operation.setObservations(elementGamme.getObservations());
					operation.setMachineId(elementGamme.getMachineId());
					operation.setSectionId(elementGamme.getSectionId());
					
					if(validDoubleValue(elementGamme.getTemps())){
						
						Long pdh = (long)(60 / elementGamme.getTemps());
						operation.setPdh(pdh);
					}
					
					
					String id = operationPersistance.create(operation);
					
					
					if(estNonVide(id)){
						
						elementGamme.setOperationId(Long.valueOf(id).longValue());
					}
					
				}
				
				
				Double temps = (elementGamme.getTemps() != null) ? elementGamme.getTemps() : ZERO_D;
				tempsTotal = tempsTotal + temps;
			}
			
			
			
			gammeOf.setTempsTotal(tempsTotal);
			gammeOf.setNbOperation(nbOperation);
			
		}
		
		
		
		String result = gammeOfPersistance.update(gammeOf);
		
		
		
		return result;
	}

	@Override
	public GammeOfValue getById(Long id) {
		
		GammeOfValue gammeOf = gammeOfPersistance.getById(id);
		
		if(gammeOf != null){
			
			// Map< machineId , machineDesignation >
			List<MachineValue> listMachine = machinePersistance.getAll();
			Map<Long, String> mapMachineIdDesignation = new HashMap<Long, String>();
			for(MachineValue machine : listMachine){
				mapMachineIdDesignation.put(machine.getId(), machine.getDesignation());
			}
			
			// Map< sectionId , sectionDesignation >
			List<SectionValue> listSection = sectionPersistance.getAll();
			Map<Long, String> mapSectionIdDesignation = new HashMap<Long, String>();
			for(SectionValue section : listSection){
				mapSectionIdDesignation.put(section.getId(), section.getDesignation());
			}
			
			// Map< operationId , operationDesignation >
			Map<Long, String> mapOperationIdDesignation = new HashMap<Long, String>();
			
			// Map< operationId , operationCode >
			Map<Long, String> mapOperationIdCode = new HashMap<Long, String>();
			
			List<OperationValue> listOperation = operationPersistance.getAll();
			
			for(OperationValue operation : listOperation){
				mapOperationIdDesignation.put(operation.getId(), operation.getDesignation());
			}
			
			for(OperationValue operation : listOperation){
				mapOperationIdCode.put(operation.getId(), operation.getCode());
			}
			
			List<ElementGammeOfValue> listElementGammeOf = new ArrayList<ElementGammeOfValue>();
			
			for(ElementGammeOfValue element : gammeOf.getListElementGammeOf()){
				
				if(element.getMachineId() != null){
					
					if(mapMachineIdDesignation.containsKey(element.getMachineId())){
						
						element.setMachineDesignation(mapMachineIdDesignation.get(element.getMachineId()));
					}
				}
				
				if(element.getSectionId() != null){

					if(mapSectionIdDesignation.containsKey(element.getSectionId())){
						
						element.setSectionDesignation(mapSectionIdDesignation.get(element.getSectionId()));
					}
				}
				
				if(element.getOperationId() != null){

					if(mapOperationIdDesignation.containsKey(element.getOperationId())){
						
						element.setOperationDesignation(mapOperationIdDesignation.get(element.getOperationId()));
						element.setOperationCode(mapOperationIdCode.get(element.getOperationId()));
					}
				}
				
				listElementGammeOf.add(element);
			}
			
			Collections.sort(listElementGammeOf);
			
			gammeOf.setListElementGammeOf(new TreeSet<>(listElementGammeOf));
			
		}
		
		return gammeOf;
	}

	@Override
	public void delete(Long id) {
		
		gammeOfPersistance.delete(id);
	}

	@Override
	public List<GammeOfValue> getAll() {
		
		return gammeOfPersistance.getAll();
	}

	@Override
	public ResultatRechecheGammeOfValue rechercherMultiCritere(
			RechercheMulticritereGammeOfValue request) {
		
		return gammeOfPersistance.rechercherMultiCritere(request);
	}

	@Override
	public List<OrdreFabricationValue> getOrdreFabricationListAvailable() {
		
		List<OrdreFabricationValue> result = null;

		List<GammeOfValue> currentListGammeOf = gammeOfPersistance.getAll();
		
		if(currentListGammeOf != null){
			
			List<Long> listIdUsed = new ArrayList<Long>();
			
			for(GammeOfValue gammeOf : currentListGammeOf){
				
				listIdUsed.add(gammeOf.getOrdreFabricationId());
			}
			
			result = ordreFabricationPersistance.getAllNotInList(listIdUsed);
		}
		
		return result;
	}

	@Override
	public List<OrdreFabricationValue> getOrdreFabricationListUsed() {
		
		List<OrdreFabricationValue> result = null;

		List<GammeOfValue> currentListGammeOf = gammeOfPersistance.getAll();
		
		if(currentListGammeOf != null){
			
			List<Long> listIdUsed = new ArrayList<Long>();
			
			for(GammeOfValue gammeOf : currentListGammeOf){
				
				listIdUsed.add(gammeOf.getOrdreFabricationId());
			}
			
			result = ordreFabricationPersistance.getAllInList(listIdUsed);
		}
		
		return result;
	}

	@Override
	public GammeOfValue getByOrdreFabricationId(Long ordreFabricationId) {
		
		GammeOfValue gammeOf = new GammeOfValue();
		
		OrdreFabricationValue ordreFabricationValue = ordreFabricationPersistance.rechercheOrdreFabricationParId(ordreFabricationId);
		
		if(ordreFabricationValue != null){
			
			RechercheMulticritereGammeProduitValue request = new RechercheMulticritereGammeProduitValue();
			request.setProduitId(ordreFabricationValue.getProduitId());
			
			 ResultatRechecheGammeProduitValue resultatRechecheGammeProduit = gammeProduitPersistance.rechercherMultiCritere(request);
			 
			 if(resultatRechecheGammeProduit != null){
				 
				 Set<ResultatRechecheGammeProduitElementValue> list = resultatRechecheGammeProduit.getList();
				 
				 if(!list.isEmpty()){
					 
					 ResultatRechecheGammeProduitElementValue firstElement = list.iterator().next();
					 
					 if(firstElement!=null){
						 
						 GammeProduitValue gammeProduit = gammeProduitPersistance.getById(firstElement.getId());
						 
						 if(gammeProduit != null){
							 
							 enrichmentGammeOfFromGammeProduit(gammeProduit, gammeOf);
							 
							 gammeOf.setOrdreFabricationId(ordreFabricationId);
							 
							// Map< machineId , machineDesignation >
								List<MachineValue> listMachine = machinePersistance.getAll();
								Map<Long, String> mapMachineIdDesignation = new HashMap<Long, String>();
								for(MachineValue machine : listMachine){
									mapMachineIdDesignation.put(machine.getId(), machine.getDesignation());
								}
								
								// Map< sectionId , sectionDesignation >
								List<SectionValue> listSection = sectionPersistance.getAll();
								Map<Long, String> mapSectionIdDesignation = new HashMap<Long, String>();
								for(SectionValue section : listSection){
									mapSectionIdDesignation.put(section.getId(), section.getDesignation());
								}
								
								// Map< operationId , operationDesignation >
								Map<Long, String> mapOperationIdDesignation = new HashMap<Long, String>();
								
								// Map< operationId , operationCode >
								Map<Long, String> mapOperationIdCode = new HashMap<Long, String>();
								
								List<OperationValue> listOperation = operationPersistance.getAll();
								
								for(OperationValue operation : listOperation){
									mapOperationIdDesignation.put(operation.getId(), operation.getDesignation());
								}
								
								for(OperationValue operation : listOperation){
									mapOperationIdCode.put(operation.getId(), operation.getCode());
								}
								
								List<ElementGammeOfValue> listElementGamme = new ArrayList<ElementGammeOfValue>();
								
								for(ElementGammeOfValue element : gammeOf.getListElementGammeOf()){
									
									if(element.getMachineId() != null){
										
										if(mapMachineIdDesignation.containsKey(element.getMachineId())){
											
											element.setMachineDesignation(mapMachineIdDesignation.get(element.getMachineId()));
										}
									}
									
									if(element.getSectionId() != null){

										if(mapSectionIdDesignation.containsKey(element.getSectionId())){
											
											element.setSectionDesignation(mapSectionIdDesignation.get(element.getSectionId()));
										}
									}
									
									if(element.getOperationId() != null){

										if(mapOperationIdDesignation.containsKey(element.getOperationId())){
											
											element.setOperationDesignation(mapOperationIdDesignation.get(element.getOperationId()));
											element.setOperationCode(mapOperationIdCode.get(element.getOperationId()));
										}
									}
									
									listElementGamme.add(element);
								}
								
								Collections.sort(listElementGamme);
								
								gammeOf.setListElementGammeOf(new TreeSet<>(listElementGamme));
								
						 }
						 
					 }
					 
				 }
				 
			 }
			
		}
			
		return gammeOf;
	}

	@Override
	public GammeOfValue getByOFId(Long ordreFabricationId) {
		GammeOfValue vGammeOF=gammeOfPersistance.getByOFId(ordreFabricationId);
		if (vGammeOF!=null)
		for (ElementGammeOfValue element:vGammeOF.getListElementGammeOf()){
			
			if (element.getOperationId()!=null){
				
				OperationValue operation=operationPersistance.getById(element.getOperationId());
				 if (operation!=null)
				 { element.setOperationDesignation(operation.getDesignation());
				   element.setOperationCode(operation.getCode()); 
				 }
			}
			
			
			
		}
		
		
		
		return vGammeOF;
	}

	private void enrichmentGammeOfFromGammeProduit(	GammeProduitValue gammeProduit,	GammeOfValue gammeOf) {
		
		gammeOf.setTempsTotalProduit(gammeProduit.getTempsTotal());
		gammeOf.setNbOperationProduit(gammeProduit.getNbOperation());
		gammeOf.setTempsTotal(null);
		gammeOf.setNbOperation(null);
		gammeOf.setObservations(gammeProduit.getObservations());
		gammeOf.setDate(gammeProduit.getDate());
		gammeOf.setProduitId(gammeProduit.getProduitId());
		
	    if(gammeProduit.getListElementGamme() != null){
	    	
	    	Set<ElementGammeOfValue> listElementGammeOf = new HashSet<ElementGammeOfValue>();
	    	
	    	for (ElementGammeValue elementGammeProduit : gammeProduit.getListElementGamme()) {
		    	 
		    	 ElementGammeOfValue elementGammeOf = new ElementGammeOfValue();
		    	 
		    	 elementGammeOf.setOperationId(elementGammeProduit.getOperationId());
		    	 elementGammeOf.setTemps(elementGammeProduit.getTemps());
		    	 elementGammeOf.setPdh(elementGammeProduit.getPdh());
		    	 elementGammeOf.setSectionId(elementGammeProduit.getSectionId());
		    	 elementGammeOf.setMachineId(elementGammeProduit.getMachineId());
		    	 elementGammeOf.setObservations(elementGammeProduit.getObservations());
		    	 elementGammeOf.setComptage(elementGammeProduit.getComptage());
		    	 elementGammeOf.setOrdre(elementGammeProduit.getOrdre());
		    	 
		    	 listElementGammeOf.add(elementGammeOf);
		    }
	    	gammeOf.setListElementGammeOf(listElementGammeOf);
		}
		
	}
	
	private boolean estNonVide(String val) {
		return val != null && !"".equals(val);

	}
	
	private boolean validDoubleValue(Double val) {
		return val != null && val != 0;
	}

	@Override
	public GammeOfValue validateByOrdreFabricationId(Long ordreFabricationId) {
			
		GammeOfValue gammeOf = gammeOfPersistance.getByOFId(ordreFabricationId);
		
		//enrichissementElementGammeOF
		enrichirElementGammeOF(gammeOf, ordreFabricationId);

		return gammeOf;
	}


	private void enrichirElementGammeOF(GammeOfValue gammeOf, Long ordreFabricationId) {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		// Map< operationId , operationDesignation >
		Map<Long, String> mapOperationIdDesignation = new HashMap<Long, String>();
		
		// Map< operationId , operationCode >
		Map<Long, String> mapOperationIdCode = new HashMap<Long, String>();
		
		List<OperationValue> listOperation = operationPersistance.getAll();
		
		for(OperationValue operation : listOperation){
			mapOperationIdDesignation.put(operation.getId(), operation.getDesignation());
		}
		
		for(OperationValue operation : listOperation){
			mapOperationIdCode.put(operation.getId(), operation.getCode());
		}
					
		if(gammeOf != null){
			
			
			List<ElementGammeOfValue> listElementGammeOf = new ArrayList<ElementGammeOfValue>();
				
			for(ElementGammeOfValue elementOf : gammeOf.getListElementGammeOf()){
				
				Map<Object,Long> mapQteParJour = new HashMap<Object, Long>();
				Map<DetailTailleCouleurAvancementOFValue,Long> mapQteTailleCouleur = new HashMap<DetailTailleCouleurAvancementOFValue, Long>();
				
				/**Operation**/
				if(elementOf.getOperationId() != null){

					if(mapOperationIdDesignation.containsKey(elementOf.getOperationId())){
						
						elementOf.setOperationDesignation(mapOperationIdDesignation.get(elementOf.getOperationId()));
						elementOf.setOperationCode(mapOperationIdCode.get(elementOf.getOperationId()));
					}
				}
				
				/** sommeQteProduite **/
				Long elementGammeOFId = elementOf.getId();
				
				RechercheMulticritereDetailSaisieValue request = new  RechercheMulticritereDetailSaisieValue();
				request.setElementGammeOfId(elementGammeOFId);
				
				ResultatRechecheDetailSaisieValue resultatDetailSaisie = detailSaisieDomaine.rechercherMultiCritere(request);
				Long sommeQteProduite = 0L;
				
				if(resultatDetailSaisie != null){
					 
					 Set<DetailSaisieElementValue> list = resultatDetailSaisie.getList();
					 
					 if(!list.isEmpty()){
						 
						 
						 for(DetailSaisieElementValue detail : resultatDetailSaisie.getList()){
							 
							 if(detail!=null){
								 Long qte;
								 Long qteTailleCouleur;
								 
								 /** quantiteProduite Totale */
								 sommeQteProduite += detail.getQuantite();
								 
								 /** MapQteParJour */
 								 if(detail.getDate() != null){
//									 Calendar cal = detail.getDate();
//									 SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
//
//									 String keyDate = format1.format(cal.getTime());
									 Calendar  keyDate = detail.getDate() ;
									 if(!mapQteParJour.containsKey(keyDate)){
										 mapQteParJour.put(keyDate, detail.getQuantite());
									 }else{
										 
										 qte = mapQteParJour.get(keyDate);
										
										 if(detail.getQuantite() != null){
											 qte += detail.getQuantite();
											 
										 }
										 mapQteParJour.put(keyDate, qte);
										 
									 }
 								 }
								 
								 /** Qte par Taille/Couleur */
								 if(detail.getPaquetId() != null){
									 
									 PaquetValue paquet = paquetPersistance.getById(detail.getPaquetId());
									 if(paquet != null){
										Long couleurId = paquet.getCouleurId();
										Long tailleId = paquet.getTailleId();
										
										if(couleurId != null && tailleId != null){
											if(rechercherKeyMapCouleurTaille(couleurId, tailleId, mapQteTailleCouleur ) == null){
												DetailTailleCouleurAvancementOFValue objetTailleCouleur = new DetailTailleCouleurAvancementOFValue();
												objetTailleCouleur.setCouleurId(couleurId);
												objetTailleCouleur.setTailleId(tailleId);
												
												mapQteTailleCouleur.put(objetTailleCouleur, detail.getQuantite());
												
											}else{
												DetailTailleCouleurAvancementOFValue objetTailleCouleur = rechercherKeyMapCouleurTaille(couleurId, tailleId, mapQteTailleCouleur);
												qteTailleCouleur = mapQteTailleCouleur.get(objetTailleCouleur);
												qteTailleCouleur += detail.getQuantite();
												mapQteTailleCouleur.put(objetTailleCouleur, qteTailleCouleur);
											}
										}
									 }
								 }
								 
							 }
							
						 }
						 elementOf.setQteProduite(sommeQteProduite);

					 }	
					 
				}
				/** ListeQteParJour */
				Iterator it = mapQteParJour.entrySet().iterator();
				List<JourProductionAvancementOFValue> setQteJour = new ArrayList<JourProductionAvancementOFValue>();
				while (it.hasNext()) {

					Map.Entry<Calendar, Long> pair = (Entry<Calendar, Long>) it.next();
					if(pair.getValue() != null){
						JourProductionAvancementOFValue elementQteJour = new JourProductionAvancementOFValue();
						elementQteJour.setDate(pair.getKey());//stringToCalendar(pair.getKey()));
						elementQteJour.setQtePrdteParJour(pair.getValue());
						setQteJour.add(elementQteJour);
					}
				}
				elementOf.setListJourProductionGammeOf(setQteJour);
				
				/** ListeQteTailleCouleur */
				Iterator itTailleCouleur = mapQteTailleCouleur.entrySet().iterator();
				List<DetailTailleCouleurAvancementOFValue> setQteTailleCouleur = new ArrayList<DetailTailleCouleurAvancementOFValue>();
				while (itTailleCouleur.hasNext()) {

					Map.Entry<DetailTailleCouleurAvancementOFValue, Long> pairTailleCouleur = (Entry<DetailTailleCouleurAvancementOFValue, Long>) itTailleCouleur.next();
					if(pairTailleCouleur.getKey() != null){
						DetailTailleCouleurAvancementOFValue elementQteTailleCouleur = new DetailTailleCouleurAvancementOFValue();
						elementQteTailleCouleur.setCouleurDesignation(convertCouleur(pairTailleCouleur.getKey().getCouleurId()));
						elementQteTailleCouleur.setTailleDesignation(convertTaille(pairTailleCouleur.getKey().getTailleId()));
						
						elementQteTailleCouleur.setQuantite(pairTailleCouleur.getValue());
						setQteTailleCouleur.add(elementQteTailleCouleur);
					}
				}
				elementOf.setListTailleCouleurGammeOf(setQteTailleCouleur);
				
				/** Minutage **/
				Double minutage = 0D;
				if(elementOf.getQteProduite()!= null && elementOf.getTemps() != null){
					
					minutage = elementOf.getQteProduite() * elementOf.getTemps();
				}
				elementOf.setMinutage(minutage);
				
				listElementGammeOf.add(elementOf);
			}
			Collections.sort(listElementGammeOf);
			
			gammeOf.setListElementGammeOf(new TreeSet<>(listElementGammeOf));
		}
		
	}

	private String convertTaille(Long tailleId) {
		//TODO : change methode param 
		if(tailleId != null){
			TailleValue tailleObj = new TailleValue();
			tailleObj.setId(tailleId);
			TailleValue taille = taillePersistance.rechercheTailleParId(tailleObj);
			if(taille != null)
				return taille.getDesignation();
			else
				return null;
		}else
			return null;
	}


	private String convertCouleur(Long couleurId) {
		//TODO : change methode param 
		if(couleurId != null){
			CouleurValue couleurObj = new CouleurValue();
			couleurObj.setId(couleurId);
			CouleurValue couleur = couleurPersistance.rechercheCouleurParId(couleurObj);
			if(couleur != null)
				return couleur.getDesignation();
			else
				return null;
		}else
			return null;
	}

	private DetailTailleCouleurAvancementOFValue rechercherKeyMapCouleurTaille(Long couleurId, Long tailleId,
			Map<DetailTailleCouleurAvancementOFValue, Long> pMmapQteTailleCouleur) {
		
		Iterator it = pMmapQteTailleCouleur.entrySet().iterator();
		
		DetailTailleCouleurAvancementOFValue objetTailleCouleur = null;
		
		while (it.hasNext()) {
			

			Map.Entry<DetailTailleCouleurAvancementOFValue,Long> pair = (Entry<DetailTailleCouleurAvancementOFValue,Long>) it
					.next();
			if(pair.getKey() != null){
				if((pair.getKey().getCouleurId() != null) && (pair.getKey().getTailleId() != null)){
					
					if((pair.getKey().getCouleurId().equals(couleurId))&&(pair.getKey().getTailleId().equals(tailleId))){
						objetTailleCouleur = pair.getKey();
						break;
					}
				}
				
			}
		}
		return objetTailleCouleur;
	}

	private Calendar stringToCalendar(String dateString) {
		
		Calendar dateCalendar = null;
		
		if(isNotEmty(dateString)){
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			dateCalendar = sdf.getCalendar();
			try {
				dateCalendar.setTime(sdf.parse(dateString));
				
			} catch (ParseException e) {
				logger.error("parse date exception: "+e.getMessage());
			}
		}
		
		return dateCalendar;
	}
	
	private boolean isNotEmty(String value) {
		return value != null && !"".equals(value);

	}


	@Override
	public ResultatRechecheGammeOfValue rechercherMultiCritere(RechercheMulticritereGammeOfValue request,
			boolean allegee) {
		return gammeOfPersistance.rechercherMultiCritere(request, allegee);
	}
	
	public ElementGammeOfValue rechercherElementGammeOF(Long id) {
		return gammeOfPersistance.getElementGammeOFById(id);
	}
	
	
	
	
}
