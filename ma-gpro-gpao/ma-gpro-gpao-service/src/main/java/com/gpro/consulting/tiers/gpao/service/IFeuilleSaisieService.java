package com.gpro.consulting.tiers.gpao.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;

/**
 * @author Wahid Gazzah
 * @since 25/05/2016
 */
public interface IFeuilleSaisieService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(FeuilleSaisieValue feuilleSaisie);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FeuilleSaisieValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(FeuilleSaisieValue feuilleSaisie);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFeuilleSaisieValue rechercherMultiCritere(
			RechercheMulticritereFeuilleSaisieValue request);

	public FeuilleSaisieValue validate(FeuilleSaisieValue feuilleSaisie);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<PersonnelValue> listPersonnel();
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public boolean updatePscProdForAllFeuilleSaisie();
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void addOrUpdateFeuilleSaisieTR(FeuilleSaisieTRValue feuilleSaisieTR);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<Entry<String, Double>> rendementChaineParMatricule(Calendar dateDebut, Calendar dateFin, Long chaineId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Map<Date, Double> rendementMatriculeParJour(Calendar dateDebut, Calendar dateFin, String matricule);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public Map<Date, Double> rendementChaineParJour(Calendar dateDebut, Calendar dateFin, Long chaineId) ;

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<Entry<Long, Double>> recapRendementChaine(Calendar dateDebut, Calendar dateFin);

}
