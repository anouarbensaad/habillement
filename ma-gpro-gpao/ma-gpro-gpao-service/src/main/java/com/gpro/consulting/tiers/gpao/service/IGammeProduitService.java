package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.GammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.RechercheMulticritereGammeProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.gammemontage.value.ResultatRechecheGammeProduitValue;

/**
 * @author Wahid Gazzah
 * @since 08/04/2016
 */
public interface IGammeProduitService {
	
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(GammeProduitValue gammeProduitValue);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeProduitValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(GammeProduitValue gammeProduitValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<GammeProduitValue> getAll();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProduitValue> getProduitListAvailable();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<ProduitValue> getProduitListUsed();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public GammeProduitValue getByProduitId(Long produitId);

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatRechecheGammeProduitValue rechercherMultiCritere(
			RechercheMulticritereGammeProduitValue request, boolean allegee) ;
}
