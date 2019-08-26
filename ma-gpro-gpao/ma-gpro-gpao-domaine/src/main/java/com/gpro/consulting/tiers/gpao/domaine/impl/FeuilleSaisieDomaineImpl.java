package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.domaine.baseinfo.IBaseInfoDomaine;
import com.gpro.consulting.tiers.gpao.coordination.aleas.ElementAleasValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.SaisieElementTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.SaisieElementValue;
import com.gpro.consulting.tiers.gpao.coordination.ficheeclatement.value.PaquetValue;
import com.gpro.consulting.tiers.gpao.coordination.fichesuiveuse.vue.FicheSuiveuseVue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.ElementGammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.gammeof.value.GammeOfValue;
import com.gpro.consulting.tiers.gpao.coordination.operation.value.OperationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;
import com.gpro.consulting.tiers.gpao.coordination.value.FeuilleSaisieRendementChaineComparator;
import com.gpro.consulting.tiers.gpao.coordination.value.FeuilleSaisieRendementComparator;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IFeuilleSaisieDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IFicheSuiveuseDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IGammeOfDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IChainePersistance;
import com.gpro.consulting.tiers.gpao.persitance.IOrdreFabricationPersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IDetailSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IFeuilleSaisiePersistance;
import com.gpro.consulting.tiers.gpao.persitance.feuillesaisie.IPersonnelPersistance;
import com.gpro.consulting.tiers.gpao.persitance.gammemontage.IOperationPersistance;

/**
 * implementation of {@link IFeuilleSaisieDomaine}
 * 
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */

@Component
public class FeuilleSaisieDomaineImpl implements IFeuilleSaisieDomaine {

	private static final Logger logger = LoggerFactory.getLogger(FeuilleSaisieDomaineImpl.class);

	private static final Double ZERO = 0D;
	private static final Long ZEROL = 0L;

	@Autowired
	private IFeuilleSaisiePersistance feuilleSaisiePersistance;

	@Autowired
	private IOrdreFabricationPersistance ordreFabricationPersistance;

	@Autowired
	private IFicheSuiveuseDomaine ficheSuiveuseDomaine;

	@Autowired
	private IGammeOfDomaine gammeOfDomaine;

	@Autowired
	private IOperationPersistance operationPersistance;

	@Autowired
	private IChainePersistance chainePersistance;
	
	@Autowired
	private IPersonnelPersistance personnelPersistance;

	
	@Autowired
	private IBaseInfoDomaine baseInfoDomaine;
	
	
	
	@Autowired
	private IDetailSaisiePersistance detailSaisiePersistence;
	
	
	
	@Override
	public FeuilleSaisieValue getById(Long id) {

		//LOGGER.info("getFeuilleSaisieById: Delegating request {} to Persistance layer." + id);

		FeuilleSaisieValue feuilleSaisie = feuilleSaisiePersistance.getById(id);

		if (feuilleSaisie != null) {

			if (feuilleSaisie.getChaineId() != null) {

				ChaineValue chaine = chainePersistance.rechercheChaineParId(feuilleSaisie.getChaineId());

				if (chaine != null) {

					feuilleSaisie.setChaineDesignation(chaine.getDesignation());
				}
			}
		}

		return feuilleSaisie;
	}

	@Override
	public String create(FeuilleSaisieValue feuilleSaisie) {

		//LOGGER.info("createFeuilleSaisie: Delegating request to Persistance layer.");

		List<String> listBareCode = new ArrayList<String>();

		for (SaisieElementValue element : feuilleSaisie.getListSaisie()) {

			listBareCode.add(element.getCodeBarre());
		}

		feuilleSaisie.setListBareCode(listBareCode);

		enrichmentFeuilleSaisie(feuilleSaisie);
		
		return feuilleSaisiePersistance.create(feuilleSaisie);
	}

	@Override
	public String update(FeuilleSaisieValue feuilleSaisie) {

		//LOGGER.info("updateFeuilleSaisie: Delegating request to Persistance layer.");

		List<String> listBareCode = new ArrayList<String>();

		for (SaisieElementValue element : feuilleSaisie.getListSaisie()) {

			listBareCode.add(element.getCodeBarre());
		}

		feuilleSaisie.setListBareCode(listBareCode);

		enrichmentFeuilleSaisieUpdate(feuilleSaisie);

		return feuilleSaisiePersistance.update(feuilleSaisie);
	}

	@Override
	public void delete(Long id) {

		//LOGGER.info("deleteFeuilleSaisie: Delegating request {} to Persistance layer." + id);

		feuilleSaisiePersistance.delete(id);
	}

