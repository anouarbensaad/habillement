package com.gpro.consulting.tiers.gpao.persitance;

import java.util.Calendar;
import java.util.List;

import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;


/**
 * @author $Ameni
 *
 */
public interface IOrdreFabricationPersistance {

	/**
	 * Methode de Creation d'un Ordre de fabrication dans la BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return numero
	 */
	public String creerOrdreFabrication(
			OrdreFabricationValue pOrdreFabricationValue);

	/**
	 * Methode de Suppression d'un ordre de fabrication de la BD
	 * 
	 * @param pId
	 */
	public void supprimerOrdreFabrication(Long pId);

	/**
	 * Methode de Modification d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return Id
	 */
	public String modifierOrdreFabrication(
			OrdreFabricationValue pOrdreFabricationValue);

	/**
	 * Methode de recherche par ID d'un ordre de fabrication dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	public OrdreFabricationValue rechercheOrdreFabricationParId(
			Long pOrdreFabricationId);

	/**
	 * Methode de recherche par ID d'une Quantite dans a BD
	 * 
	 * @param pOrdreFabricationValue
	 * @return
	 */
	public List<QuantiteVue> rechercheQuantiteParIdProduit(
			Long pIdProduit);

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

	/**
	 * Methode d'Affichage de tous les ordres de fabrication
	 * 
	 * @return
	 */
	
	public List<OrdreFabricationValue> listeOrdreFabrication();

	public List<OrdreFabricationValue> getAllInList(List<Long> ids);
	
	public List<OrdreFabricationValue> getAllNotInList(List<Long> ids);
	
	public OrdreFabricationValue getByNumero(String numero);
	
	/**
	 * @author Zeineb Medimagh
	 * @since 01/12/2016
	 */
		
	public String getNumOfParId(Long pOrdreFabricationId);
	
	public OrdreFabricationValue getOFWithSomeColumns(Long pOrdreFabricationId);
	
	public Long getProduitIdByOFId(Long OFId);
	
	public OrdreFabricationValue getNumeroOF(Long id);
	
	public boolean numOFExistence(String numOF);

	Long CalculeQteProduiteCumul(Long of, Long op);

	Long calculeQteProduiteJours(Long of, Calendar dateDebut, Calendar dateFin,
			Long op);

	public ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritereGlobale(
			RechercheMulticritereOrdreFabricationValue request);

	public List<OrdreFabricationValue> listerOFTOCommercial();

	public Long getIdOfParNum(String pOrdreFabricationNum);

}
