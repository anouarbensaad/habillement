package com.gpro.consulting.tiers.gpao.service.stockfini;



import org.springframework.transaction.annotation.Transactional;


import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.RechercheMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.ResultatMulticritereMouvementFiniValue;


/**
 * @author $Samer Hassen
 *
 */
public interface IMouvementFiniService {


	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String createMouvementFini(
			MouvementFiniValue pMouvementFiniValue);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void deleteMouvementFini(
			Long id);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public MouvementFiniValue findMouvementFiniParId(
			Long id);
	

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatMulticritereMouvementFiniValue rechercherMouvementFiniMultiCritere(
			RechercheMulticritereMouvementFiniValue pRechercheMouvementFiniMulitCritere);

	
}
