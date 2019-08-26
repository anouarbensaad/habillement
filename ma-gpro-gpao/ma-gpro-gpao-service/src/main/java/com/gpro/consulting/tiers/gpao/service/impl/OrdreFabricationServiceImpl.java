package com.gpro.consulting.tiers.gpao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gpro.consulting.tiers.commun.coordination.value.CouleurValue;
import com.gpro.consulting.tiers.commun.coordination.value.TailleValue;
import com.gpro.consulting.tiers.gpao.coordination.detailSaisie.value.RechercheMulticritereDetailSaisieValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.vue.PhaseProduitVue;
import com.gpro.consulting.tiers.gpao.coordination.vue.QuantiteVue;
import com.gpro.consulting.tiers.gpao.domaine.IOrdreFabricationDomaine;
import com.gpro.consulting.tiers.gpao.service.IOrdreFabricationService;

/**
 * @author $Ameni
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class OrdreFabricationServiceImpl implements IOrdreFabricationService{

	@Autowired
	IOrdreFabricationDomaine ordreFabricationDomaine;
	
	@Override
	public String creerOrdreFabrication(
			OrdreFabricationValue pOrdreFabricationValue) {
		return ordreFabricationDomaine.creerOrdreFabrication(pOrdreFabricationValue);
	}

	@Override
	public void supprimerOrdreFabrication(Long pId) {
		ordreFabricationDomaine.supprimerOrdreFabrication(pId);
	}

	@Override
	public String modifierOrdreFabrication(
			OrdreFabricationValue pOrdreFabricationValue) {
		return ordreFabricationDomaine.modifierOrdreFabrication(pOrdreFabricationValue);
	}

	@Override
	public OrdreFabricationValue rechercheOrdreFabricationParId(
			Long pOrdreFabricationId) {
		return ordreFabricationDomaine.rechercheOrdreFabricationParId(pOrdreFabricationId);
	}
	
	@Override
	public List<QuantiteVue> rechercheQuantiteParIdProduit(Long pIdProduit) {
		return ordreFabricationDomaine.rechercheQuantiteParIdProduit(pIdProduit);
	}
	
	@Override
	public List<PhaseProduitVue> recherchePhaseParIdProduit(Long pIdProduit) {
		return ordreFabricationDomaine.recherchePhaseParIdProduit(pIdProduit);
	}

	@Override
	public ResultatMulticritereOrdreFabricationValue rechercherOrdreFabricationMultiCritere(
			RechercheMulticritereOrdreFabricationValue pRechercheOrdreFaricationMulitCritere) {
		return ordreFabricationDomaine.rechercherOrdreFabricationMultiCritere(pRechercheOrdreFaricationMulitCritere);
	}
	
	@Override
	public List<OrdreFabricationValue> listeOrdreFabrication() {
		return ordreFabricationDomaine.listeOrdreFabrication();
	}

	@Override
	public List<CouleurValue> listeCouleurOf(Long ordreFabricationId) {
		
		return ordreFabricationDomaine.listeCouleurOf(ordreFabricationId);
	}

	@Override
	public List<TailleValue> listeTailleOf(Long ordreFabricationId) {
		
		return ordreFabricationDomaine.listeTailleOf(ordreFabricationId);
	}

	@Override
	public OrdreFabricationValue getNumeroOF(Long id) {
		return ordreFabricationDomaine.getNumeroOF(id);
	}

	@Override
	public void changerEtatOF() {
         ordreFabricationDomaine.changerEtatOF();		
	}
	@Override
	public String exporterOFToBC(){
		return ordreFabricationDomaine.exporterOFToBC();
	}
	
	@Override
	public OrdreFabricationValue getByNumero(String numero) {
		
		return ordreFabricationDomaine.getByNumero(numero);
		
		
		
	}

	@Override
	public OrdreFabricationValue getByNumeroAvailableForGamme(String numero) {
		
		return ordreFabricationDomaine.getByNumeroAvailableForGamme(numero);
		
		
		
	}

	@Override
	public void updateSuivi( RechercheMulticritereDetailSaisieValue request ) {
         ordreFabricationDomaine.updateSuivi(request);	
	}

	@Override
	public OrdreFabricationValue getByNumeroAvailableForEclatement(String numero) {
		// TODO Auto-generated method stub
		return ordreFabricationDomaine.getByNumeroAvailableForEclatement(numero);
	}


}
