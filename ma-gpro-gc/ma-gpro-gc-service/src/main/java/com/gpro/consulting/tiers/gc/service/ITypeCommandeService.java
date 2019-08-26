package com.gpro.consulting.tiers.gc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;

/**
 * @author toshiba
 *
 */
public interface ITypeCommandeService {
	/**
	 * Methode de creation d'un nouveau type de commande dans la base de données
	 * 
	 * @param pTypeCommandeValue
	 * @return designation
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerTypeCommande(TypeCommandeValue pTypeCommandeValue);

	/**
	 * Methode de suppression d'un nouveau type commande de la base de données
	 * 
	 * @param pId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerTypeCommande(Long pId);

	/**
	 * Methode de modification d'un nouveau type de commande
	 * 
	 * @param pTypeCommandeValue
	 * @return designation
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierTypeCommande(TypeCommandeValue pTypeCommandeValue);

	/**
	 * Methode de recherche d'un etat de commande par Id dans la base de données
	 * 
	 * @param pTypeCommandeValue
	 * @return TypeCommandeValue
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public TypeCommandeValue rechercheTypeCommandeParId(Long pId);

	/**
	 * Methode de recherche de tous les etats de commandes
	 * 
	 * @return List<TypeCommandeValue>
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<TypeCommandeValue> listeTypeCommande();
}
