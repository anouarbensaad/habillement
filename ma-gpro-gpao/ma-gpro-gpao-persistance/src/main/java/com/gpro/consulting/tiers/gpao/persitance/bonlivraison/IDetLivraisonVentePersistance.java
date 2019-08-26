package com.gpro.consulting.tiers.gpao.persitance.bonlivraison;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheDetailBonLivraisonValue;

/**
 * DetLivraisonVente Persistance interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetLivraisonVentePersistance {
	
	public String create(DetLivraisonVenteValue detLivraisonVenteValue);

	public DetLivraisonVenteValue getById(Long id);

	public String update(DetLivraisonVenteValue detLivraisonVenteValue);

	public void delete(Long id);

	public DetLivraisonVenteValue getBylivraisonVenteAndProduit(
			Long livraisonVenteId, Long produitId, String choix);
	

	
	public void setTraitementPU(Long id , Double nouveauPU, Long idFiche);
	
	
}
