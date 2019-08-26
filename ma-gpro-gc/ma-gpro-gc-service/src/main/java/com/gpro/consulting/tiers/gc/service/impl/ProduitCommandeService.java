package com.gpro.consulting.tiers.gc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.IProduitCommandeDomaine;
import com.gpro.consulting.tiers.gc.service.IProduitCommandeService;

/**
 * implementation of {@link IProduitCommandeService}
 * 
 * @author Wahid Gazzah
 * @since 15/03/2016
 *
 */

@Service
@Transactional
public class ProduitCommandeService implements IProduitCommandeService {
	
	@Autowired
	IProduitCommandeDomaine produitCommandeDomaine;

	@Override
	public List<ProduitCommandeValue> getAll() {
		
		return produitCommandeDomaine.getAll();
	}

	@Override
	public ResultatRechecheProduitCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitCommandeValue request) {
		
		return produitCommandeDomaine.rechercherMultiCritere(request);
	}

	// Added on 22/03/2016, by Ameni
	@Override
	public void supprimerProduitCommande(Long pId) {
		
		produitCommandeDomaine.supprimerProduitCommande(pId);
	}

}
