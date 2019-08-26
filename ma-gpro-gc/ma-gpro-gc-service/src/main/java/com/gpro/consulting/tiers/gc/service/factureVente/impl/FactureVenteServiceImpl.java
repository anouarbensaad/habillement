package com.gpro.consulting.tiers.gc.service.factureVente.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.RechercheMulticritereFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ResultatRechecheFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gc.domaine.factureVente.IFactureVenteDomaine;
import com.gpro.consulting.tiers.gc.service.factureVente.IFactureVenteService;

/**
 * @author Ameni Berrich
 *
 */

@Service
@Transactional
public class FactureVenteServiceImpl implements IFactureVenteService{

	@Autowired
	private IFactureVenteDomaine factureVenteDomaine;

	@Override
	public String creerFactureVente(FactureVenteValue pFactureVenteValue) {
		return factureVenteDomaine.creerFactureVente(pFactureVenteValue);
	}

	@Override
	public void supprimerFactureVenteValue(Long pId) {
		factureVenteDomaine.supprimerFactureVenteValue(pId);
	}

	@Override
	public String modifierFactureVenteValue(FactureVenteValue pFactureVenteValue) {
		return factureVenteDomaine.modifierFactureVenteValue(pFactureVenteValue);
	}

	@Override
	public FactureVenteValue rechercheFactureVenteValueParId(Long pId) {
		return factureVenteDomaine.rechercheFactureVenteValueParId(pId);
	}

	@Override
	public ResultatRechecheFactureVenteValue rechercherFactureVenteMultiCritere(
			RechercheMulticritereFactureVenteValue pRechercheFactureVenteValueMulitCritere) {
		return factureVenteDomaine.rechercherFactureVenteMultiCritere(pRechercheFactureVenteValueMulitCritere);
	}

	@Override
	public LivraisonVenteVue getProduitFactureByReferenceBLList(
			List<String> refBLList) {
		return factureVenteDomaine.getProduitFactureByReferenceBLList(refBLList);
	}


	
}