	@Override
	public ResultatRechecheFeuilleSaisieValue rechercherMultiCritere(RechercheMulticritereFeuilleSaisieValue request) {


		ResultatRechecheFeuilleSaisieValue result = feuilleSaisiePersistance.rechercherMultiCritere(request);

		if (result != null) {

			for (ResultatRechecheFeuilleSaisieElementValue element : result.getList()) {

				if (element.getChaineId() != null) {

					ChaineValue chaine = chainePersistance.rechercheChaineParId(element.getChaineId());

					if (chaine != null) {

						element.setChaineDesignation(chaine.getDesignation());
					}
				}
			}
		}

		return result;
	}

	@Override
	public FeuilleSaisieValue validate(FeuilleSaisieValue feuilleSaisie) {

		enrichmentFeuilleSaisie(feuilleSaisie);

		// enrichmentFeuilleSaisieUpdate(feuilleSaisie);

		return feuilleSaisie;
	}

	@Override
	public List<PersonnelValue> listPersonnel() {

		//LOGGER.info("listPersonnel: Delegating request to Persistance layer.");

		return feuilleSaisiePersistance.listPersonnel();
	}

	
	//TODO 
	//VALIDER POUR SOPROLIN 
	
	private void enrichmentFeuilleSaisie(FeuilleSaisieValue feuilleSaisie) {

		BaseInfoValue baseInfo=baseInfoDomaine.getClientActif();
		
		
		if (feuilleSaisie.getListBareCode() != null) {

			List<SaisieElementValue> newListSaisieElement = new ArrayList<SaisieElementValue>();

			Map<String, Long> mapBareCodeSaisieElementId = new HashMap<String, Long>();

			Double minutageTotal = ZERO;
			Double rendement = null;
			Double activite = null;
			Long pieceProd = 0L;

			// add the bareCode already created to the listSaisie
			if (feuilleSaisie.getId() != null) {

				FeuilleSaisieValue fs = feuilleSaisiePersistance.getById(feuilleSaisie.getId());

				if (fs.getListSaisie() != null) {

					for (SaisieElementValue elementSaisie : fs.getListSaisie()) {

						mapBareCodeSaisieElementId.put(elementSaisie.getCodeBarre(), elementSaisie.getId());

					}
				}
			}

			for (String bareCode : feuilleSaisie.getListBareCode()) {

				if (bareCode != null && bareCode.length() == 10) {

					SaisieElementValue element = new SaisieElementValue();

					// bareCode pattern : idOF(4) + ordrePaquet(3) +
					// ordreEGOF(3)
					// exmp: bareCode = 0001002003
					// idOF = 1
					// ordrePaquet = 2
					// ordreEGOF = 3

				
					//TODO SOPROLIN  String test = rang_of  + op  + of1;
					
					// Autres 
					
					/*
					 * 
					 * String idOFString = bareCode.substring(0, 4);
					   String ordrePaquetString = bareCode.substring(4, 7);
					   String ordreEGOFString = bareCode.substring(7, 10);
					 */
					
					
					// TODO SOPROLIN  
					String idOFString = bareCode.substring(5);
					String ordrePaquetString = bareCode.substring(0,3);
					String ordreEGOFString = bareCode.substring(3,5);

					
//					//System.out.println("###  idOFString" + idOFString);
//					//System.out.println("####   ordrePaquetString   " + ordrePaquetString);
//					//System.out.println("####   ordreEGOFString  "+ordreEGOFString);
//					
					
					try {

						Long idOF = Long.parseLong(idOFString);
						Long ordrePaquet = Long.parseLong(ordrePaquetString);
						Long ordreEGOF = Long.parseLong(ordreEGOFString);

						
						
						GammeOfValue gammeOf = gammeOfDomaine.getByOFId(idOF);

						if (gammeOf != null) {

							for (ElementGammeOfValue elementGammeOf : gammeOf.getListElementGammeOf()) {

								if (elementGammeOf.getOrdre().equals(ordreEGOF)) {

									if (elementGammeOf.getOperationId() != null) {

										OperationValue operation = operationPersistance
												.getById(elementGammeOf.getOperationId());

										if (operation != null) {

											element.setOperationCode(operation.getCode());
											element.setOperationDesignation(operation.getDesignation());
											element.setIdOperation(operation.getId());
										}
									}

									element.setElementGammeId(elementGammeOf.getId());
									element.setTemps(elementGammeOf.getTemps());
                                    element.setComptage(elementGammeOf.getComptage());
								}
							}
						}
						
						
						
						
						
						OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
								.rechercheOrdreFabricationParId(idOF);

						if (ordreFabrication != null) {

							element.setOrdreFabricationNumero(ordreFabrication.getNumero());
						    element.setIdOF(ordreFabrication.getId());
						    element.setIdClient(ordreFabrication.getPartieInterresId());
						    element.setIdProduit(ordreFabrication.getProduitId());
						
						    // Update suivi OF
						    
						    
						    
						    //TODO   Commentaire update Suivi OF 
						    
						    
//						    if (element.getComptage()!=null)
//						      if (element.getComptage()==true)
//						    {    Long eclatement=baseInfo.getEclatement(); 
//						    Long engagement=baseInfo.getEngagement();
//						    Long sortieCpe=baseInfo.getSortieCoupe();
//						    Long sortieChaine=baseInfo.getSortieChaine();
//						    Long suppl1=baseInfo.getSupp1();
//						    Long supp2=baseInfo.getSupp2();
//						    Long conditionnement=baseInfo.getConditionnement();
//						    	  
//						    	  
//						    	  
//						    	  
//					
//						    	  
//						    	  if(element.getIdOperation().equals(eclatement)) {
//								
//									  
//									Long qte=ordreFabrication.getQtSupp3();
//									ordreFabrication.setQtSupp3(qte+element.getQuantite());
//									
//								}
//						    	  
//						    	  
//						    	  if(element.getIdOperation().equals(engagement)) {
//								
//									  
//									Long qte=ordreFabrication.getQtEngagement();
//									ordreFabrication.setQtEngagement(qte+element.getQuantite());
//									
//								} 
//						    	  
//						    	  
//						    	  if(element.getIdOperation().equals(sortieChaine)) {
//								     
//						    		  System.out.println("###### ENTER TO SORTIE");
//									  
//									Long qte=ordreFabrication.getQtSortie();
//									ordreFabrication.setQtSortie(qte+element.getQuantite());
//									
//								} 
//						    	
//						    	  if(element.getIdOperation().equals(sortieCpe)) {
//										
//									  
//										Long qte=ordreFabrication.getQtCoupe();
//										ordreFabrication.setQtCoupe(qte+element.getQuantite());
//										
//									} 
//						    	  
//						    	  
//						    	
//						    	  if(element.getIdOperation().equals(conditionnement)) {
//										
//									  
//										Long qte=ordreFabrication.getQtColisage();
//										ordreFabrication.setQtColisage(qte+element.getQuantite());
//										
//									} 
//						    	  
//						    	  
//						    	  if(element.getIdOperation().equals(suppl1)) {
//										
//									  
//										Long qte=ordreFabrication.getQtSupp1();
//										ordreFabrication.setQtSupp1(qte+element.getQuantite());
//										
//									} 
//						    	  
//						    	  
//						    	
//						    	  if(element.getIdOperation().equals(supp2)) {
//										
//									  
//										Long qte=ordreFabrication.getQtSupp2();
//										ordreFabrication.setQtSupp2(qte+element.getQuantite());
//										
//									} 
//						    	  
//						    	  ordreFabricationPersistance.modifierOrdreFabrication(ordreFabrication);
//						    	  
//						    	  
//						    }
						
						
						
						
						
						}

						FicheSuiveuseVue ficheSuiveuse = ficheSuiveuseDomaine.getByOrdreFabricationId(idOF);

						if (ficheSuiveuse != null) {

							for (PaquetValue paquet : ficheSuiveuse.getPaquetsList()) {

								if (paquet.getOrdre().equals(ordrePaquet)) {

									element.setPaquetId(paquet.getId());
									element.setQuantite(paquet.getQuantite());
									element.setPaquetNum(paquet.getNum());
									element.setOrdre(paquet.getOrdre());
								}
							}
						
						
						////System.out.println("Paquet  qte :    "+element.getQuantite());
						////System.out.println("####  element.setPaquetId(paquet.getId());  "+element.getPaquetId());
						
						}

						

						element.setCodeBarre(bareCode);
						if (!feuilleSaisie.isDirecte()) {
							if (element.getQuantite() != null && element.getTemps() != null) {

								Double minutage = element.getQuantite() * element.getTemps();

								element.setMinutage(minutage);

								minutageTotal = minutageTotal + minutage;

								pieceProd = pieceProd + element.getQuantite();
							}
						}
						if (mapBareCodeSaisieElementId.containsKey(element.getCodeBarre())) {

							element.setId((mapBareCodeSaisieElementId.get(element.getCodeBarre())));
							newListSaisieElement.add(element);
						
						
						}
						
						else {
							if (detailSaisiePersistence.codeExistence(element.getCodeBarre())==false)
								newListSaisieElement.add(element);
						}

						

					} catch (NumberFormatException e) {

						logger.error("FeuilleSaisie- validate - NumberFormatException: " + e.getMessage());
					}

				}
			}

			feuilleSaisie.setMinProd(minutageTotal);
			feuilleSaisie.setPscProd(pieceProd);

		
			Double sumDuree = 0.0D;

			if (feuilleSaisie.getListElementAleas() != null) {
				for (ElementAleasValue element : feuilleSaisie.getListElementAleas()) {
					if (element.getDuree() != null) {
						sumDuree += element.getDuree();
					}

				}
			}

			feuilleSaisie.setMinAleas(sumDuree);
			
			
			
			
			
			
			if (feuilleSaisie.getMinProd() != null && feuilleSaisie.getMinPresence() != null
					&& feuilleSaisie.getMinPresence() != 0) {

				rendement = (feuilleSaisie.getMinProd() / feuilleSaisie.getMinPresence()) * 100;

			}

			boolean validDenominateurAndNumerateur = false;

			if (feuilleSaisie.getMinProd() != null && feuilleSaisie.getMinPresence() != null
					&& feuilleSaisie.getMinAleas() != null
					&& (feuilleSaisie.getMinPresence() - feuilleSaisie.getMinAleas() != 0)) {

				validDenominateurAndNumerateur = true;
			}

			if (validDenominateurAndNumerateur) {

				Double numerateur = feuilleSaisie.getMinProd();
				Double denominateur = feuilleSaisie.getMinPresence() - feuilleSaisie.getMinAleas();

				activite = (numerateur / denominateur) * 100;
			}

			feuilleSaisie.setRendement(rendement);
			feuilleSaisie.setActivite(activite);

			Collections.sort(newListSaisieElement);
			feuilleSaisie.setListSaisie(new TreeSet<>(newListSaisieElement));
		}
		
	}

