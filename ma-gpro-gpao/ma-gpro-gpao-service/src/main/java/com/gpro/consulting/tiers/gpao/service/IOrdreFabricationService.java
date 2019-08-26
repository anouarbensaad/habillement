package com.gpro.consulting.tiers.gpao.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;

/**
 * @author $Ameni
 *
 */
public interface IOrdreFabricationService {
	/**
	 * Methode de Creation d'un Ordre de fabrication dans la BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return numero
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String creerOrdreFabrication(
			OrdreFabricationValue pOrdreFabricationValue);

	/**
	 * Methode de Suppression d'un ordre de fabrication de la BD
	 * 
	 * @param pId
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void supprimerOrdreFabrication(Long pId);

	/**
	 * Methode de Modification d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return Id
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String modifierOrdreFabrication(
			OrdreFabricationValue pOrdreFabricationValue);

	/**
	 * Methode de recherche par ID d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrdreFabricationValue rechercheOrdreFabricationParId(
			Long pOrdreFabricationId);
	
	
	/**
	 * Methode de recherche par ID d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<QuantiteVue> rechercheQuantiteParIdProduit(Long pIdProduit);

	/**
	 * Methode de recherche par ID des Phases Produit dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<PhaseProduitVue> recherchePhaseParIdProduit(
			Long pIdProduit);
	
	/**
	 * Méthode de recherche des ordres par critères
	 * 
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritere(
			RechercheMulticritereOrdreFabricationValue pRechercheOrdreFaricationMulitCritere);

	/**
	 * Methode d'Affichage de tous les ordres de fabrication
	 * 
	 * @return
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List<OrdreFabricationValue> listeOrdreFabrication();
	
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List <CouleurValue> listeCouleurOf(Long ordreFabricationId);
	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public List <TailleValue> listeTailleOf(Long ordreFabricationId);

	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrdreFabricationValue getNumeroOF(Long id);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void changerEtatOF();
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public String exporterOFToBC();

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrdreFabricationValue getByNumero(String numero);

	
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrdreFabricationValue getByNumeroAvailableForGamme(String numero);

	@Transactional(readOnly = false, rollbackFor = Exception.class)
     public void updateSuivi( RechercheMulticritereDetailSaisieValue request );

	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public OrdreFabricationValue getByNumeroAvailableForEclatement(String numero);
	
}
