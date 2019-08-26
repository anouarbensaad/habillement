package com.gpro.consulting.tiers.commun.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.RechercheMulticritereProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.ResultatRechecheProduitValue;
import com.gpro.consulting.tiers.commun.domaine.IProduitDomaine;
import com.gpro.consulting.tiers.commun.service.IProduitService;
// TODO: Auto-generated Javadoc

/**
 * The Class PrduitServiceImpl.
 * @author med
 */
@Service
@Transactional
public class ProduitServiceImpl implements IProduitService{
	/** The produit domaine. */
	@Autowired
	IProduitDomaine produitDomaine;

	//creer produit
	@Override
	public String creerProduit(ProduitValue pProduitValue) {
		return produitDomaine.creerProduit(pProduitValue);
	}
    
	//supprimer
	@Override
	public void supprimerProduit(Long pProduitId) {
       produitDomaine.supprimerProduit(pProduitId);		
	}

	//modifier
	@Override
	public String modifierProduit(ProduitValue pProduitValue) {
		return produitDomaine.modifierProduit(pProduitValue);
	}

	//recherche par id
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId) {
		return produitDomaine.rechercheProduitById(pProduitId);
	}

	//liste produit
	@Override
	public List<ProduitValue> listeProduit() {
		return produitDomaine.listeProduit();
	}

	/**
	 * Gets the produit domaine.
	 *
	 * @return the produit domaine
	 */
	public IProduitDomaine getProduitDomaine() {
		return produitDomaine;
	}

	/**
	 * Sets the produit domaine.
	 *
	 * @param produitDomaine the new produit domaine
	 */
	public void setProduitDomaine(IProduitDomaine produitDomaine) {
		this.produitDomaine = produitDomaine;
	}


	@Override
	public ResultatRechecheProduitValue rechercherProduitMultiCritere(
			RechercheMulticritereProduitValue pRechercheProduitMulitCritere) {
		  return produitDomaine.rechercherProduitMultiCritere(pRechercheProduitMulitCritere);

		
	}
	
	
	@Override
	public ProduitValue rechercheProduitById(Long pProduitId, boolean allegee) {
		return produitDomaine.rechercheProduitById(pProduitId, allegee);
	}

}