	private void enrichmentFeuilleSaisieUpdate(FeuilleSaisieValue feuilleSaisie) {

		if (feuilleSaisie.getListBareCode() != null) {

			List<SaisieElementValue> listSaisie = new ArrayList<SaisieElementValue>();

			List<String> listBareCodeExist = new ArrayList<String>();
			List<String> listBareCodeNotExist = new ArrayList<String>();

			Double minutageTotal = ZERO;
			Double rendement = null;
			Double activite = null;
			Long pieceProd = 0L;

			// add the bareCode already created to the listSaisie
			if (feuilleSaisie.getId() != null) {

				FeuilleSaisieValue fs = feuilleSaisiePersistance.getById(feuilleSaisie.getId());

				if (fs.getListSaisie() != null) {

					for (SaisieElementValue elementSaisie : fs.getListSaisie()) {

						listBareCodeExist.add(elementSaisie.getCodeBarre());

						// listSaisie.add(elementSaisie);
					}

				}
			}

			// for(String bareCode : listBareCodeExist){
			//
			// feuilleSaisie.getListBareCode().remove(bareCode);
			//
			// }

			for (String bareCode : feuilleSaisie.getListBareCode()) {

				if (bareCode != null && bareCode.length() == 10) {

					SaisieElementValue element = new SaisieElementValue();

					// bareCode pattern : idOF(4) + ordrePaquet(3) +
					// ordreEGOF(3)
					// exmp: bareCode = 0001002003
					// idOF = 1
					// ordrePaquet = 2
					// ordreEGOF = 3

					// TODO SOPROLIN  
					String idOFString = bareCode.substring(5);
					String ordrePaquetString = bareCode.substring(0,3);
					String ordreEGOFString = bareCode.substring(3,5);

					try {

						Long idOF = Long.parseLong(idOFString);
						Long ordrePaquet = Long.parseLong(ordrePaquetString);
						Long ordreEGOF = Long.parseLong(ordreEGOFString);

						OrdreFabricationValue ordreFabrication = ordreFabricationPersistance
								.rechercheOrdreFabricationParId(idOF);

						if (ordreFabrication != null) {

							element.setOrdreFabricationNumero(ordreFabrication.getNumero());
							element.setIdOF(ordreFabrication.getId());
						    element.setIdClient(ordreFabrication.getPartieInterresId());
						    element.setIdProduit(ordreFabrication.getProduitId());
						
						
						
						}

						FicheSuiveuseVue ficheSuiveuse = ficheSuiveuseDomaine.getByOrdreFabricationId(idOF);

						if (ficheSuiveuse != null) {

							for (PaquetValue paquet : ficheSuiveuse.getPaquetsList()) {

								if (paquet.getOrdre().equals(ordrePaquet)) {

									element.setPaquetId(paquet.getId());
									element.setQuantite(paquet.getQuantite());
								element.setPaquetNum(paquet.getNum());
								element.setOrdre(paquet.getOrdre());
								}
							}
						}

						GammeOfValue gammeOf = gammeOfDomaine.getByOFId(idOF);

						if (gammeOf != null) {

							for (ElementGammeOfValue elementGammeOf : gammeOf.getListElementGammeOf()) {

								if (elementGammeOf.getOrdre().equals(ordreEGOF)) {

									if (elementGammeOf.getOperationId() != null) {

										OperationValue operation = operationPersistance
												.getById(elementGammeOf.getOperationId());

										if (operation != null) {

											element.setOperationCode(operation.getCode());
											element.setOperationDesignation(operation.getDesignation());
											element.setIdOperation(operation.getId());
										}
									}

									element.setElementGammeId(elementGammeOf.getId());
									element.setTemps(elementGammeOf.getTemps());
                                    element.setComptage(elementGammeOf.getComptage());
								
								}
							}
						}

						element.setCodeBarre(bareCode);
						if (!feuilleSaisie.isDirecte()) {
							if (element.getQuantite() != null && element.getTemps() != null) {

								Double minutage = element.getQuantite() * element.getTemps();

								element.setMinutage(minutage);

								minutageTotal = minutageTotal + minutage;

								pieceProd = pieceProd + element.getQuantite();
							}
						}
						listSaisie.add(element);

					} catch (NumberFormatException e) {

						logger.error("FeuilleSaisie- validate - NumberFormatException: " + e.getMessage());
					}

				}
			}

			feuilleSaisie.setMinProd(minutageTotal);
			feuilleSaisie.setPscProd(pieceProd);

	
			
			Double sumDuree = 0.0D;

			if (feuilleSaisie.getListElementAleas() != null) {
				for (ElementAleasValue element : feuilleSaisie.getListElementAleas()) {
					if (element.getDuree() != null) {
						sumDuree += element.getDuree();
					}

				}
			}

			feuilleSaisie.setMinAleas(sumDuree);
			
			
			
			
			if (feuilleSaisie.getMinProd() != null && feuilleSaisie.getMinPresence() != null
					&& feuilleSaisie.getMinPresence() != 0) {

				rendement = (feuilleSaisie.getMinProd() / feuilleSaisie.getMinPresence()) * 100;

			}

			boolean validDenominateurAndNumerateur = false;

			if (feuilleSaisie.getMinProd() != null && feuilleSaisie.getMinPresence() != null
					&& feuilleSaisie.getMinAleas() != null
					&& (feuilleSaisie.getMinPresence() - feuilleSaisie.getMinAleas() != 0)) {

				validDenominateurAndNumerateur = true;
			}

			if (validDenominateurAndNumerateur) {

				Double numerateur = feuilleSaisie.getMinProd();
				Double denominateur = feuilleSaisie.getMinPresence() - feuilleSaisie.getMinAleas();

				activite = (numerateur / denominateur) * 100;
			}

			feuilleSaisie.setRendement(rendement);
			feuilleSaisie.setActivite(activite);

			Collections.sort(listSaisie);
			feuilleSaisie.setListSaisie(new TreeSet<>(listSaisie));
		}
		
	

	}

