package com.gpro.consulting.tiers.gc.service.livraisonVente;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ProduitLivraisonValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.RechercheMulticritereLivraisonVenteValue;
import com.gpro.consulting.tiers.gc.coordination.livraisonVente.Value.ResultatRechecheLivraisonVenteValue;

/**
 * @author Ameni Berrich
 *
 */
public interface ILivraisonVenteService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerLivraisonVente(LivraisonVenteValue pLivraisonVenteValue);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerLivraisonVenteValue(Long pId);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierLivraisonVenteValue(LivraisonVenteValue pLivraisonVenteValue);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public LivraisonVenteValue rechercheLivraisonVenteValueParId(Long pId);
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultatRechecheLivraisonVenteValue rechercherLivraisonVenteMultiCritere(
			RechercheMulticritereLivraisonVenteValue pRechercheLivraisonVenteValueMulitCritere);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public List<ProduitLivraisonValue> getProduitLivraisonListByRefBonCmdList(List<String> refBonCmdList);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<String> getReferences();
}

