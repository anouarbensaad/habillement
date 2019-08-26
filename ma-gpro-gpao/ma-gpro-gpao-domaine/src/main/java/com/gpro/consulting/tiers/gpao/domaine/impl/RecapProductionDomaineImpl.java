package com.gpro.consulting.tiers.gpao.domaine.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IPartieInteresseeDomaine;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.RechercheMulticritereRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.recapproduction.value.ResultatRechecheRecapProductionValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;
import com.gpro.consulting.tiers.gpao.domaine.IOrdreFabricationDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IRecapProductionDomaine;
import com.gpro.consulting.tiers.gpao.persitance.IStatutPersistance;

/**
 * implementation of {@link IRecapProductionDomaine}
 * 
 * @author Wahid Gazzah
 * @since 21/06/2016
 *
 */

@Component
public class RecapProductionDomaineImpl implements IRecapProductionDomaine{

	private static final Logger logger = LoggerFactory.getLogger(RecapProductionDomaineImpl.class);
	
	@Autowired
	private IOrdreFabricationDomaine ordreFabricationDomaine;
	
	@Autowired
	private IPartieInteresseeDomaine partieInteresseeDomaine;
	
	@Autowired
	private IProduitDomaine produitDomaine;
	
	@Autowired
	private IStatutPersistance statutPersistence;
	
	@Override
	public ResultatRechecheRecapProductionValue rechercherMultiCritere(
			RechercheMulticritereRecapProductionValue request) {
		
		ResultatRechecheRecapProductionValue result = new ResultatRechecheRecapProductionValue();
		
		List<RecapProductionValue> listRecapProduction = new ArrayList<RecapProductionValue>();
		
		RechercheMulticritereOrdreFabricationValue requestOF = new RechercheMulticritereOrdreFabricationValue();
		requestOF.setClientId(request.getPartieInteresseId());
		//request.get
		requestOF.setProduitId(request.getProduitId());
		requestOF.setDateLivraisonDu(request.getDateLivraisonMin());
		requestOF.setDateLivraisonTo(request.getDateLivraisonMax());
		requestOF.setvDateIntroductionAu(request.getDateIntroductionMax());
		requestOF.setvDateIntroductionDu(request.getDateIntroductionMin());
		if (request.getStatutId()!= null) {
//			StatutOfValue statut = statutPersistence.rechercheStatutParId(request.getStatutId());
//			if (statut != null) {
				requestOF.setvEtat(request.getStatutId().toString());;
				
//			}
		}
		
		
		ResultatMulticritereOrdreFabricationValue resultOF = ordreFabricationDomaine.rechercherOrdreFabricationMultiCritere(requestOF);
		
		
		for(OrdreFabricationValue of : resultOF.getOrdreFabricationValues()){
			
			RecapProductionValue recapProduction = new RecapProductionValue();
			
			recapProduction.setId(of.getId());
			recapProduction.setNumero(of.getNumero());
			recapProduction.setQuantite(of.getQuantite());
			recapProduction.setPartieInterresId(of.getPartieInterresId());
			recapProduction.setProduitId(of.getProduitId());
			recapProduction.setDateLivraison(of.getDateLivraison());
			recapProduction.setDateIntroduction(of.getDateIntroduction());
			
			recapProduction.setOfId(of.getId());
			
			if(of.getPartieInterresId() != null){
				PartieInteresseValue partieInteresse = partieInteresseeDomaine.getById(of.getPartieInterresId());
				if(partieInteresse != null){
					recapProduction.setPartieInterresAbreviation(partieInteresse.getAbreviation());
				}
			}
			
			if(of.getProduitId() != null){
				ProduitValue produit = produitDomaine.rechercheProduitById(of.getProduitId());
				if(produit != null){
					recapProduction.setProduitReference(produit.getReference());
					recapProduction.setProduitDesignation(produit.getDesignation());
				}
			}
			
			listRecapProduction.add(recapProduction);
		}
		
		
		Collections.sort(listRecapProduction);
		result.setNombreResultaRechercher(Long.valueOf(listRecapProduction.size()));
		result.setList(new TreeSet<>(listRecapProduction));
		
		return result;
	}

}
