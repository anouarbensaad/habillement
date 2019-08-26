package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;

/**
 * GammeProduit Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public interface IGammeProduitDomaine {
	
	public String create(GammeProduitValue gammeProduitValue);

	public GammeProduitValue getById(Long id);

	public String update(GammeProduitValue gammeProduitValue);

	public void delete(Long id);

	public List<GammeProduitValue> getAll();

	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request);

	public List<ProduitValue> getProduitListAvailable();

	public List<ProduitValue> getProduitListUsed();

	public GammeProduitValue getByProduitId(Long produitId);
	
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request, boolean allegee) ;

}
