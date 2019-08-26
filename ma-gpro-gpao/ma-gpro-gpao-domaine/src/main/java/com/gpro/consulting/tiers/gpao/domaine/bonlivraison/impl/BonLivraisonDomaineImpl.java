package com.gpro.consulting.tiers.gpao.domaine.bonlivraison.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.LivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.RechercheMulticritereBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.ResultatRechecheBonLivraisonValue;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.vue.LivraisonVenteVue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;

import com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value.BonLivraisonReportProductValue;
import com.gpro.consulting.tiers.gpao.coordination.report.bonlivraison.value.BonLivraisonReportValue;
import com.gpro.consulting.tiers.gpao.domaine.bonlivraison.IBonLivraisonDomaine;
import com.gpro.consulting.tiers.gpao.domaine.bonlivraison.utilities.BonLivraisonFactureUtilities;
import com.gpro.consulting.tiers.gpao.domaine.bonlivraison.utilities.DetLivraisonVenteComparator;
import com.gpro.consulting.tiers.gpao.domaine.report.IGestionnaireReportGpaoDomaine;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.gpao.persitance.bonsortiefini.IBonSortieFiniPersistance;


/**
 * Implementation of {@link IBonLivraisonDomaine}
 * 
 * @author Wahid Gazzah
 * @since 27/01/2016
 *
 */

@Component
public class BonLivraisonDomaineImpl implements IBonLivraisonDomaine {

	private static final Logger logger = LoggerFactory.getLogger(BonLivraisonDomaineImpl.class);

	private static final String SEPARATOR = "-";
	private static final int FIRST_INDEX = 0;
	private static final Double ZERO = 0D;
	private static final Long ZERO_LONG = 0L;

	private static final Long TAXE_ID_FODEC = 1L;
	private static final Long TAXE_ID_TVA = 2L;
	private static final Long TAXE_ID_TIMBRE = 3L;

	@Autowired
	private IGestionnaireReportGpaoDomaine gestionnaireReportGpaoDomaine;



	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;

	@Autowired
	BonLivraisonFactureUtilities bonLivraisonFactureUtilities;

	@Override
	public ResultatRechecheBonLivraisonValue rechercherMultiCritere(RechercheMulticritereBonLivraisonValue request) {

		return bonLivraisonPersistance.rechercherMultiCritere(request);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String createBonLivraison(LivraisonVenteValue bonLivraisonValue) {
		// //System.out.println("#### Debut de Creationn : "+new Date());
		Double montantTaxeFodec = ZERO;
		Double montantTaxeTVA = ZERO;
		Double montantTaxeTimbre = ZERO;

		Double assietteFodec = ZERO;
		Double assietteTVA = ZERO;

		Double montantHTaxeTotal = ZERO;
		Double montantTaxesTotal = ZERO;

		Double montantRemiseTotal = ZERO;
		Double metrageTotal = ZERO;

		Long nbreColisTtotal = ZERO_LONG;

		Long nbrePaletteTotal = ZERO_LONG;

		bonLivraisonValue.setNatureLivraison("FINI");

		// TODO Inutile A voir
		if (bonLivraisonValue.getInfoSortie() != null && (!bonLivraisonValue.getInfoSortie().equals(""))) {
			String infoSortieSplitted[] = bonLivraisonValue.getInfoSortie().split(SEPARATOR);

			String firstRefBonSortie = infoSortieSplitted[FIRST_INDEX];

			List<String> refBonSortieList = new ArrayList<String>();
			refBonSortieList.add(firstRefBonSortie);

			List<BonSortieFiniValue> bonSortieFiniList = bonSortieFiniPersistance
					.getListByBonSortieList(refBonSortieList);

			if (bonSortieFiniList != null) {
				if (bonSortieFiniList.size() > 0) {
					bonLivraisonValue.setPartieIntId(bonSortieFiniList.get(FIRST_INDEX).getPartieInt());
				}

			}
		}

		List<String> palettes = new ArrayList<String>();

		for (DetLivraisonVenteValue detLivraisonVente : bonLivraisonValue.getListDetLivraisonVente()) {

			if (detLivraisonVente.getQuantite() != null & detLivraisonVente.getPrixUnitaireHT() != null) {
				detLivraisonVente
						.setPrixTotalHT(detLivraisonVente.getQuantite() * detLivraisonVente.getPrixUnitaireHT());
			}

			if (detLivraisonVente.getPrixTotalHT() != null) {
				montantHTaxeTotal = montantHTaxeTotal + detLivraisonVente.getPrixTotalHT();
			}

			if (detLivraisonVente.getRemise() != null && detLivraisonVente.getPrixTotalHT() != null) {
				montantRemiseTotal = montantRemiseTotal
						+ (detLivraisonVente.getPrixTotalHT() * detLivraisonVente.getRemise()) / 100;
			}

			if (detLivraisonVente.getQuantite() != null)
				metrageTotal += detLivraisonVente.getQuantite();
			if (detLivraisonVente.getNombreColis() != null)
				nbreColisTtotal += detLivraisonVente.getNombreColis();

			if (!palettes.contains(detLivraisonVente.getPalette()))
				palettes.add(detLivraisonVente.getPalette());

		}

		if (palettes != null && palettes.size() > 0)
			nbrePaletteTotal = new Long(palettes.size());

		Double montantTTCTotal = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);
		bonLivraisonValue.setMontantTTC(montantTTCTotal);
		bonLivraisonValue.setMetrageTotal(metrageTotal);
		bonLivraisonValue.setTotalColis(nbreColisTtotal);
		bonLivraisonValue.setTotalPalette(nbrePaletteTotal);

		// if(bonLivraisonValue.getTotalColisReel()==null)
		bonLivraisonValue.setTotalColisReel(nbreColisTtotal);


		return  bonLivraisonPersistance.createBonLivraison(bonLivraisonValue);
	}

