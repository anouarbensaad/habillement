package com.gpro.consulting.tiers.gpao.domaine;

import java.util.List;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;

/**
 * @author toshiba
 *
 */
public interface IOrdreFabricationDomaine {
	/**
	 * Methode de Creation d'un Ordre de fabrication dans la BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return numero
	 */
	public String creerOrdreFabrication(OrdreFabricationValue pOrdreFabricationValue);

	/** Methode de Suppression d'un ordre de fabrication de la BD
	 * @param pId
	 */
	public void supprimerOrdreFabrication(Long pId);

	/**Methode de Modification d'un ordre de fabrication dans a BD
	 * @param pOrdreFabricationValue
	 * @return Id
	 */
	public String modifierOrdreFabrication(OrdreFabricationValue pOrdreFabricationValue);

	/**Methode de recherche par ID  d'un ordre de fabrication dans a BD
	 * @param pOrdreFabricationValue
	 * @return
	 */
	public OrdreFabricationValue rechercheOrdreFabricationParId(Long pId);

	/**
	 * Methode de recherche par ID d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	public List<QuantiteVue> rechercheQuantiteParIdProduit(Long pIdProduit);
	
	/**
	 * Methode de recherche par ID des Phases Produit dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	public List<PhaseProduitVue> recherchePhaseParIdProduit(
			Long pIdProduit);
	
	/**
	 * Méthode de recherche des ordres par critères
	 * 
	 * @return
	 */
	public ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritere(
			RechercheMulticritereOrdreFabricationValue pRechercheOrdreFaricationMulitCritere);

	/**Methode d'Affichage de tous les ordres de fabrication 
	 * @return
	 */
	public List<OrdreFabricationValue> listeOrdreFabrication();

	public List<CouleurValue> listeCouleurOf(Long ordreFabricationId);

	public List<TailleValue> listeTailleOf(Long ordreFabricationId);

	public OrdreFabricationValue getNumeroOF(Long id);

	public void changerEtatOF();

	public String exporterOFToBC();
	
	
	public OrdreFabricationValue getByNumero(String numero);

	public OrdreFabricationValue getByNumeroAvailableForGamme(String numero);

	public void updateSuivi(RechercheMulticritereDetailSaisieValue request);

	public OrdreFabricationValue getByNumeroAvailableForEclatement(String numero);

	
	
	
}
