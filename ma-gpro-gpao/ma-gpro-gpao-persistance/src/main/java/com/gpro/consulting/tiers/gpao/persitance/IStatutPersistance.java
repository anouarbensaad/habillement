package com.gpro.consulting.tiers.gpao.persitance;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.value.StatutOfValue;

/**
 * @author $Ameni
 *
 */
public interface IStatutPersistance {

	/**
	 * Methode de creation d'un StatutOF dans la base de données
	 * 
	 * @param pStatutValue
	 * @return 
	 */
	public String creerStatut(StatutOfValue pStatutValue);

	/**
	 * Methode de suppression d'un StatutOF de la base de données
	 * 
	 * @param pId
	 */
	public void supprimerStatut(Long pId);

	/**
	 * Methode de modification d'un StatutOF 
	 * 
	 * @param pStatutValue
	 * @return
	 */
	public String modifierStatut(StatutOfValue pStatutValue);

	/**
	 * Methode de recherche d'un StatutOF par Id dans la base de données
	 * 
	 * @param pStatutValue
	 * @return
	 */
	public StatutOfValue rechercheStatutParId(Long pId);

	/**
	 * Methode de recherche de tous les StatutOFs
	 * 
	 * @return List<StatutValue>
	 */
	public List<StatutOfValue> listeStatut();

}
