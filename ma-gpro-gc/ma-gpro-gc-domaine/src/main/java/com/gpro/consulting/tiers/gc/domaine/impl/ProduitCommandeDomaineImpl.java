package com.gpro.consulting.tiers.gc.domaine.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.value.PartieInteresseValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IPartieInteresseePersistance;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.gc.coordination.value.ProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.RechercheMulticritereProduitCommandeValue;
import com.gpro.consulting.tiers.gc.coordination.value.ResultatRechecheProduitCommandeValue;
import com.gpro.consulting.tiers.gc.domaine.IProduitCommandeDomaine;
import com.gpro.consulting.tiers.gc.persitance.IProduitCommandePersistance;

/**
 * implementation of {@link IProduitCommandeDomaine}
 * 
 * @author Wahid Gazzah
 * @since 15/03/2016
 *
 */

@Component
public class ProduitCommandeDomaineImpl implements IProduitCommandeDomaine{
	
	@Autowired
	IProduitCommandePersistance produitCommandePersistance;
	
	@Autowired
	IProduitPersistance produitPersistance;
	
	@Autowired
	IPartieInteresseePersistance partieInteresseePersistance;

	@Override
	public List<ProduitCommandeValue> getAll() {

		return produitCommandePersistance.getAll();
	}

	@Override
	public ResultatRechecheProduitCommandeValue rechercherMultiCritere(
			RechercheMulticritereProduitCommandeValue request) {
		
		ResultatRechecheProduitCommandeValue result = produitCommandePersistance.rechercherMultiCritere(request);
		
		if(result != null){
			
			//TODO cache
			List<ProduitValue> listProduit = produitPersistance.listeProduit();
			Map<Long, ProduitValue> mapProduits = new HashMap<Long, ProduitValue>();
			for(ProduitValue produit : listProduit){
				Long key = produit.getId();
				if (mapProduits.get(key) == null) {
					mapProduits.put(key, produit);
				}
			}
			
			//TODO cache
			Map<Long, String> mapClientsAbreviations = new HashMap<Long, String>();
			for(PartieInteresseValue client : partieInteresseePersistance.listePartieInteressee()){
				Long key = client.getId();
				if (mapClientsAbreviations.get(key) == null) {
					mapClientsAbreviations.put(key, client.getAbreviation());
				}
			}
			
			for(ProduitCommandeValue produitCommande : result.getListProduitCommandeValue()){
				
				Long produitId = produitCommande.getProduitId();
	
				if(produitId != null){
					if(mapProduits.containsKey(produitId)){
						produitCommande.setProduitDesignation(mapProduits.get(produitId).getDesignation());
						produitCommande.setReferenceProduit(mapProduits.get(produitId).getReference());
					}				
				}
				
				Long clientId = produitCommande.getPartieIntersseId();
				
				if(clientId != null){
					if(mapClientsAbreviations.containsKey(clientId)){
						produitCommande.setClientAbreviation(mapClientsAbreviations.get(clientId));
					}				
				}

			}
		}
		
		return result;
	}

	// Added on 22/03/2016, by Ameni
	@Override
	public void supprimerProduitCommande(Long pId) {
		produitCommandePersistance.supprimerProduitCommande(pId);
	}
	

}
