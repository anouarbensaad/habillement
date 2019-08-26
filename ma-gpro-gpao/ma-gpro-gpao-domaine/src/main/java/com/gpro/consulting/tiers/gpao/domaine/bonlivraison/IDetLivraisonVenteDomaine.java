package com.gpro.consulting.tiers.gpao.domaine.bonlivraison;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;

/**
 * BonLivraison Domaine interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetLivraisonVenteDomaine {

	public String create(DetLivraisonVenteValue detLivraisonVenteValue);

	public DetLivraisonVenteValue getById(Long id);

	public String update(DetLivraisonVenteValue detLivraisonVenteValue);

	public void delete(Long id);
	
}
