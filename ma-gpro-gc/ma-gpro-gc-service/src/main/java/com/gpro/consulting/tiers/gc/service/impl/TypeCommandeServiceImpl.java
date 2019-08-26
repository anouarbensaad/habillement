package com.gpro.consulting.tiers.gc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.value.TypeCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.ITypeCommandeDomaine;
import com.gpro.consulting.tiers.gc.service.ITypeCommandeService;

/**
 * @author $Ameni
 *
 */

@Service
@Transactional
public class TypeCommandeServiceImpl implements ITypeCommandeService {

	@Autowired
	ITypeCommandeDomaine typeCommandeDomaine;

	/**
	 * Methode de Creation d'un Bon de Commande de Vente de la base de données
	 * 
	 * @param pTypeCommandeValue
	 * @return reference
	 */
	@Override
	public String creerTypeCommande(TypeCommandeValue pTypeCommandeValue) {
		return typeCommandeDomaine.creerTypeCommande(pTypeCommandeValue);
	}

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pId
	 */
	@Override
	public void supprimerTypeCommande(Long pId) {
		typeCommandeDomaine.supprimerTypeCommande(pId);
	}

	/**
	 * Methode de suppression d'un bon de commande de la base de données
	 * 
	 * @param pTypeCommandeValue
	 * @return reference
	 */
	@Override
	public String modifierTypeCommande(TypeCommandeValue pTypeCommandeValue) {
		return typeCommandeDomaine.modifierTypeCommande(pTypeCommandeValue);
	}

	/**
	 * Methode de recherche d'un Bon de Commande par Id retournant un Objet
	 * Valeur
	 * 
	 * @param pTypeCommandeValue
	 * @return TypeCommandeValue
	 */
	@Override
	public TypeCommandeValue rechercheTypeCommandeParId(Long pId) {
		return typeCommandeDomaine.rechercheTypeCommandeParId(pId);
	}

	/**
	 * Methode de recherche de tous les bons de Commandes
	 * 
	 * @return List < TypeCommandeValue >
	 */
	@Override
	public List<TypeCommandeValue> listeTypeCommande() {
		return typeCommandeDomaine.listeTypeCommande();
	}
	/***************************** Getter & Setter ********************************/
	/**
	 * @return the typeCommandeDomaine
	 */
	public ITypeCommandeDomaine getTypeCommandeDomaine() {
		return typeCommandeDomaine;
	}

	/**
	 * @param typeCommandeDomaine the typeCommandeDomaine to set
	 */
	public void setTypeCommandeDomaine(ITypeCommandeDomaine typeCommandeDomaine) {
		this.typeCommandeDomaine = typeCommandeDomaine;
	}
	
}
