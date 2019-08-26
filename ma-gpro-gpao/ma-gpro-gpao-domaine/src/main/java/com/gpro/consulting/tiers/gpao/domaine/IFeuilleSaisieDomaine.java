package com.gpro.consulting.tiers.gpao.domaine;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;

/**
 * Feuille Saisie Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */
public interface IFeuilleSaisieDomaine {

	public String create(FeuilleSaisieValue feuilleSaisie);

	public FeuilleSaisieValue getById(Long id);

	public String update(FeuilleSaisieValue feuilleSaisie);

	public void delete(Long id);

	public ResultatRechecheFeuilleSaisieValue rechercherMultiCritere(
			RechercheMulticritereFeuilleSaisieValue request);

	public FeuilleSaisieValue validate(FeuilleSaisieValue feuilleSaisie);

	public List<PersonnelValue> listPersonnel();
	
	public boolean updatePscProdForAllFeuilleSaisie();
	
	public void addOrUpdateFeuilleSaisieTR(FeuilleSaisieTRValue feuilleSaisieTR);
	
	public List<Entry<String, Double>> rendementChaineParMatricule(Calendar dateDebut, Calendar dateFin, Long chaineId);

	public Map<Date, Double> rendementMatriculeParJour(Calendar dateDebut, Calendar dateFin, String matricule);
	
	public Map<Date, Double> rendementChaineParJour(Calendar dateDebut, Calendar dateFin, Long chaineId) ;
	
	public List<Entry<Long, Double>> recapRendementChaine(Calendar dateDebut, Calendar dateFin);

}
