package com.gpro.consulting.tiers.gc.service.livraisonVente.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.domaine.livraisonVente.ILivraisonVenteDomaine;
import com.gpro.consulting.tiers.gc.domaine.livraisonVente.impl.LivraisonVenteDomaineImp;
import com.gpro.consulting.tiers.gc.service.livraisonVente.ILivraisonVenteService;

/**
 * @author Ameni Berrich
 *
 */

@Service
@Transactional
public class LivraisonVenteServiceImpl implements ILivraisonVenteService{

	private static final Logger logger = LoggerFactory.getLogger(LivraisonVenteDomaineImp.class);

	@Autowired
	private ILivraisonVenteDomaine livraisonVenteDomaine;

	@Override
	public String creerLivraisonVente(LivraisonVenteValue pLivraisonVenteValue) {
		return livraisonVenteDomaine.creerLivraisonVente(pLivraisonVenteValue);
	}

	@Override
	public void supprimerLivraisonVenteValue(Long pId) {
		livraisonVenteDomaine.supprimerLivraisonVenteValue(pId);
		
	}

	@Override
	public String modifierLivraisonVenteValue(
			LivraisonVenteValue pLivraisonVenteValue) {
		return livraisonVenteDomaine.modifierLivraisonVenteValue(pLivraisonVenteValue);
	}

	@Override
	public LivraisonVenteValue rechercheLivraisonVenteValueParId(Long pId) {
		return livraisonVenteDomaine.rechercheLivraisonVenteValueParId(pId);
	}

	@Override
	public ResultatRechecheLivraisonVenteValue rechercherLivraisonVenteMultiCritere(
			RechercheMulticritereLivraisonVenteValue pRechercheLivraisonVenteValueMulitCritere) {
		return livraisonVenteDomaine.rechercherLivraisonVenteMultiCritere(pRechercheLivraisonVenteValueMulitCritere);
	}

	@Override
	public List<ProduitLivraisonValue> getProduitLivraisonListByRefBonCmdList(List<String> refBonCmdList) {
		
		return livraisonVenteDomaine.getProduitLivraisonListByRefBonCmdList(refBonCmdList);
	}

	@Override
	public List<String> getReferences() {
		
		return livraisonVenteDomaine.getReferences();
	}

}
