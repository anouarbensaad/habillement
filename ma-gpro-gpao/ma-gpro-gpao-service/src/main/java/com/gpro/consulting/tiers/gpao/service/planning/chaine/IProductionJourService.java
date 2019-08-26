package com.gpro.consulting.tiers.gpao.service.planning.chaine;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.RechercheMulticritereProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ResultatRechecheProductionJourValue;


/**
 * @author Hamdi Etteieb
 *
 */
public interface IProductionJourService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(ProductionJourElementValue  pProductionJour);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ProductionJourElementValue  getById(Long id);
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(ProductionJourElementValue  pProductionJour);
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheProductionJourValue rechercherMultiCritere(
			RechercheMulticritereProductionJourValue request);
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProductionJourElementValue > listProductionJour();
}