	public boolean updatePscProdForAllFeuilleSaisie() {

		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();
		ResultatRechecheFeuilleSaisieValue result = feuilleSaisiePersistance.rechercherMultiCritere(request);

		if (result != null) {

			for (ResultatRechecheFeuilleSaisieElementValue element : result.getList()) {

				FeuilleSaisieValue feuilleSaisie = feuilleSaisiePersistance.getById(element.getId());

				if (feuilleSaisie != null) {

					Long pscProd = ZEROL;

					for (SaisieElementValue elementSaisie : feuilleSaisie.getListSaisie()) {

						if (elementSaisie.getQuantite() != null) {

							pscProd = pscProd + elementSaisie.getQuantite();
						}
					}

					feuilleSaisie.setPscProd(pscProd);
					feuilleSaisiePersistance.update(feuilleSaisie);
				}
			}
		}

		return true;

	}

	@Override
	public void addOrUpdateFeuilleSaisieTR(FeuilleSaisieTRValue feuilleSaisieTR) {
		
		Collections.sort( feuilleSaisieTR.getListSaisieTR());
		Collections.reverse(feuilleSaisieTR.getListSaisieTR());
		// Création de la feuille saisie à partir de feuille saisie TR
	
		Map<Long, FeuilleSaisieValue> map = new HashMap<Long, FeuilleSaisieValue>();
		
		for (SaisieElementTRValue element : feuilleSaisieTR.getListSaisieTR()) {
			
			PersonnelValue personnel = this.personnelPersistance.getByMatricule(element.getMatricule());
			
			String code = element.getCode();
			String formattedCode =code;
			
			if(code.length()<10 && code.length()>1){
				for(int i=0; i<10 - code.length() ; i++){
					formattedCode = "0".concat(formattedCode);
				}
			}
			
			if(map.containsKey(personnel.getId())){
				
				FeuilleSaisieValue feuilleSaisie = map.get(personnel.getId());
				List<String> listCodeBarre = feuilleSaisie.getListBareCode();
				
				
				SaisieElementValue elementSaisie = new SaisieElementValue();
				elementSaisie.setCodeBarre(formattedCode);
			
				Set<SaisieElementValue> listSaisie = feuilleSaisie.getListSaisie();
				listSaisie.add(elementSaisie);
				
				feuilleSaisie.setListSaisie(listSaisie);
				
				
				listCodeBarre.add(formattedCode);
				feuilleSaisie.setListBareCode(listCodeBarre);
				
				map.remove(personnel.getId());
				map.put(personnel.getId(), feuilleSaisie);
			}else{
				
				//Créer une nouvelle feuille de saisie dans le map
				FeuilleSaisieValue feuilleSaisie = new FeuilleSaisieValue();
				
				feuilleSaisie.setChaineId(feuilleSaisieTR.getChaineId());
				feuilleSaisie.setMinPresence(feuilleSaisieTR.getMinPresence());
				feuilleSaisie.setDateSaisie(element.getDate());
				feuilleSaisie.setPersonnelId(personnel.getId());
				
				Set<SaisieElementValue> listSaisie = new HashSet<SaisieElementValue>();
				SaisieElementValue elementSaisie = new SaisieElementValue();
				elementSaisie.setCodeBarre(formattedCode);
				listSaisie.add(elementSaisie);
				feuilleSaisie.setListSaisie(listSaisie);
				
				List<String> listCodeBarre = new ArrayList<>();
				listCodeBarre.add(formattedCode);
				feuilleSaisie.setListBareCode(listCodeBarre);
				map.put(personnel.getId(), feuilleSaisie);
			}
		}
		Iterator it = map.entrySet().iterator();

		while(it.hasNext()){
			Map.Entry <Long , FeuilleSaisieValue> pair = (Map.Entry<Long, FeuilleSaisieValue>)it.next();

			FeuilleSaisieValue feuilleSaisie =pair.getValue();
			FeuilleSaisieValue existentFeuilleSaisie = this.feuilleSaisiePersistance.checkExistence(feuilleSaisie);
			
			if(existentFeuilleSaisie != null){
				// Update the object
				
				Set<SaisieElementValue> newElementSaisieList = existentFeuilleSaisie.getListSaisie();
				newElementSaisieList.addAll(feuilleSaisie.getListSaisie());
				
				existentFeuilleSaisie.setListSaisie(newElementSaisieList);
				
				List<String> listBareCode = new ArrayList<String>();

				for (SaisieElementValue element : existentFeuilleSaisie.getListSaisie()) {

					listBareCode.add(element.getCodeBarre());
				}
				
				existentFeuilleSaisie.setListBareCode(listBareCode);
				enrichmentFeuilleSaisieUpdate(existentFeuilleSaisie);

				this.feuilleSaisiePersistance.update(existentFeuilleSaisie);
			}else{
				// Create a new object
				feuilleSaisie.setListBareCode(feuilleSaisie.getListBareCode());
				
				enrichmentFeuilleSaisie(feuilleSaisie);
				
				this.feuilleSaisiePersistance.create(feuilleSaisie);
			}
		}
		
	}

