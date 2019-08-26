package com.gpro.consulting.tiers.gpao.domaine.bonsortiefini.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gpro.consulting.tiers.commun.coordination.baseinfo.value.BaseInfoValue;
import com.gpro.consulting.tiers.commun.coordination.value.ProduitValue;
import com.gpro.consulting.tiers.commun.coordination.value.SousFamilleProduitValue;
import com.gpro.consulting.tiers.commun.persistance.IProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.ISousFamilleProduitPersistance;
import com.gpro.consulting.tiers.commun.persistance.baseinfo.IBaseInfoPersistance;
import com.gpro.consulting.tiers.commun.service.baseinfo.IBaseInfoService;
import com.gpro.consulting.tiers.gpao.coordination.bonlivraison.value.DetLivraisonVenteValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.report.value.GroupementRouleauFiniChoixReportValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.report.value.ListPaletteElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.report.value.RouleauFiniReportGroupedByProduitValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.report.value.RouleauFiniReportValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.BonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListProduitElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ListTraitFaconElementValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.RechercheMulticritereBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.bonsortiefini.value.ResultatRechecheBonSortieFiniValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.FicheColisageValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.RechercheMulticritereColisValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisElementValue;
import com.gpro.consulting.tiers.gpao.coordination.fichecolisage.value.ResultatRechecheColisValue;
import com.gpro.consulting.tiers.gpao.coordination.value.OrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.RechercheMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.coordination.value.ResultatMulticritereOrdreFabricationValue;
import com.gpro.consulting.tiers.gpao.domaine.IColisDomaine;
import com.gpro.consulting.tiers.gpao.domaine.IOrdreFabricationDomaine;
import com.gpro.consulting.tiers.gpao.domaine.bonlivraison.utilities.DetLivraisonVenteValidateComparator;
import com.gpro.consulting.tiers.gpao.domaine.bonsortiefini.IBonSortieFiniDomain;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.IBonLivraisonPersistance;
import com.gpro.consulting.tiers.gpao.persitance.bonlivraison.IDetLivraisonVentePersistance;
import com.gpro.consulting.tiers.gpao.persitance.bonsortiefini.IBonSortieFiniPersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IColisPersistance;
import com.gpro.consulting.tiers.gpao.persitance.fichecolisage.IFicheColisagePersistance;

/**
 * Implementation of {@link IBonSortieFiniDomain}
 * 
 * @author Wahid Gazzah
 * @since 08/01/2016
 *
 */
@Component
public class BonSortieFiniDomainImpl implements IBonSortieFiniDomain {

	private static final Logger logger = LoggerFactory.getLogger(BonSortieFiniDomainImpl.class);

	private static final Double ZERO = 0D;
	private static final String MAILLE = "maille";
	private static final String KG = "kg";
	private static final String M = "m";
	private static final String EMPTY = "";
	private static final String SEPARATOR = "-";

	@Autowired
	private IBonSortieFiniPersistance bonSortieFiniPersistance;

	@Autowired
	private IColisPersistance rouleauFiniPersistance;

	@Autowired
	private IColisDomaine rouleauFiniDomaine;

	@Autowired
	private IProduitPersistance produitPersistance;

	@Autowired
	private ISousFamilleProduitPersistance sousFamilleProduitPersistance;

	@Autowired
	private IDetLivraisonVentePersistance detLivraisonVentePersistance;

	@Autowired
	private IBonLivraisonPersistance bonLivraisonPersistance;

	@Autowired
	private IOrdreFabricationDomaine miseDomaine;

	@Autowired
	private IFicheColisagePersistance ficheColisagePersistence;

	@Autowired
	IBaseInfoPersistance baseInfoPersistance;

	// TODO Changed
	@Override
	public List<ColisValue> validateBonSortieFini(List<String> barreCodeList, Long id) {

		List<ColisValue> listeRouleauFini = rouleauFiniPersistance.getRouleauFiniListByBarreCodeList(barreCodeList, id);

		Long ordre = 0L;

		if (id != null)

		{
			for (ColisValue colis : listeRouleauFini) {

				if (colis.getOrdrePalette() != null)
					if (!colis.getOrdrePalette().equals("")) {
						Long op = new Long(colis.getOrdrePalette());

						if (op > ordre) {
							// System.out.println(">>>>>>>>>>>>>>>"+colis.getId()+" ordrePalette = "+ op);
							ordre = op;
						}

					}

			}

		}

		// ordre=new Long(listeRouleauFini.size());

		for (ColisValue colis : listeRouleauFini) {

			if (colis.getFicheColisageId() != null) {
				FicheColisageValue fiche = ficheColisagePersistence.getById(colis.getFicheColisageId());

				if (fiche != null) {

					colis.setOrdreNumero(fiche.getOrdreFabricationNumero());

					colis.setProduitReference(fiche.getProduitReference());

					colis.setProduitDesignation(fiche.getProduitDesignation());

				}

			}

			if (colis.getOrdrePalette() == null) {
				ordre++;
				colis.setOrdrePalette(ordre.toString());

			}

		}

		Collections.sort(listeRouleauFini);

		return listeRouleauFini;
	}

