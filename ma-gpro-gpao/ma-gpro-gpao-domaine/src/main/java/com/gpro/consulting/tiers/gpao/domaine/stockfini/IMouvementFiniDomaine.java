package com.gpro.consulting.tiers.gpao.domaine.stockfini;




import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.MouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.RechercheMulticritereMouvementFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.stockfini.value.ResultatMulticritereMouvementFiniValue;



/**
 * @author $Samer Hassen
 *
 */
public interface IMouvementFiniDomaine {
	
	
	public String createMouvementFini(
			MouvementFiniValue pMouvementFiniValue);


	public void deleteMouvementFini(
			Long id);

	/**
	 * Methode de recherche par ID d'un ordre de fabrication dans a BD
	 * 
	 * @param pMouvementFiniValue
	 * @return
	 */
	public MouvementFiniValue findMouvementFiniParId(
			Long pMouvementFiniId);

	/**


	 */
	public ResultatMulticritereMouvementFiniValue rechercherMouvementFiniMultiCritere(
			RechercheMulticritereMouvementFiniValue pRechercheOrdreFaricationMulitCritere);


	

}
