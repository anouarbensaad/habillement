package com.gpro.consulting.tiers.gc.service.factureVente;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.FactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.RechercheMulticritereFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.factureVente.Value.ResultatRechecheFactureVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Vue.LivraisonVenteVue;

/**
 * @author Ameni Berrich
 *
 */
public interface IFactureVenteService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerFactureVente(FactureVenteValue pFactureVenteValue);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerFactureVenteValue(Long pId);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierFactureVenteValue(FactureVenteValue pFactureVenteValue);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public FactureVenteValue rechercheFactureVenteValueParId(Long pId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheFactureVenteValue rechercherFactureVenteMultiCritere(
			RechercheMulticritereFactureVenteValue pRechercheFactureVenteValueMulitCritere);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public LivraisonVenteVue getProduitFactureByReferenceBLList(List<String> refBLList);
}
