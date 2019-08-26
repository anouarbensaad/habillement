package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.value.ChaineValue;

/**
 * @author $Ameni
 *
 */
public interface IChaineDomaine {
	/**Methode de creation d'une chaine dans la base de données
	 * @param pChaineValue
	 * @return 
	 */
	public  String creerChaine(ChaineValue pChaineValue);
	
	/**Methode de suppression d'une chaine de la base de données
	 * @param pId
	 */
	public  void supprimerChaine(Long pId);
	
	/**Methode de modification d'un etat de commande 
	 * @param pChaineValue
	 * @return
	 */
	public String modifierChaine(ChaineValue pChaineValue);
	
	/** Methode de recherche d'une chaine par Id dans la base de données
	 * @param pChaineValue
	 * @return
	 */
	public ChaineValue rechercheChaineParId(Long pId);
	
	/** Methode de recherche de tous les d'une chaines
	 * @return List<ChaineValue>
	 */
	public List<ChaineValue> listeChaine();
}
