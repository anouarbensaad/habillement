/**
 * 
 */
package com.gpro.consulting.tiers.gpao.service.planning.chaine.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ProductionJourElementValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.RechercheMulticritereProductionJourValue;
import com.gpro.consulting.tiers.gpao.coordination.planning.chaine.value.ResultatRechecheProductionJourValue;
import com.gpro.consulting.tiers.gpao.domaine.planning.chaine.IProductionJourDomaine;
import com.gpro.consulting.tiers.gpao.service.planning.chaine.IProductionJourService;


/**
 * @author Hamdi Etteieb
 *
 */
@Service
@Transactional
public class ProductionJourServiceImpl implements IProductionJourService {

	@Autowired
	IProductionJourDomaine productionJourDomaine;

	@Override
	public String create(ProductionJourElementValue  pProductionValue) {
		// TODO Auto-generated method stub
		return productionJourDomaine.create(pProductionValue);
	}

	@Override
	public ProductionJourElementValue  getById(Long id) {
		
		return productionJourDomaine.getById(id);
	}

	@Override
	public String update(ProductionJourElementValue  pProductionValue) {
		
		return productionJourDomaine.update(pProductionValue);
	}

	@Override
	public void delete(Long id) {
		productionJourDomaine.delete(id);
		
	}

	@Override
	public ResultatRechecheProductionJourValue rechercherMultiCritere(
			RechercheMulticritereProductionJourValue request) {
		
		return productionJourDomaine.rechercherMultiCritere(request);
	}

	@Override
	public List<ProductionJourElementValue > listProductionJour() {
		// TODO Auto-generated method stub
		return productionJourDomaine.listProductionJour();
	}
	
	}