	@Override
	public String createBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {

		if (bonSortieFiniValue.getReference() == null || bonSortieFiniValue.getReference().equals("")) {

			bonSortieFiniValue.setReference(getNumeroBonSotie(Calendar.getInstance()));
			// bonSortieFiniValue.getReference().concat(getNumeroBonSotie(Calendar.getInstance()));

		}

		if (bonSortieFiniValue.getReference() != null && bonSortieFiniValue.getBlExport() != null
				&& !bonSortieFiniValue.getReference().equals("") && !bonSortieFiniValue.getBlExport().equals("")) {
			String referenceBon = "";
			referenceBon = bonSortieFiniValue.getBlExport() + "/" + bonSortieFiniValue.getReference();

			bonSortieFiniValue.setReferenceBon(referenceBon);

		}

		List<ColisValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();
		List<String> listeOrdreFabricationDistinct = new ArrayList<String>();

		Map<Long, Double> metrageMap = new HashMap<Long, Double>();

		Double metrageTotal = ZERO;

		// System.out.println("##### listeRouleauFini : "+listeRouleauFini.size());

		List<String> listOrdrePalette = new ArrayList<String>();

		if (listeRouleauFini != null) {
			// retrieve the old METRAGE data for each element from
			// listeRouleauFini
			for (ColisValue rouleauFini : listeRouleauFini) {

				if (rouleauFini != null) {
						

					rouleauFini.setOrdre(rouleauFini.getAncienOrdre());
					if (rouleauFini.getQuantite() != null) {

						metrageTotal = metrageTotal + rouleauFini.getQuantite();
					}
					// metrageTotal = metrageTotal + rouleauFini.getMetrage();

					if (rouleauFini.getOrdrePalette() != null) {
						if (!listOrdrePalette.contains(rouleauFini.getOrdrePalette()))
						{
							listOrdrePalette.add(rouleauFini.getOrdrePalette());
							rouleauFini.setFictif(null);
						}
							
						else
						{
							rouleauFini.setFictif(true);
						}
							
					}

				}

			}
		}

		if (bonSortieFiniValue.getSpecial() != null) {
			if (bonSortieFiniValue.getSpecial() == true)
				bonSortieFiniValue.setNbColis(listOrdrePalette.size());
			else
				bonSortieFiniValue.setNbColis(listeRouleauFini.size());
		} else {
			bonSortieFiniValue.setNbColis(listeRouleauFini.size());
		}

		bonSortieFiniValue.setFini("FINI");
		bonSortieFiniValue.setMetrageTotal(metrageTotal);

		bonSortieFiniValue.setListeRouleauFini(listeRouleauFini);

		// System.out.println("*-*-******
		// bonSortieFiniValue.setListeRouleauFini(listeRouleauFini:
		// "+bonSortieFiniValue.getListeRouleauFini().size());

		String idBonSortieFiniSaved = bonSortieFiniPersistance.createBonSortieFini(bonSortieFiniValue);

		if (listeRouleauFini != null) {
			// retrieve the old METRAGE data for each element from
			// listeRouleauFini
			for (ColisValue rouleauFini : listeRouleauFini) {

				rouleauFini.setDateSortie(bonSortieFiniValue.getDateSortie());
				// rouleauFini.setb

				if (rouleauFini.getId() != null) {

					if (rouleauFini.getQuantite() != null) {

						metrageTotal = metrageTotal + rouleauFini.getQuantite();
					}
				}

				rouleauFini.setBonSortie(new Long(idBonSortieFiniSaved));

				rouleauFini.setOrdre(rouleauFini.getAncienOrdre());

				// System.out.println("###### idBonSortieFiniSaved : "+idBonSortieFiniSaved);

				rouleauFiniPersistance.modifierColis(rouleauFini);
				
				if(!listeOrdreFabricationDistinct.contains(rouleauFini.getOrdreNumero()))
				{
					listeOrdreFabricationDistinct.add(rouleauFini.getOrdreNumero());
					
				}
			}
		}
		
		//update qte expedition d'ordre de fabrication
		
		RechercheMulticritereColisValue rechColisMultiCritaire = new RechercheMulticritereColisValue();
		rechColisMultiCritaire.setHasPalette(true);
		
	
		for (String of : listeOrdreFabricationDistinct)
		{
			rechColisMultiCritaire.setNumeroOF(of);
			
			
			ResultatRechecheColisValue resultatRechColisMultiCritaire = rouleauFiniDomaine.rechercherMultiCritere(rechColisMultiCritaire);
			if (resultatRechColisMultiCritaire != null && resultatRechColisMultiCritaire.getNombreResultaRechercher() != null && resultatRechColisMultiCritaire.getNombreResultaRechercher() > 0)
			{
			  Long sommeQteColis = 0l;
				for (ResultatRechecheColisElementValue elementColis : resultatRechColisMultiCritaire.getList())
				{
					if(elementColis.getQuantite() != null) 
						sommeQteColis += elementColis.getQuantite();
				}
				
				OrdreFabricationValue ofValue = miseDomaine.getByNumero(of);
				 if (ofValue != null)
				 {
					 ofValue.setQtExpedition(sommeQteColis);
					 miseDomaine.modifierOrdreFabrication(ofValue);
				 }
				
			}
			
		
			
		}

		return idBonSortieFiniSaved;
	}

