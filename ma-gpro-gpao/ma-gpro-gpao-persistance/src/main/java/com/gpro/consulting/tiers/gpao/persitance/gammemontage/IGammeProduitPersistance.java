package com.gpro.consulting.tiers.gpao.persitance.gammemontage;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;

/**
 * GammeProduit Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 08/04/2016
 *
 */
public interface IGammeProduitPersistance {

	public String create(GammeProduitValue gammeProduitValue);

	public GammeProduitValue getById(Long id);

	public String update(GammeProduitValue gammeProduitValue);

	public void delete(Long id);

	public List<GammeProduitValue> getAll();

	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request);
	
	public GammeProduitValue getByProduitId(Long produitId);
	
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request, boolean allegee) ;
}
