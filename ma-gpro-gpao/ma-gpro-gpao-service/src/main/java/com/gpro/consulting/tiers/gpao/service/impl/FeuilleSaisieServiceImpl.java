package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.domaine.IFeuilleSaisieDomaine;
import com.gpro.consulting.tiers.gpao.service.IFeuilleSaisieService;

/**
 * implementation of {@link IFeuilleSaisieService}
 * 
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */

@Service
@Transactional
public class FeuilleSaisieServiceImpl implements IFeuilleSaisieService{

	private static final Logger logger = LoggerFactory.getLogger(FeuilleSaisieServiceImpl.class);

	@Autowired
	private IFeuilleSaisieDomaine feuilleSaisieDomaine;
	
	@Override
	public String create(FeuilleSaisieValue feuilleSaisie) {
		
		//LOGGER.info("createFeuilleSaisie: Delegating request to Domaine layer.");
		//LOGGER.info("createFeuilleSaisie:Domaine"+feuilleSaisie);

		return feuilleSaisieDomaine.create(feuilleSaisie);
	}

	@Override
	public FeuilleSaisieValue getById(Long id) {
		
		//LOGGER.info("getFeuilleSaisieById: Delegating request {} to Domaine layer."+id);
		
		return feuilleSaisieDomaine.getById(id);
	}

	@Override
	public String update(FeuilleSaisieValue feuilleSaisie) {
		
		//LOGGER.info("updateFeuilleSaisie: Delegating request to Domaine layer.");
		
		return feuilleSaisieDomaine.update(feuilleSaisie);
	}

	@Override
	public void delete(Long id) {
		
		//LOGGER.info("deleteFeuilleSaisie: Delegating request {} to Domaine layer."+id);
		
		feuilleSaisieDomaine.delete(id);
	}
	
	@Override
	public ResultatRechecheFeuilleSaisieValue rechercherMultiCritere(
			RechercheMulticritereFeuilleSaisieValue request) {
		
		//LOGGER.info("updateFeuilleSaisie: Delegating request to Domaine layer.");
		
		return feuilleSaisieDomaine.rechercherMultiCritere(request);
	}

	@Override
	public FeuilleSaisieValue validate(FeuilleSaisieValue feuilleSaisie) {
		
		//LOGGER.info("validate: Delegating request to Domaine layer.");
		
		return feuilleSaisieDomaine.validate(feuilleSaisie);
	}

	@Override
	public List<PersonnelValue> listPersonnel() {
		
		//LOGGER.info("listPersonnel: Delegating request to Domaine layer.");
		
		return feuilleSaisieDomaine.listPersonnel();
	}

	@Override
	public boolean updatePscProdForAllFeuilleSaisie() {
		
		//LOGGER.info("Update PscProd For All FeuilleSaisie: Delegating request to Domaine layer.");
		
		return feuilleSaisieDomaine.updatePscProdForAllFeuilleSaisie();
	}

	@Override
	public void addOrUpdateFeuilleSaisieTR(FeuilleSaisieTRValue feuilleSaisieTR) {
		this.feuilleSaisieDomaine.addOrUpdateFeuilleSaisieTR(feuilleSaisieTR);
	}

	@Override
	public List<Entry<String, Double>> rendementChaineParMatricule(Calendar dateDebut, Calendar dateFin, Long chaineId) {
		return this.feuilleSaisieDomaine.rendementChaineParMatricule(dateDebut, dateFin, chaineId);
	}

	@Override
	public Map<Date, Double> rendementMatriculeParJour(Calendar dateDebut, Calendar dateFin, String matricule) {
		// TODO Auto-generated method stub
		return this.feuilleSaisieDomaine.rendementMatriculeParJour(dateDebut, dateFin, matricule);
	}
	
	@Override
	public Map<Date, Double> rendementChaineParJour(Calendar dateDebut, Calendar dateFin, Long chaineId) {
		return this.feuilleSaisieDomaine.rendementChaineParJour(dateDebut, dateFin, chaineId);
	}

	@Override
	public List<Entry<Long, Double>> recapRendementChaine(Calendar dateDebut, Calendar dateFin) {
		// TODO Auto-generated method stub
		return this.feuilleSaisieDomaine.recapRendementChaine(dateDebut, dateFin);
	}

}