	@Override
	public BonSortieFiniValue getBonSortieFiniById(Long id) {

		return bonSortieFiniPersistance.getBonSortieFiniById(id);
	}

	@Override
	public ResultatRechecheBonSortieFiniValue rechercherMultiCritere(RechercheMulticritereBonSortieFiniValue request) {

		return bonSortieFiniPersistance.rechercherMultiCritere(request);
	}

	@Override
	public String updateBonSortieFini(BonSortieFiniValue bonSortieFiniValue) {

		List<ColisValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();
		List<Long> listeIdBonsortie = new ArrayList<Long>();
		
		for (ColisValue c : listeRouleauFini) {

			if(c.getId() != null)
			listeIdBonsortie.add(c.getId());
		}
		// Put NULL to bonsortieId for each RouleauFini deleted from
		// BonSortieFini
		BonSortieFiniValue oldBonSortieFiniValue = bonSortieFiniPersistance
				.getBonSortieFiniById(bonSortieFiniValue.getId());

		if (bonSortieFiniValue.getReference() != null && bonSortieFiniValue.getBlExport() != null
				&& !bonSortieFiniValue.getReference().equals("") && !bonSortieFiniValue.getBlExport().equals("")) {
			String referenceBon = "";
			referenceBon = bonSortieFiniValue.getBlExport() + "/" + bonSortieFiniValue.getReference();

			bonSortieFiniValue.setReferenceBon(referenceBon);

		}

		for (ColisValue rouleauFini : oldBonSortieFiniValue.getListeRouleauFini()) {
			rouleauFini.setOrdre(rouleauFini.getAncienOrdre());
			//if (!listeRouleauFini.contains(rouleauFini)) {
				
				if(!listeIdBonsortie.contains(rouleauFini.getId())) {
				//System.out.println("####################ID"+rouleauFini.getId());
				
				rouleauFini.setBonSortie(null);
				rouleauFini.setDateSortie(null);
				rouleauFini.setOrdre(rouleauFini.getAncienOrdre());
				// ajouter par samer le 30.04.19
				rouleauFini.setOrdrePalette(null);
				rouleauFini.setFictif(null);
				// TODO UPDATE COLIS
				rouleauFiniPersistance.modifierColis(rouleauFini);
				
				// ajouter par samer le 07.06.19 pour permettre la modification de qte expedition d'un of
				
				FicheColisageValue ficheColisage = ficheColisagePersistence.getById(rouleauFini.getFicheColisageId());
			
				if (ficheColisage != null && ficheColisage.getOrdreFabricationNumero() != null)
				{
					OrdreFabricationValue ofValue = miseDomaine.getByNumero(ficheColisage.getOrdreFabricationNumero());
					 if (ofValue != null)
					 {
						 Long nouvelleQteExpedition = ofValue.getQtExpedition() - rouleauFini.getQuantite();
						 ofValue.setQtExpedition(nouvelleQteExpedition);
						 miseDomaine.modifierOrdreFabrication(ofValue);
					 }
				}
			

			}
		}

		Map<Long, Double> metrageMap = new HashMap<Long, Double>();

		Double metrageTotal = ZERO;
		List<String> listOrdrePalette = new ArrayList<String>();
		List<String> listeOrdreFabricationDistinct = new ArrayList<String>();
		
		if (listeRouleauFini != null) {
			// retrieve the old METRAGE data for each element from
			// listeRouleauFini
			for (ColisValue rouleauFini : listeRouleauFini) {

				if (rouleauFini != null) {

					if (rouleauFini.getQuantite() != null) {

						metrageTotal = metrageTotal + rouleauFini.getQuantite();
					}
					// metrageTotal = metrageTotal + rouleauFini.getMetrage();
         
					if (rouleauFini.getOrdrePalette() != null) {
						if (!listOrdrePalette.contains(rouleauFini.getOrdrePalette()))
						{
							listOrdrePalette.add(rouleauFini.getOrdrePalette());
							rouleauFini.setFictif(null);
						}
							
						else
						{
							rouleauFini.setFictif(true);
						}
							
					}
					
					
				}

			}
		}

		if (bonSortieFiniValue.getSpecial() != null) {
			if (bonSortieFiniValue.getSpecial() == true)
				bonSortieFiniValue.setNbColis(listOrdrePalette.size());
			else
				bonSortieFiniValue.setNbColis(listeRouleauFini.size());
		} else {
			bonSortieFiniValue.setNbColis(listeRouleauFini.size());
		}

		bonSortieFiniValue.setMetrageTotal(metrageTotal);
		bonSortieFiniValue.setFini("FINI");

		String idBonSortieFiniSaved = bonSortieFiniPersistance.updateBonSortieFini(bonSortieFiniValue);

		// TODO A VERIFIER
		if (listeRouleauFini != null) {

			for (ColisValue rouleauFini : listeRouleauFini) {

				rouleauFini.setBonSortie(Long.valueOf(idBonSortieFiniSaved).longValue());
				rouleauFini.setDateSortie(bonSortieFiniValue.getDateSortie());
				rouleauFini.setOrdre(rouleauFini.getAncienOrdre());
				rouleauFiniPersistance.modifierColis(rouleauFini);

				// create a new rouleauFini if:
				// rouleauFini.metrageModif is true
				// newMetrage not null,
				// and the oldMetrage > rouleauFini.metrage
				
				if(!listeOrdreFabricationDistinct.contains(rouleauFini.getOrdreNumero()))
				{
					listeOrdreFabricationDistinct.add(rouleauFini.getOrdreNumero());
					
				}

			}

		}
		
		
		//update qte expedition d'ordre de fabrication
		
		RechercheMulticritereColisValue rechColisMultiCritaire = new RechercheMulticritereColisValue();
		rechColisMultiCritaire.setHasPalette(true);
		
	
		for (String of : listeOrdreFabricationDistinct)
		{
			rechColisMultiCritaire.setNumeroOF(of);
			
			
			ResultatRechecheColisValue resultatRechColisMultiCritaire = rouleauFiniDomaine.rechercherMultiCritere(rechColisMultiCritaire);
			if (resultatRechColisMultiCritaire != null && resultatRechColisMultiCritaire.getNombreResultaRechercher() != null && resultatRechColisMultiCritaire.getNombreResultaRechercher() > 0)
			{
			  Long sommeQteColis = 0l;
				for (ResultatRechecheColisElementValue elementColis : resultatRechColisMultiCritaire.getList())
				{
					if(elementColis.getQuantite() != null) 
						sommeQteColis += elementColis.getQuantite();
				}
				
				OrdreFabricationValue ofValue = miseDomaine.getByNumero(of);
				 if (ofValue != null)
				 {
					 ofValue.setQtExpedition(sommeQteColis);
					 miseDomaine.modifierOrdreFabrication(ofValue);
				 }
				
			}
			
		
			
		}
		

		return idBonSortieFiniSaved;
	}