	@Override
	public List<Entry<String, Double>> rendementChaineParMatricule(Calendar dateDebut, Calendar dateFin, Long chaineId) {
		
		Map<String, Double> mapRendementMatricule = new HashMap<String, Double>();

		Map<String, Integer> mapMatriculeNbreFeuilles = new HashMap<String, Integer>();

		
		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();

		request.setDateSaisieMax(dateFin);
		request.setDateSaisieMin(dateDebut);
		request.setChaineId(chaineId);
		
		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request);

		Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());
		
				for (ResultatRechecheFeuilleSaisieElementValue elementFicheSaisie : set) {
					
					
					if (elementFicheSaisie.getPersonnelMatricule() != null
							&& !personnelPersistance.personnelIsIndirect(elementFicheSaisie.getPersonnelMatricule())) {
						String matriculeKey = elementFicheSaisie.getPersonnelMatricule();

						if (mapRendementMatricule.get(matriculeKey) == null) {
							mapRendementMatricule.put(matriculeKey, 0.0);
							mapMatriculeNbreFeuilles.put(matriculeKey, 1);

						}
						
						//MAJ somme du rendement
						Double rendementTemp = mapRendementMatricule.get(matriculeKey);
						
						if(rendementTemp != 0){
							mapRendementMatricule.remove(matriculeKey);
							mapRendementMatricule.put(matriculeKey, rendementTemp + elementFicheSaisie.getRendement());
							
							//MAJ somme du rendement
							Integer nbFeuillesTemp = mapMatriculeNbreFeuilles.get(matriculeKey);
							nbFeuillesTemp+=1;
							mapMatriculeNbreFeuilles.remove(matriculeKey);
							mapMatriculeNbreFeuilles.put(matriculeKey, nbFeuillesTemp);
						}else{
							mapRendementMatricule.remove(matriculeKey);
							mapRendementMatricule.put(matriculeKey, elementFicheSaisie.getRendement());
						}
						
					}
					
				}
				
				Iterator it = mapRendementMatricule.entrySet().iterator();
				
				while (it.hasNext()) {

					Iterator it2 = mapMatriculeNbreFeuilles.entrySet().iterator();

					
					Map.Entry<String, Double> pair = (Entry<String, Double>) it.next();
					
					String matricule = pair.getKey();
					Double rendement = pair.getValue();
					
					while(it2.hasNext()){
						
						Map.Entry<String, Integer> pair2 = (Entry<String, Integer>) it2.next();
						
						if(pair2.getKey().equals(matricule)){
							rendement = rendement/ pair2.getValue();
							
							break;
						}
					}
					
					pair.setValue(rendement);
					
				}
				
				//Sort by keys (matricule)
				
				/*TreeMap<String, Double> sortedMapRendementMatricule = new TreeMap<String, Double>(mapRendementMatricule); 
		
				Map<String, Double> descendentOrder = sortedMapRendementMatricule.descendingMap();*/
				
				
				
				//Sort by values (Rendement)
				Set<Entry<String, Double>> rendementSet = mapRendementMatricule.entrySet();
				List<Entry<String, Double>> sortedList = new ArrayList<Entry<String, Double>>(rendementSet);
				Collections.sort(sortedList, new FeuilleSaisieRendementComparator());
				
		return sortedList;
	}

	@Override
	public Map<Date, Double> rendementMatriculeParJour(Calendar dateDebut, Calendar dateFin, String matricule) {

		//SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yy");
		
		Map<Date, Double> mapRendementMatricule = new HashMap<Date, Double>();

		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();

		request.setDateSaisieMax(dateFin);
		request.setDateSaisieMin(dateDebut);
		request.setMatricule(matricule);
		
		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request);

		Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());
		
				for (ResultatRechecheFeuilleSaisieElementValue elementFicheSaisie : set) {
					
					if(elementFicheSaisie.getRendement() != 0){
						mapRendementMatricule.put(elementFicheSaisie.getDateSaisie().getTime(), elementFicheSaisie.getRendement());
						
					}
					
				}
				

				//Set<Entry<Date, Double>> rendementSet = mapRendementMatricule.entrySet();
				//List<Entry<Date, Double>> sortedList = new ArrayList<Entry<Date, Double>>(rendementSet);
				
				//Map.Entry<Calendar, Double> sortedMapRendementMatricule = new Map.Entry<Calendar, Double>()
						
				Map<Date, Double> sortedList = new TreeMap<Date, Double>(mapRendementMatricule); 

				//Collections.sort(sortedList,  new FeuilleSaisieRendementParJourComparator());
				//Map<Calendar, Double> finalSortedMapRendementMatricule = new HashMap<Calendar, Double>(); 

