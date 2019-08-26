package com.gpro.consulting.tiers.gpao.domaine.planning.chaine;

import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.RechercheMulticritereProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ResultatRechecheProductionJourValue;


/**
 * @author Hamdi Etteieb
 *
 */
public interface IProductionJourDomaine {
	
	public String create(ProductionJourElementValue pProduction);

	public ProductionJourElementValue getById(Long id);

	public String update(ProductionJourElementValue pProduction);

	public void delete(Long id);

	public ResultatRechecheProductionJourValue rechercherMultiCritere(
			RechercheMulticritereProductionJourValue request);

	public List<ProductionJourElementValue> listProductionJour();
}