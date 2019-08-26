package com.gpro.consulting.tiers.gpao.persitance.feuillesaisie;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieTRValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.FeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.PersonnelValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.RechercheMulticritereFeuilleSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.feuillesaisie.value.ResultatRechecheFeuilleSaisieValue;

/**
 * Feuille Saisie Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 25/05/2016
 *
 */
public interface IFeuilleSaisiePersistance {

	public String create(FeuilleSaisieValue feuilleSaisie);

	public FeuilleSaisieValue getById(Long id);

	public String update(FeuilleSaisieValue feuilleSaisie);

	public void delete(Long id);

	public ResultatRechecheFeuilleSaisieValue rechercherMultiCritere(
			RechercheMulticritereFeuilleSaisieValue request);

	public List<PersonnelValue> listPersonnel();
	
	public FeuilleSaisieValue checkExistence(FeuilleSaisieValue feuilleSaisieTR);
	
	public boolean personnelDateChaineExistence(Long personnelId, Calendar date, Long chaineId);
	
	/*
	 * @author Zeineb Medimagh
	 * @since 02/08/2017
	 */
	
	public Calendar getDateSaisie(Long feuilleId);
	
	public Long getChaineId(Long feuilleId);

}