//				Iterator it = sortedList.iterator();
//				
//				while(it.hasNext()){
//					
//					Map.Entry<Calendar, Double> pair = (Entry<Calendar, Double>) it.next();
//					
//					finalSortedMapRendementMatricule.put(formatter.format(pair.getKey().getTime()), pair.getValue());
//		
//				}
				return sortedList;
	}
	
	@Override
	public Map<Date, Double> rendementChaineParJour(Calendar dateDebut, Calendar dateFin, Long chaineId) {

		Map<Date, Double> mapRendementChaineParJour = new HashMap<Date, Double>();
		Map<Date, Double> mapRendementChaineParJour2 = new HashMap<Date, Double>();

		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();

		request.setDateSaisieMax(dateFin);
		request.setDateSaisieMin(dateDebut);
		request.setChaineId(chaineId);
		
		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request);

		Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());
		
				for (ResultatRechecheFeuilleSaisieElementValue elementFicheSaisie : set) {
					
					Date dateKey = elementFicheSaisie.getDateSaisie().getTime();
					if(elementFicheSaisie.getRendement() != 0){
						if (mapRendementChaineParJour.get(dateKey) == null) {
							mapRendementChaineParJour.put(dateKey, -1.0);
							mapRendementChaineParJour2.put(dateKey, -1.0);

						}
						
						//MAJ somme du rendement
						Double sommeMinProdTemp = mapRendementChaineParJour.get(dateKey);
						Double sommeMinPresTemp = mapRendementChaineParJour2.get(dateKey);
						
						if(sommeMinProdTemp > 0){
							
							mapRendementChaineParJour.remove(dateKey);
							mapRendementChaineParJour.put(dateKey, sommeMinProdTemp + elementFicheSaisie.getMinProd());
							
						}else{
							mapRendementChaineParJour.remove(dateKey);
							mapRendementChaineParJour.put(dateKey, elementFicheSaisie.getMinProd());
						}
						
						if(sommeMinPresTemp > 0){
							mapRendementChaineParJour2.remove(dateKey);
							mapRendementChaineParJour2.put(dateKey, sommeMinPresTemp + elementFicheSaisie.getMinPresence());
						}else{
							mapRendementChaineParJour2.remove(dateKey);
							mapRendementChaineParJour2.put(dateKey, elementFicheSaisie.getMinPresence());
						}
					}
				
				}
				
				
				Iterator it = mapRendementChaineParJour.entrySet().iterator();
				
				while (it.hasNext()) {

					Iterator it2 = mapRendementChaineParJour2.entrySet().iterator();

					
					Map.Entry<Date, Double> pair = (Entry<Date, Double>) it.next();
					
					Date dateSaisie = pair.getKey();
					Double sommeMinProd = pair.getValue();
					Double rendement = 0.0;
					
					while(it2.hasNext()){
						
						Map.Entry<Date, Double> pair2 = (Entry<Date, Double>) it2.next();
						
						if(pair2.getKey().equals(dateSaisie)){ 
							Double sommeMinPresence = pair2.getValue();
							
							if(sommeMinPresence > 0){
								rendement = sommeMinProd/ sommeMinPresence;
							}
							
							
							break;
						}
					}
					
					pair.setValue(rendement);
					
					
				}
				
				Map<Date, Double> sortedList = new TreeMap<Date, Double>(mapRendementChaineParJour); 

				return sortedList;
	}

	@Override
	public List<Entry<Long, Double>>  recapRendementChaine(Calendar dateDebut, Calendar dateFin) {
		Map<Long, Double> mapRendementChaine = new HashMap<Long, Double>();

		Map<Long, Integer> mapChaineNbreFeuilles = new HashMap<Long, Integer>();

		
		RechercheMulticritereFeuilleSaisieValue request = new RechercheMulticritereFeuilleSaisieValue();

		request.setDateSaisieMax(dateFin);
		request.setDateSaisieMin(dateDebut);
		
		ResultatRechecheFeuilleSaisieValue resultRecherche = feuilleSaisiePersistance.rechercherMultiCritere(request);

		Set<ResultatRechecheFeuilleSaisieElementValue> set = new TreeSet<ResultatRechecheFeuilleSaisieElementValue>(
				resultRecherche.getList());
		
				for (ResultatRechecheFeuilleSaisieElementValue elementFicheSaisie : set) {
				
						Long chaineKey = elementFicheSaisie.getChaineId();

						if (mapRendementChaine.get(chaineKey) == null) {
							mapRendementChaine.put(chaineKey, 0.0);
							mapChaineNbreFeuilles.put(chaineKey, 1);

						}
						
						//MAJ somme du rendement
						Double rendementTemp = mapRendementChaine.get(chaineKey);
						
						if(rendementTemp != 0){
							mapRendementChaine.remove(chaineKey);
							mapRendementChaine.put(chaineKey, rendementTemp + elementFicheSaisie.getRendement());
							
							//MAJ somme du rendement
							Integer nbFeuillesTemp = mapChaineNbreFeuilles.get(chaineKey);
							nbFeuillesTemp+=1;
							mapChaineNbreFeuilles.remove(chaineKey);
							mapChaineNbreFeuilles.put(chaineKey, nbFeuillesTemp);
						}else{
							mapRendementChaine.remove(chaineKey);

							if(elementFicheSaisie.getRendement() != 0){
								mapRendementChaine.put(chaineKey, elementFicheSaisie.getRendement());
							}
						}
						
					
				}
				
				Iterator it = mapRendementChaine.entrySet().iterator();
				
				while (it.hasNext()) {

					Iterator it2 = mapChaineNbreFeuilles.entrySet().iterator();

					
					Map.Entry<Long, Double> pair = (Entry<Long, Double>) it.next();
					
					Long chaineId = pair.getKey();
					Double rendement = pair.getValue();
					
					while(it2.hasNext()){
						
						Map.Entry<Long, Integer> pair2 = (Entry<Long, Integer>) it2.next();
						
						if(pair2.getKey().equals(chaineId)){
							rendement = rendement/ pair2.getValue();
							
							break;
						}
					}
					
					pair.setValue(rendement);
					
				}
				
				//Sort by values (Rendement)
				Set<Entry<Long, Double>> rendementSet = mapRendementChaine.entrySet();
				List<Entry<Long, Double>> sortedList = new ArrayList<Entry<Long, Double>>(rendementSet);
				Collections.sort(sortedList, new FeuilleSaisieRendementChaineComparator());
				
		return sortedList;
	}

}