	@Override
	public LivraisonVenteValue getBonLivraisonById(Long id) {
		//// System.out.println("######## Fin de creation de Bon de livraison domaine :
		//// "+new Date());

		LivraisonVenteValue livraisonVenteValue = bonLivraisonPersistance.getBonLivraisonById(id);

		if (livraisonVenteValue.getListDetLivraisonVente() != null) {

			Collections.sort(livraisonVenteValue.getListDetLivraisonVente(), new DetLivraisonVenteComparator());

		}
		// TEST
		//// System.out.println("######## Fin de Recuperation de Bon de livraison
		// Domaine: "+new Date());
		return livraisonVenteValue;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String updateBonLivraison(LivraisonVenteValue bonLivraisonValue) {

		Double montantTaxeFodec = ZERO;
		Double montantTaxeTVA = ZERO;
		Double montantTaxeTimbre = ZERO;

		Double assietteFodec = ZERO;
		Double assietteTVA = ZERO;

		Double montantHTaxeTotal = ZERO;
		Double montantTaxesTotal = ZERO;

		Double montantRemiseTotal = ZERO;
		Double metrageTotal = ZERO;

		Long nbreColisTtotal = ZERO_LONG;

		Long nbrePaletteTotal = ZERO_LONG;

		if (bonLivraisonValue.getInfoSortie() != null && (!bonLivraisonValue.getInfoSortie().equals(""))) {
			String infoSortieSplitted[] = bonLivraisonValue.getInfoSortie().split(SEPARATOR);

			String firstRefBonSortie = infoSortieSplitted[FIRST_INDEX];

			List<String> refBonSortieList = new ArrayList<String>();
			refBonSortieList.add(firstRefBonSortie);

			List<BonSortieFiniValue> bonSortieFiniList = bonSortieFiniPersistance
					.getListByBonSortieList(refBonSortieList);

			if (bonSortieFiniList != null) {
				if (bonSortieFiniList.size() > 0) {
					bonLivraisonValue.setPartieIntId(bonSortieFiniList.get(FIRST_INDEX).getPartieInt());
				}
				nbrePaletteTotal = new Long(bonSortieFiniList.size() + 1);
			}
		}

		List<String> palettes = new ArrayList<String>();

		if (bonLivraisonValue.getListDetLivraisonVente() != null)
			for (DetLivraisonVenteValue detLivraisonVente : bonLivraisonValue.getListDetLivraisonVente()) {
				if (detLivraisonVente.getQuantite() != null & detLivraisonVente.getPrixUnitaireHT() != null) {
					detLivraisonVente
							.setPrixTotalHT(detLivraisonVente.getQuantite() * detLivraisonVente.getPrixUnitaireHT());
				}

				if (detLivraisonVente.getPrixTotalHT() != null) {
					montantHTaxeTotal = montantHTaxeTotal + detLivraisonVente.getPrixTotalHT();
				}
//			if(detLivraisonVente.getQuantite() != null && !bonLivraisonValue.getNatureLivraison().equals("FACON")){
//				metrageTotal= metrageTotal + detLivraisonVente.getQuantite();
//			}

				if (detLivraisonVente.getRemise() != null && detLivraisonVente.getPrixTotalHT() != null) {
					montantRemiseTotal = montantRemiseTotal
							+ (detLivraisonVente.getPrixTotalHT() * detLivraisonVente.getRemise()) / 100;
				}

				if (detLivraisonVente.getQuantite() != null)
					metrageTotal += detLivraisonVente.getQuantite();
				if (detLivraisonVente.getNombreColis() != null)
					nbreColisTtotal += detLivraisonVente.getNombreColis();

				if (!palettes.contains(detLivraisonVente.getPalette()))
					palettes.add(detLivraisonVente.getPalette());

			}

		if (palettes != null && palettes.size() > 0)
			nbrePaletteTotal = new Long(palettes.size());

		Double montantTTC = montantHTaxeTotal + montantTaxesTotal /*- montantRemiseTotal*/;

		bonLivraisonValue.setMontantHTaxe(montantHTaxeTotal);
		bonLivraisonValue.setMontantRemise(montantRemiseTotal);
		bonLivraisonValue.setMontantTaxe(montantTaxesTotal);
		bonLivraisonValue.setMontantTTC(montantTTC);
		bonLivraisonValue.setMetrageTotal(metrageTotal);
		bonLivraisonValue.setTotalColis(nbreColisTtotal);
		bonLivraisonValue.setTotalPalette(nbrePaletteTotal);

		// if(bonLivraisonValue.getTotalColisReel()==null)
		bonLivraisonValue.setTotalColisReel(nbreColisTtotal);

		return bonLivraisonPersistance.updateBonLivraison(bonLivraisonValue);
	}

	@Override
	public void deleteBonLivraison(Long id) {

		bonLivraisonPersistance.deleteBonLivraison(id);
	}

	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonLivraisonList, Long factureVenteId) {

		return null;
	}