	@Override
	public void deleteBonSortieFini(Long id) {

		BonSortieFiniValue bonSortieFiniValue = bonSortieFiniPersistance.getBonSortieFiniById(id);
		List<Long> listeIdFicheColisageDistinct = new ArrayList<Long>();

		if (bonSortieFiniValue != null) {
			List<ColisValue> listeRouleauFini = bonSortieFiniValue.getListeRouleauFini();
			for (ColisValue rouleauFiniValue : listeRouleauFini) {
				
				
				rouleauFiniValue.setBonSortie(null);
				rouleauFiniValue.setDateSortie(null);
				rouleauFiniValue.setOrdrePalette(null);
				rouleauFiniValue.setFictif(null);

				// TODO
				// rouleauFiniPersistance.updateRouleauFini(rouleauFiniValue);
				rouleauFiniPersistance.modifierColis(rouleauFiniValue);
				
			
					//update qte expedition d'un of
					FicheColisageValue ficheColisage = ficheColisagePersistence.getById(rouleauFiniValue.getFicheColisageId());
					
					if (ficheColisage != null && ficheColisage.getOrdreFabricationNumero() != null)
					{
						OrdreFabricationValue ofValue = miseDomaine.getByNumero(ficheColisage.getOrdreFabricationNumero());
						 if (ofValue != null)
						 {
							 Long nouvelleQteExpedition = ofValue.getQtExpedition() - rouleauFiniValue.getQuantite();
							 ofValue.setQtExpedition(nouvelleQteExpedition);
							 miseDomaine.modifierOrdreFabrication(ofValue);
						 }
					}
					
				
			}

			bonSortieFiniValue.setListeRouleauFini(null);
			bonSortieFiniPersistance.updateBonSortieFini(bonSortieFiniValue);
			bonSortieFiniPersistance.deleteBonSortieFini(id);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ListProduitElementValue getProduitElementList(List<String> refBonSortieList, Long livraisonVenteId) {

		Double cout_minute = 0D;
	/*	List<BaseInfoValue> baseInfoValues = baseInfoPersistance.getAll();
		String clientDesigntion = "";
		for (BaseInfoValue baseInf : baseInfoValues) {
			if (baseInf.isActif()) {
				cout_minute = baseInf.getCoutMinute();
			}
		}
  */
		// TODO BL VALIDATE
		List<BonSortieFiniValue> listeBonSortieFini = bonSortieFiniPersistance.getListByBonSortieList(refBonSortieList);
//
		List<ColisValue> listeRouleauFini = new ArrayList<ColisValue>();
//
		boolean searchDateSortie = true;
		Calendar dateSortie = null;

		String observations = "";

//
		for (BonSortieFiniValue bonSortie : listeBonSortieFini) {

			if (bonSortie.getDateSortie() != null && searchDateSortie) {
				dateSortie = bonSortie.getDateSortie();
			}

			if (bonSortie.getObservation() != null && !bonSortie.getObservation().equals(""))

				observations += "  -" + bonSortie.getObservation();

			for (ColisValue rouleau : bonSortie.getListeRouleauFini()) {

				listeRouleauFini.add(rouleau);
			}
		}
//
		// Map<Map<Long, Long>, List<ColisValue>> mapRouleau = new HashMap<Map<Long,
		// Long>, List<ColisValue>>();
//

		///////////////////// NEW TRAITEMENT
		///////////////////// //////////////////////////////////////////////////////////////////

		/** Grouper par produit id */

		List<ListPaletteElementValue> listePalette = new ArrayList<ListPaletteElementValue>();

		for (BonSortieFiniValue bonSortieFiniValue : listeBonSortieFini) {
			ListPaletteElementValue palette = new ListPaletteElementValue();
			Map<Long, List<ColisValue>> mapRouleau = new HashMap<Long, List<ColisValue>>();
			for (ColisValue rouleau : bonSortieFiniValue.getListeRouleauFini()) {
				Long key = rouleau.getFicheColisageId();
				if (mapRouleau.get(key) == null) {
					mapRouleau.put(key, new ArrayList<ColisValue>());
				}
				mapRouleau.get(key).add(rouleau);
			}

			Iterator it = mapRouleau.entrySet().iterator();
			List<RouleauFiniReportGroupedByProduitValue> listRouleauFiniReportGrouppedByProduit = new ArrayList<>();
			while (it.hasNext()) {
				// Création d'un nouveau Elément
				RouleauFiniReportGroupedByProduitValue element = new RouleauFiniReportGroupedByProduitValue();

				Map.Entry<Long, List<ColisValue>> pair = (Map.Entry<Long, List<ColisValue>>) it.next();
				/** Enrichissement d'un element */

				// Produit
				element.setProduitId(pair.getKey());
				Double prixUnitaire = 0D;
				if (element.getProduitId() != null) {
					FicheColisageValue produitValue = ficheColisagePersistence.getById(element.getProduitId());

					if (produitValue != null) {

						SousFamilleProduitValue sousFamilleProduitValue = null;

						element.setRefTissu(produitValue.getProduitReference());

						if (produitValue.getOrdreFabricationId() != null) {

							OrdreFabricationValue of = miseDomaine
									.rechercheOrdreFabricationParId(produitValue.getOrdreFabricationId());
							// if (of)
							
							if (of!=null)
							element.setTissu(of.getProduitDesignation());
							else 
								element.setTissu(produitValue.getProduitDesignation());

							
							
							prixUnitaire = of.getPrixUnitaire();

							// System.out.println("###### TPS : "+of.getTemps());

							// System.out.println("############# cout_minute : "+cout_minute);

				/*			if (prixUnitaire == null || (prixUnitaire != null && prixUnitaire == 0D)) {
								if (of.getTemps() != null && cout_minute != null)

								{
									prixUnitaire = of.getTemps() * cout_minute;
								}

							}
							
				*/

							if (of.getProduitId() != null) {
								ProduitValue prod = produitPersistance.rechercheProduitById(of.getProduitId());

								if (prod != null) {

									element.setRefTissu(prod.getReference());

								}

							}

						}

						// OF
						element.setComposition(produitValue.getOrdreFabricationNumero());
					
						// System.out.println("##### ################## PU: "+prixUnitaire);

						element.setPrixUnitaire(prixUnitaire);

					}
				}

				Double totalMetrage = 0D;
				Double totalPoids = 0D;

				List<RouleauFiniReportValue> listRouleauFiniReportValue = new ArrayList<RouleauFiniReportValue>();
				Map<GroupementRouleauFiniChoixReportValue, Long> mapGroupement = new HashMap<GroupementRouleauFiniChoixReportValue, Long>();

				for (ColisValue rouleauFiniValue : pair.getValue()) {
					RouleauFiniReportValue rouleauFiniReportValue = new RouleauFiniReportValue();

					if (rouleauFiniValue.getQuantite() != null) {
						totalMetrage = rouleauFiniValue.getQuantite().doubleValue() + totalMetrage;
					}

					element.setTotalMetrage(totalMetrage);
					element.setTotalPoids(totalPoids);

					if (rouleauFiniValue.getTailleDesignation() != null) {

						rouleauFiniReportValue.setChoix(rouleauFiniValue.getTailleDesignation());

					}
					// rouleauFiniReportValue.setLaize(rouleauFiniValue.getLaize());
					rouleauFiniReportValue.setMetrage(rouleauFiniValue.getQuantite().doubleValue());

					System.out.println(
							"#######  VALUE   :    rouleauFiniValue.getFictif()  :  " + rouleauFiniValue.getFictif());

					rouleauFiniReportValue.setFictif(rouleauFiniValue.getFictif());

					rouleauFiniReportValue.setCarton(rouleauFiniValue.getOrdrePalette());

//				
//				if (element.getProduitId() != null) 
//					{FicheColisageValue produitValue = ficheColisagePersistance.getById(element.getProduitId());
//				      rouleauFiniReportValue.setMise(produitValue.getOrdreFabricationNumero());
//				
//					}
					rouleauFiniReportValue.setRefRouleau(rouleauFiniValue.getId().toString());
					// avec mise decide si on affiche le rapport avec Mise ou Pas

					// Rapport Groupe

					// System.out.println("#### ENTER TO GRP #################");

					// System.out.println("#### ENTER TO GRP ################# KEY ");

					// TODO Changement Special
					Boolean special = false;

					if (bonSortieFiniValue.getSpecial() != null)
						special = bonSortieFiniValue.getSpecial();

					GroupementRouleauFiniChoixReportValue key = getGroupementByQteChoix(
							rouleauFiniReportValue.getChoix(), rouleauFiniReportValue.getMetrage(), mapGroupement,
							special);

					//// System.out.println("#### ENTER TO GRP ################# KEY / "
					//// +key.toString());

					Long nombreColis = 1L;
					if (key != null) { // System.out.println("#### ENTER TO GRP ################# KEY / "
										// +key.toString());
						nombreColis += mapGroupement.get(key);
						key.setFictif(rouleauFiniReportValue.getFictif());

						if (bonSortieFiniValue.getSpecial() != null)
							if (bonSortieFiniValue.getSpecial() == true)
								key.setCarton(rouleauFiniReportValue.getCarton());

					}

					else {
						// System.out.println("#### ENTER TO GRP ################# KEY / NULL ");
						key = new GroupementRouleauFiniChoixReportValue();
						key.setChoix(rouleauFiniReportValue.getChoix());
						key.setQuantite(rouleauFiniReportValue.getMetrage());
						// if (rouleauFiniValue.getFictif()==true)

						if (bonSortieFiniValue.getSpecial() != null)
							if (bonSortieFiniValue.getSpecial() == true)
								key.setCarton(rouleauFiniReportValue.getCarton());

						// System.out.println("####### VALUE : rouleauFiniReportValue.getFictif() :
						// "+rouleauFiniReportValue.getFictif());

						key.setFictif(rouleauFiniReportValue.getFictif());
					}
					mapGroupement.put(key, nombreColis);

					//// System.out.println("####### test fin MAP SIZE : "+mapGroupement.size());

					// if (special==true)

					// mapGroupement

				}

				// System.out.println("#### Liste de Cles : "+mapGroupement.keySet().size());

				for (GroupementRouleauFiniChoixReportValue grp : mapGroupement.keySet()) {

					RouleauFiniReportValue newRouleau = new RouleauFiniReportValue();

					// champ Nombre de colis modifier
					newRouleau.setRefRouleau(mapGroupement.get(grp).toString());

					System.out.println("#######  VALUE   :   grp.getFictif():  " + grp.getFictif());

					newRouleau.setFictif(grp.getFictif());
					if (bonSortieFiniValue.getSpecial() != null)
						if (bonSortieFiniValue.getSpecial() == true)
							newRouleau.setCarton(grp.getCarton());

					newRouleau.setMetrage(grp.getQuantite());
					newRouleau.setChoix(grp.getChoix());
					listRouleauFiniReportValue.add(newRouleau);
				}

				// System.out.println("####### intiale listRouleauFiniReportValue :
				// "+bonSortieFiniValue.getListeRouleauFini().size());

				// System.out.println("####### test fin MAP SIZE : "+mapGroupement.size());

				// System.out.println("####### listRouleauFiniReportValue Final :
				// "+listRouleauFiniReportValue.size());

				element.setList(listRouleauFiniReportValue);

				listRouleauFiniReportGrouppedByProduit.add(element);
				it.remove();
			}
			palette.setPalette(bonSortieFiniValue.getReference());
			palette.setDateSortie(bonSortieFiniValue.getDateSortie());
			palette.setNombreResultaRechercher(listRouleauFiniReportGrouppedByProduit.size());
			palette.setListOFPalette(listRouleauFiniReportGrouppedByProduit);
			// palette
			listePalette.add(palette);

			// System.out.println("*-------------------------------*");
			// System.out.println("----Palette : "+palette.getPalette());
			// System.out.println("----NBRE OF/SEP :
			// "+palette.getNombreResultaRechercher());

			// System.out.println("*-------------------------------*");

		}

		// System.out.println("######## TEST de Creation : "+listePalette.size());

///////////////////// FIN    NEW TRAITEMENT //////////////////////////////////////////////////////////////////

		////////////////////// Remplissage de la liste /////////////////////

		List<DetLivraisonVenteValue> listDetLivraisonVente = new ArrayList<DetLivraisonVenteValue>();
		for (ListPaletteElementValue palette : listePalette) {

			for (RouleauFiniReportGroupedByProduitValue ofPalette : palette.getListOFPalette()) {

				for (RouleauFiniReportValue colis : ofPalette.getList()) {

					// RemplirDetails
					DetLivraisonVenteValue element = new DetLivraisonVenteValue();
					element.setPalette(palette.getPalette());
					element.setNumeroOF(ofPalette.getComposition());
					element.setProduitReference(ofPalette.getRefTissu());
					element.setProduitDesignation(ofPalette.getTissu());
					element.setChoix(colis.getChoix());
					element.setNombreColis(new Long(colis.getRefRouleau()));
					element.setQuantiteColis(colis.getMetrage().longValue());
					element.setQuantite(element.getQuantiteColis() * element.getNombreColis());
					element.setPrixUnitaireHT(ofPalette.getPrixUnitaire());
					element.setUnite(colis.getCarton());

					// Ajouter par samer le 08.04.19
					// System.out.println("########################BONSORTIEFINIDOMAINEIMP ##### OF
					// :"+ofPalette.getComposition());
					OrdreFabricationValue ofV = miseDomaine.getByNumero(ofPalette.getComposition());
					if (ofV != null) {
						/*element.setCodeDouane(ofV.getCodeDouane());
						element.setComposition(ofV.getComposition());

						element.setReferenceTissu1(ofV.getReferenceTissu1());
						element.setReferenceTissu2(ofV.getReferenceTissu2());
						element.setReferenceTissu3(ofV.getReferenceTissu3());
						element.setReferenceTissu4(ofV.getReferenceTissu4());

						element.setDesignationTissu1(ofV.getDesignationTissu1());
						element.setDesignationTissu2(ofV.getDesignationTissu2());
						element.setDesignationTissu3(ofV.getDesignationTissu3());
						element.setDesignationTissu4(ofV.getDesignationTissu4());

						element.setEmploiTissu1(ofV.getEmploiTissu1());
						element.setEmploiTissu2(ofV.getEmploiTissu2());
						element.setEmploiTissu3(ofV.getEmploiTissu3());
						element.setEmploiTissu4(ofV.getEmploiTissu4());

						element.setPrixTissu1(ofV.getPrixTissu1());
						element.setPrixTissu2(ofV.getPrixTissu2());
						element.setPrixTissu3(ofV.getPrixTissu3());
						element.setPrixTissu4(ofV.getPrixTissu4());

						element.setTypeTissu1(ofV.getTypeTissu1());
						element.setTypeTissu2(ofV.getTypeTissu2());
						element.setTypeTissu3(ofV.getTypeTissu3());
						element.setTypeTissu4(ofV.getTypeTissu4());
						*/
					}

					// System.out.println("############ if (colis.getFictif()!=null) :
					// "+colis.getFictif());
					if (colis.getFictif() != null)
						if (colis.getFictif() == true)
							element.setNombreColis(0L);

					listDetLivraisonVente.add(element);

				}

			}

		}

		/////// Fin de Remplissage de la liste //////////////////////////////

		if (listDetLivraisonVente.size() > 0) {
			Collections.sort(listDetLivraisonVente, new DetLivraisonVenteValidateComparator());
		}

		ListProduitElementValue result = new ListProduitElementValue();
//TODO 
		result.setNombreResultaRechercher(listDetLivraisonVente.size());
		result.setDateSortie(dateSortie);
		result.setListDetLivraisonVente(listDetLivraisonVente);
		result.setNombrePalette(listePalette.size());
		result.setObservationsPalettes(observations);

		return result;

	}

	@Override
	public List<String> getListBonSortieFiniRef() {

		// Changé par Ghazi le 23/10/2017

		List<String> listBonSortieFini = new ArrayList<String>();

		List<String> vBonSortieFinilist = bonSortieFiniPersistance.listerReferenceFini();

		// System.out.println("##### LIST REF : "+vBonSortieFinilist.size());

		for (String vReference : vBonSortieFinilist) {

			if (!bonLivraisonPersistance.existeBS(vReference))
				listBonSortieFini.add(vReference);
		}

//		List<String> listBonSortieFiniToRemove = new ArrayList<String>();
//
//		List<LivraisonVenteValue> LivraisonVenteList = bonLivraisonPersistance.getAll();
//
//		for (LivraisonVenteValue livraisonVente : LivraisonVenteList) {
//
//			String infoSortieSplitted[];
//
//			if (livraisonVente.getInfoSortie() != null) {
//
//				infoSortieSplitted = livraisonVente.getInfoSortie().split(SEPARATOR);
//
//				for (int index = 0; index < infoSortieSplitted.length; index++) {
//
//					listBonSortieFiniToRemove.add(infoSortieSplitted[index]);
//
//				}
//			}
//
//		}
//
//		List<String> listBonSortieFini = new ArrayList<String>();
//
//		List<BonSortieFiniValue> bonSortieFinilist = bonSortieFiniPersistance.getAll();
//
//		for (BonSortieFiniValue bonSortieFini : bonSortieFinilist) {
//			if (bonSortieFini.getReference() != null && bonSortieFini.getReference().length() != 0)
//				listBonSortieFini.add(bonSortieFini.getReference());
//		}
//
//		listBonSortieFini.removeAll(listBonSortieFiniToRemove);

		return listBonSortieFini;
	}

	/**
	 * Récuperer le numéro du Bon de receptionà partir du Guichet Bon Reception
	 * 
	 * Le Numéro Bon Reception est unique pour une Bon Reception OSIRIS et doit
	 * respect le format:
	 * 
	 * - AAAA : année du Bon Reception. - NNNNNN : un numéro incrémental au sein de
	 * l'année civile.
	 * 
	 * @param pDateBonReception la date de la Bon Reception
	 * @return le numéro du prochaine Bon Reception à insérer
	 */
	private String getNumeroBonSotie(final Calendar pDateBonSortie) {
		StringBuilder vNumBonSortie = new StringBuilder("");

		// TODO Guicher
//		Long vNumGuichetBonSortie = this.guichetierMensuelDomaine.getNextNumBonSortieReference();
//		/** Année courante. */
//		int vAnneeCourante = pDateBonSortie.get(Calendar.YEAR);
//		int moisActuel = pDateBonSortie.get(Calendar.MONTH) + 1;
//		/** Format du numero de la Bon Reception= AAAA-NN. */
//		
//		vNumBonSortie.append(vAnneeCourante);
//		vNumBonSortie.append(String.format("%02d", moisActuel));
//		vNumBonSortie.append(String.format("%04d", vNumGuichetBonSortie));
//		/** Inserer une nouvelle valeur dans Guichet BonReception. */
//		GuichetMensuelValue vGuichetValeur = new GuichetMensuelValue();
//
//		/** idMensuel = (annuelcourante - 2016) + moisCourant */
//
//		Calendar cal = Calendar.getInstance();
//		int anneActuelle = cal.get(Calendar.YEAR);
//
//		int idMensuel = (anneActuelle - 2016)*12 + moisActuel;
//
//		vGuichetValeur.setId(new Long(idMensuel));
//		vGuichetValeur.setAnnee(new Long(vAnneeCourante));
//		vGuichetValeur.setNumReferenceBonSortieCourante(new Long(vNumGuichetBonSortie + 1L));
//		/** Modification de la valeur en base du numéro. */
//		this.guichetierMensuelDomaine.modifierGuichetBonSortieMensuel(vGuichetValeur);
		return vNumBonSortie.toString();

	}

	private boolean valueOfBoolean(Boolean value) {
		if (value == null)
			return false;
		else
			return value;
	}

	@Override
	public List<String> getListBonSortieFaconRef() {

		List<String> listBonSortieFini = new ArrayList<String>();

		List<String> vBonSortieFinilist = bonSortieFiniPersistance.listerReferenceFacon();

		// TODO LIVRAISON
//		   for (String vReference : vBonSortieFinilist){
//			   if(!bonLivraisonPersistance.existeBS(""+vReference))
//				   listBonSortieFini.add(vReference);
//		   }

		return listBonSortieFini;
	}

	@Override
	public ListTraitFaconElementValue getTraitFaconElementList(List<String> refBonLivraisonList, Long factureVenteId) {
		// TODO Auto-generated method stub
		return null;
	}

	GroupementRouleauFiniChoixReportValue getGroupementByQteChoix(String choix, Double quantite,
			Map<GroupementRouleauFiniChoixReportValue, Long> map, Boolean special) {

		// System.out.println("######## PARAMS FONCTION #########");

		// System.out.println("Choix : "+choix);
		// System.out.println("quantite : "+quantite);

		// System.out.println("######## PARAMS FONCTION #########");

		if (map != null && map.size() > 0) { // System.out.println("ENTER TO MAP ");
			for (GroupementRouleauFiniChoixReportValue grp : map.keySet()) {

				// System.out.println("### KEY choix : "+grp.getChoix());
				// System.out.println("###### Key Qte : "+grp.getQuantite());

				if (grp.getChoix().equals(choix) && grp.getQuantite().equals(quantite) && (special == false)) { // System.out.println("#####
																												// SORTIR
																												// With
																												// Value
																												// ######");
					return grp;

				}

			}

			// System.out.println("###### SORTIE FROM MAP ######### ");

		}
		return null;

	}

}
