package com.gpro.consulting.tiers.gpao.service.bonlivraison;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;

/**
 * BonLivraison Service Interface
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */
public interface IDetLivraisonVenteService {

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String create(DetLivraisonVenteValue detLivraisonVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public DetLivraisonVenteValue getById(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String update(DetLivraisonVenteValue detLivraisonVenteValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void delete(Long id);

}