	@Override
	public List<String> getListBonLivraisonRef() {

		List<String> listBonLivraisonToRemove = new ArrayList<String>();

		// List<FactureVenteValue> factureVenteList = facturePersistance.getAll();

		List<String> listBonLivraison = new ArrayList<String>();

//		for(FactureVenteValue livraisonVente : factureVenteList){
//			
//			String infoSortieSplitted[];
//			
//			if(livraisonVente.getInfoLivraison() != null){
//				
//				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);
//				
//				for(int index=0; index < infoSortieSplitted.length ;index++){
//
//					listBonLivraisonToRemove.add(infoSortieSplitted[index]);
//					
//				}
//			}
//			
//		}
//		
//		
//		
//		List<LivraisonVenteValue> bonLivraisonlist = bonLivraisonPersistance.getAll();
//		
//		for(LivraisonVenteValue livraisonVente : bonLivraisonlist){
//			if(livraisonVente.getReference() != null && livraisonVente.getReference().length() != 0)
//				listBonLivraison.add(livraisonVente.getReference());
//		}
//		
//		listBonLivraison.removeAll(listBonLivraisonToRemove);
//		

		return listBonLivraison;
	}

	@Override
	public List<LivraisonVenteVue> getListBonLivraisonRefByClient(Long idClient) {

		List<LivraisonVenteVue> bonLivraisonListFinal = new ArrayList<>();

//		List<String> listBonLivraisonToRemove = new ArrayList<String>();
//		
//		List<FactureVenteValue> factureVenteList = facturePersistance.getAll();
//		
//		for(FactureVenteValue livraisonVente : factureVenteList){
//			
//			String infoSortieSplitted[];
//			
//			if(livraisonVente.getInfoLivraison() != null){
//				
//				infoSortieSplitted = livraisonVente.getInfoLivraison().split(SEPARATOR);
//				
//				for(int index=0; index < infoSortieSplitted.length ;index++){
//
//					listBonLivraisonToRemove.add(infoSortieSplitted[index]);
//					
//				}
//			}
//			
//		}
//		logger.info("-------listBonLivraisonToRemove-------------"+listBonLivraisonToRemove);
//
//		List<LivraisonVenteVue> bonLivraisonlist = bonLivraisonPersistance.getReferenceBLByClientId(idClient);
//	
//		for (LivraisonVenteVue livraisonVenteVue : bonLivraisonlist) {
//			
//			if (!listBonLivraisonToRemove.contains(livraisonVenteVue.getReferenceBL())) {
//				//logger.info("-------found-------------");
//				bonLivraisonListFinal.add(livraisonVenteVue);
//				
//				
//			}
//			
//			
//		}
////		logger.info("-------bonLivraisonlist after remove All-------------"+bonLivraisonlist);
//
//	//	bonLivraisonlist.removeAll(listBonLivraisonToRemove);
//		logger.info("-------bonLivraisonlist non facturé-------------"+bonLivraisonlist);
		return bonLivraisonListFinal;
	}

	@Override
	public LivraisonVenteValue getByReference(String reference) {

		return bonLivraisonPersistance.getByReference(reference);
	}

	@Override
	public List<LivraisonVenteValue> getAll() {
		return bonLivraisonPersistance.getAll();
	}

}
